//package de.fliegersoftware.amazon.rest;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.core.Response;
//
//import de.hybris.platform.core.model.user.CustomerModel;
//import de.hybris.platform.webservices.AbstractYResource;
//
//public class CustomerResource extends AbstractYResource<CustomerModel> {
//
//	public CustomerResource() {
//		super("Customer");
//	}
//
//	@GET
//	public Response getCustomer() {
//		return createGetResponse().build();
//	}
//
//	@Override
//	protected CustomerModel readResource(String resourceId) throws Exception {
//		CustomerModel model = new CustomerModel();
//		model.setUid(resourceId);
//		return (CustomerModel) readResourceInternal(model);
//	}
//}