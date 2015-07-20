package br.hering.fulfilmentprocess.jobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.fulfilmentprocess.model.jobs.TelephoneCellphoneReplaceCharJobModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

public class TelephoneCellphoneReplaceCharJobPerformable  extends AbstractJobPerformable<TelephoneCellphoneReplaceCharJobModel>
{
	private Logger LOG = Logger.getLogger(TelephoneCellphoneReplaceCharJobPerformable.class);
	
	@Resource
	private FlexibleSearchService flexibleSearchService;
	
	@Override
	public PerformResult perform(TelephoneCellphoneReplaceCharJobModel job) 
	{
		Assert.notNull(job);
		
		List<AddressModel> addressList = findAddress();
		
		if(CollectionUtils.isEmpty(addressList))
		{
			LOG.info("no telephone or cellphone with wrong char");
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}

		LOG.info(addressList.size() + " telephones and cellphones to export");
		
		if(fixAddress(addressList))
		{
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		
		return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
	}
	
	private boolean fixAddress(List<AddressModel> addressList){
		
		for(AddressModel add : addressList){
			LOG.info("Updating address pk:" + add.getPk());
			boolean update = false;
			if(add.getPhone1() != null && (add.getPhone1().contains("-") || add.getPhone1().contains(" "))){
				LOG.info("Updating address phone1:" + add.getPhone1());
				add.setPhone1(add.getPhone1().replaceAll("[^\\d.]", ""));
				update = true;
			}
			
			if(add.getCellphone() != null && (add.getCellphone().contains("-") || add.getCellphone().contains(" "))){
				LOG.info("Updating address phone1:" + add.getCellphone());
				add.setCellphone(add.getCellphone().replaceAll("[^\\d.]", ""));
				update = true;
			}
			
			if(add.getPostalcode() != null && (add.getPostalcode().contains("-") || add.getPostalcode().contains(" "))){
				LOG.info("Updating address postapostal:" + add.getPostalcode());
				add.setPostalcode(add.getPostalcode().replaceAll("[^\\d.]", ""));
				update = true;
			}
			
			if(update){
				LOG.info("Saving address pk:" + add.getPk());
				modelService.save(add);
				modelService.refresh(add);
			}else{
				LOG.info("Didn't update");
			}
		}
		
		return true;
	}

	private List<AddressModel> findAddress() 
	{
		StringBuilder sql = new StringBuilder("select {PK} from {Address} "
												+ "	where {phone1} like '%-%' or {cellphone} like '%-%' or {postalcode} like '%-%'"
													+ "or {phone1} like '% %' or {cellphone} like '% %' or {postalcode} like '% %'");
		Map<String, Object> params = new HashMap<String, Object>();
		return flexibleSearchService.<AddressModel>search(sql.toString(), params).getResult();
	}
}