package br.hering.core.jobs;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.customergroups.CustomerGroupFacade;
import de.hybris.platform.commercefacades.customergroups.exceptions.InvalidCustomerGroupException;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.UserGroupOption;
import de.hybris.platform.commercefacades.user.data.PrincipalData;
import de.hybris.platform.commercefacades.user.data.UserGroupData;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.europe1.constants.GeneratedEurope1Constants.Enumerations.UserDiscountGroup;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.user.daos.UserGroupDao;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import br.hering.core.model.jobs.EmployeeIdentificationJobModel;

import de.hybris.platform.util.Config;

public class EmployeeIdentificationJobPerformable extends AbstractJobPerformable<EmployeeIdentificationJobModel> 
{
	private Logger LOG = Logger.getLogger(EmployeeIdentificationJobPerformable.class);
	
	private final static String PRODUTIVO_GROUP = "PRODUTIVO";
	
	private final static String ADMINISTRATIVO_GROUP = "ADMINISTRATIVO";
	
	private Map<String, EmployeeIdentification> thisJobMap = new HashMap<String, EmployeeIdentification>();
	
//	private String baseCustomerGroupId;
	
	@Resource
	private CustomerGroupFacade customerGroupFacade;
	
	@Resource
	private UserFacade userFacade;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CustomerFacade customerFacade;
		
	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
	 */
	@Override
	public PerformResult perform(final EmployeeIdentificationJobModel job)
	{
		CronJobResult cronJobResult = CronJobResult.SUCCESS;
		
		try{
			//read the file into a map
			prepare();
		}
		catch (Exception e){
			LOG.debug("Error while preparing execution: " + e);
			cronJobResult = CronJobResult.ERROR;
		}
		
		try
		{
			//check if the groups contains only the correct members
			updateGroups();
		}
		catch (Exception e)
		{
			LOG.debug("Error while updating gruoups: " + e);
			cronJobResult = CronJobResult.ERROR;
		}

		try
		{
			//check if the employees are in the correct group
			checkEmployeesInGroup();
		}
		catch (Exception e)
		{
			LOG.debug("Error while checking if users are in correct group: " + e);
			cronJobResult = CronJobResult.ERROR;
		}
		
		try
		{
			insertEmployeesInGroup();
		}
		catch (Exception e)
		{
			LOG.debug("Error while inserting employees into groups: " + e);
			cronJobResult = CronJobResult.ERROR;
		}
		thisJobMap.clear();
		return new PerformResult(cronJobResult, CronJobStatus.FINISHED);
	}

	/**
	 * Check if the loaded employees are already in the group they should be
	 */
	private void checkEmployeesInGroup()
	{
		List<String> userRemoveList = new ArrayList<String>();
		
		for (EmployeeIdentification employeeIdentification : thisJobMap.values())
		{
			List<UserGroupData> customerGroupsForUser = customerGroupFacade.getCustomerGroupsForUser(employeeIdentification.uid);
			
			for (UserGroupData userGroupData : customerGroupsForUser)
			{
				if(userGroupData.getUid().equalsIgnoreCase(employeeIdentification.groupId)){
					userRemoveList.add(employeeIdentification.uid);
					LOG.info("User: " + employeeIdentification + " is already member of group:" + employeeIdentification.groupId);
					break;
				}
			}
		}
		
		for (String uid : userRemoveList)
		{
			thisJobMap.remove(uid);
		}
	}

	/**
	 * Read the newly added csv file and add all the entries to the map with
	 * 
	 * the employees entries
	 * 
	 * */
	private void prepare() throws Exception
	{
		Iterator<String> iterator = null;	
		try {
			iterator = getEmployeeList();
			
			if(!iterator.hasNext()) {
				LOG.info("There's no CPF to check, finished job execution");
				throw new Exception("No cpf to import");
			}
		} catch (Exception e) {
			LOG.error("error", e);
			throw new Exception("Unexpected error" + e);
		}
		
		while(iterator.hasNext()) {
			final String funcionario = iterator.next();
			String[] dados = funcionario.split("[,;]");
			try
			{
				if(dados.length < 3){
					LOG.info("Invalid data: " + dados);
					continue;
				}
				
				final String cpf = dados[0].replaceAll("[\"\']", "");
				final String tipo = dados[1].replaceAll("[\"\']", "");
				final String group = dados[2].replaceAll("[\"\']", "");
				final String[] uidList = findCustomerByCPF(cpf);
				
				if(uidList == null || uidList.length == 0)
				{
					LOG.info("Customer not found for cpf: " + cpf);
				} else {
					for (String uid : uidList)
					{
						thisJobMap.put(uid, new EmployeeIdentification(uid, cpf, tipo, group));	
					}
				}
			}
			catch (Exception e){
				LOG.error("Unexpected error with:" + funcionario, e);
			}
		}
		final Set<UserGroupOption> options = new HashSet<UserGroupOption>();
		try
		{
			customerGroupFacade.getCustomerGroup(ADMINISTRATIVO_GROUP, options);
		}
		catch (UnknownIdentifierException e)
		{
			LOG.info("Creating customer group: " + ADMINISTRATIVO_GROUP);
			customerGroupFacade.createCustomerGroup(ADMINISTRATIVO_GROUP, ADMINISTRATIVO_GROUP);
		}
		
		try
		{
			customerGroupFacade.getCustomerGroup(PRODUTIVO_GROUP, options);
		}
		catch (UnknownIdentifierException e)
		{
			LOG.info("Creating customer group: " + PRODUTIVO_GROUP);
			customerGroupFacade.createCustomerGroup(PRODUTIVO_GROUP, PRODUTIVO_GROUP);
		}
	}
	
	private void insertEmployeesInGroup()
	{	
		LOG.info("Starting insert employees into groups");
		for (String uid : thisJobMap.keySet())
		{
			final EmployeeIdentification employeeIdentification = thisJobMap.get(uid);
			
			try
			{
				customerGroupFacade.removeUserFromCustomerGroup(ADMINISTRATIVO_GROUP, uid);
				LOG.info(employeeIdentification + " removed from " + ADMINISTRATIVO_GROUP);
			}
			catch (Exception e)
			{
				LOG.info(employeeIdentification + " does not belong to group" + ADMINISTRATIVO_GROUP + e);
			}
			
			try
			{
				customerGroupFacade.removeUserFromCustomerGroup(PRODUTIVO_GROUP, uid);
				LOG.info(employeeIdentification + " removed from " + PRODUTIVO_GROUP);
			}
			catch (Exception e)
			{
				LOG.info(employeeIdentification + " does not belong to group" + PRODUTIVO_GROUP + e);
			}
			
			try
			{
				customerGroupFacade.addUserToCustomerGroup(employeeIdentification.groupId, uid);
				LOG.info(employeeIdentification + " successfully added to group " + employeeIdentification.groupId);
			}
			catch (Exception e)
			{
				LOG.info(employeeIdentification + " could not be added to group" + employeeIdentification.groupId);
			}
		}
	}

	/**
	 * 
	 */
	private void updateGroups()
	{
		try
		{
			removeInvalidUsersFromGroup(ADMINISTRATIVO_GROUP);
		}
		catch (Exception e)
		{
			LOG.info("Unexpected error while updating group: " + ADMINISTRATIVO_GROUP);
		}
		
		try
		{
			removeInvalidUsersFromGroup(PRODUTIVO_GROUP);
		}
		catch (Exception e)
		{
			LOG.info("Unexpected error while updating group: " + PRODUTIVO_GROUP);
		}
	}

	private Iterator<String> getEmployeeList() throws IOException, URISyntaxException, Exception {
		final File directory = new File("/HYBRIS/medias/csv");
		final Pattern pattern = Pattern.compile("^[^.]+\\.csv", Pattern.CASE_INSENSITIVE);
		
		if (directory.exists() && directory.isDirectory())
		{	
			FilenameFilter filenameFilter = new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					Matcher matcher = pattern.matcher(name);
					if(matcher.matches()){
						return true;
					}
					return false;
				}
			};
			
			File[] files = directory.listFiles(filenameFilter);

			Arrays.sort(files, new Comparator<File>() {
			    public int compare(File f1, File f2) {
			        return Long.compare(f1.lastModified(), f2.lastModified());
			    }
			});
			
			if(files.length > 0){
				return FileUtils.lineIterator(files[0]);
			} else {
				throw new Exception("Error opening csv file");
			}
		}
		throw new Exception("No file found");
	}

	private String[] findCustomerByCPF(final String cpf)
	{	
		FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {uid} FROM {Customer} WHERE {cpfCnpj} = ?cpf");
		query.addQueryParameter("cpf", cpf);
		query.setResultClassList(Arrays.asList(String.class));
		
		final List<String> result = flexibleSearchService.<String>search(query).getResult();
		
		if(CollectionUtils.isNotEmpty(result))
		{
			return result.toArray(new String[0]);
//			return result.iterator().next();
		}
		return null;
	}
	
	/**
	 * For a given group, get all its members and check if they are still in the
	 * new employee list. If they are not, remove them from the group
	 * */
	private void removeInvalidUsersFromGroup(final String userGroup) {
		Set<UserGroupOption> options = new HashSet<UserGroupOption>();
		options.add(UserGroupOption.MEMBERS);
		
		UserGroupData customerGroup = customerGroupFacade.getCustomerGroup(userGroup, options);
		List<? extends PrincipalData> members = customerGroup.getMembers();
		
		for (PrincipalData userData : members) {
			if(thisJobMap.containsKey(userData.getUid())){
				LOG.info(thisJobMap.get(userData.getUid()) + " is employee ");
			} else {
				customerGroupFacade.removeUserFromCustomerGroup(userGroup, userData.getUid());
				LOG.info("Removing " + userData.getUid() + ". No longer employee ");	
			}
		}
	}
	
	private class EmployeeIdentification{
		final String uid;
		final String cpf;
		final String tipo;
		final String groupId;
		
		EmployeeIdentification(final String uid, final String cpf, final String tipo, final String groupId){
			this.uid = uid;
			this.cpf = cpf;
			this.tipo = tipo;
			this.groupId = groupId;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "Uid: " + uid + ";CPF: " + cpf + ";tipo: " + tipo + ";groupid: " + groupId;
		}
	}
	
	
//	//daqui pra baixo deveria estar na facade
//	private void removeUserFromCustomerGroup(final String customerGroupId, final String userId)
//	{
//		Assert.notNull(customerGroupId);
//		Assert.notNull(userId);
//		final UserModel user = getUserService().getUserForUID(userId);
//		final UserGroupModel group = getCustomerGroupById(customerGroupId);
//		
//		final Set<PrincipalModel> members = new HashSet<PrincipalModel>(group.getMembers());
//		final HashSet<PrincipalModel> modifiedMembers = new HashSet<PrincipalModel>(members);
//		modifiedMembers.remove(user);
//		group.setMembers(modifiedMembers);
//		modelService.save(group);
//		modelService.save(user);
//		modelService.refresh(user);
//	}
//
//	public UserService getUserService(){
//		return this.userService;
//	}
//	
//	public void setUserService(final UserService userService){
//		this.userService = userService;
//	}	
//
//	private UserGroupModel getCustomerGroupById(final String customerGroupId)
//	{
//		final UserGroupModel group = getUserService().getUserGroupForUID(customerGroupId);
//		if (isCustomerGroup(group))
//		{
//			return group;
//		}
//		throw new InvalidCustomerGroupException(group);
//	}
//	
//	private boolean isCustomerGroup(final UserGroupModel group)
//	{
//		final UserGroupModel customerGroup = getBaseCustomerGroup();
//		return getUserService().isMemberOfGroup(group, customerGroup) || group.equals(customerGroup);
//	}
//
//	private UserGroupModel getBaseCustomerGroup()
//	{
//		final UserGroupModel customerGroup = getUserService().getUserGroupForUID(getBaseCustomerGroupId());
//		if (customerGroup == null)
//		{
//			throw new IllegalStateException("No group called " + getBaseCustomerGroupId() + " found in the system.");
//		}
//		return customerGroup;
//	}
//	
//	@Required
//	public void setBaseCustomerGroupId(final String value)
//	{
//		this.baseCustomerGroupId = value;
//	}
//
//	public String getBaseCustomerGroupId()
//	{
//		return baseCustomerGroupId;
//	}

//	@Required
//	public void setModelService(final ModelService modelService)
//	{
//		this.modelService = modelService;
//	}
//
//	protected ModelService getModelService()
//	{
//		return modelService;
//	}
	
}