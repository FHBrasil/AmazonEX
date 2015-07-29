//package de.fliegersoftware.amazon.rest;
//
//import java.util.Collection;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.Response;
//
//import de.hybris.platform.core.model.user.CustomerModel;
//import de.hybris.platform.webservices.AbstractCollectionResource;
//import de.hybris.platform.webservices.AbstractYResource;
//import de.hybris.platform.core.dto.user.CustomersDTO;
//
//@Path("/customers")
//public class CustomersResource extends AbstractCollectionResource<Collection<CustomerModel>>{
//
//	public CustomersResource() {
//		super("Customer");
//	}
//	
//	@GET
//	public Response getAllCustomers() {
//		return createGetResponse().build(CustomersDTO.class);
//	}
//
//	@Path("{uid}")
//	public AbstractYResource<CustomerModel> getCustomerResource(@PathParam("uid") String uid) {
//		final CustomerResource resource = resourceCtx.getResource(CustomerResource.class);
//		resource.setResourceId(uid);
//		resource.setParentResource(this);
//		return resource;
//	}
//}
