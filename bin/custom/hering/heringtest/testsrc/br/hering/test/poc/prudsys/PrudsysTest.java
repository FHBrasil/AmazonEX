package br.hering.test.poc.prudsys;

import de.hybris.bootstrap.annotations.UnitTest;

@UnitTest
public class PrudsysTest 
{
//	private static final Logger LOG = Logger.getLogger(PrudsysTest.class);
//	
//	private final Communicator prudsys = Communicator.getInstance();
//	
//	private final Customer currentCustomer = new Customer("FRANTHESCOLLY_SAMPLE_UUID", "franthescolly.maneira@gmail.com");
//	
//	private final String productCode = "201400002592";
//	
//	private final String brandCode = "hauck";
//	
//	private final String blacklist = "";
//	
//	private List<Product> products;
//	
//	@Before
//	public void before()
//	{
//		products = Arrays.asList(
//				new Product("201100001592", 19.90),
//				new Product("201500000656", 84.94),
//				new Product("201500000651", 124.94),
//				new Product("201100001462", 99.94),
//				new Product("201300007222", 79.99)
//				);
//		
//		testTrackEventUserToSession();
//	}
//	
//	@Test
//	public void testPing()
//	{
//		Assert.assertTrue(prudsys.ping());
//	}
//	
//	@Test
//	public void testRecommendationForHomePage() //OK
//	{
//		List<String> recommendations = prudsys.homePage(currentCustomer.getUuid());
//		logRecommendations("homepage", recommendations);
//	}
//
//	@Test
//	public void testRecommendationForProductPage()//OK
//	{
//		List<String> recommendations = prudsys.productPage(currentCustomer.getUuid(), productCode, blacklist);
//		logRecommendations("productpage", recommendations);
//	}
//	
//	@Test
//	public void testRecommendationForBrandPage()//OK
//	{
//		List<String> recommendations = prudsys.brandPage(currentCustomer.getUuid(), brandCode);
//		logRecommendations("brandpage", recommendations);
//	}
//	
//	@Test
//	public void testRecommendationForBasketPage()//OK
//	{
//		prudsys.basketpage(currentCustomer.getUuid(), productCode);
//	}
//	
//	@Test
//	public void testRecommendationFor404and500Page()//OK
//	{
//		List<String> recommendations = prudsys.errorPage(currentCustomer.getUuid());
//		logRecommendations("errorpage", recommendations);
//	}
//	
//	@Test
//	public void testRecommendationForResultsNotFound()//OK
//	{
//		List<String> recommendations = prudsys.noResultPage(currentCustomer.getUuid());
//		logRecommendations("pagenotfound", recommendations);
//	}
//	
//	@Test
//	public void testTrackEventUserToSession()//OK
//	{
//		prudsys.trackEventUserToSession(currentCustomer.getUuid(), currentCustomer.getEmail());
//	}
//	
//	@Test
//	public void testTrackEventClick() //OK
//	{
//		prudsys.trackEventClick(currentCustomer.getUuid(), productCode, "categorypage", "box1");
//	}
//	
//	@Test
//	public void testTrackBasket()//OK
//	{
//		final List<String> items = new ArrayList<String>();
//		final List<String> qty = new ArrayList<String>();
//	
//		for(int i = 0; i < products.size(); i++)
//		{
//			items.add(products.get(i).getCode());
//			qty.add(String.valueOf(i + 1));
//		}
//		
//		prudsys.basket(currentCustomer.getUuid(), items, qty);
//	}
//	
//	@Test
//	public void testTrackOrder()//OK
//	{
//		final List<String> items = new ArrayList<String>();
//		final List<String> qty = new ArrayList<String>();
//		final List<String> utp = new ArrayList<String>();
//		
//		for(int i = 0; i < products.size(); i++)
//		{
//			items.add(products.get(i).getCode());
//			qty.add(String.valueOf(i + 1));
//			utp.add(String.valueOf(products.get(i).getPrice() * (i+1)));
//		}
//		
//		prudsys.order(currentCustomer.getUuid(), items, qty, utp);
//	}
//	
//	
//	private void logRecommendations(final String page, final List<String> recommendations) {
//		
//		LOG.debug("####################");
//		if(CollectionUtils.isEmpty(recommendations)) {
//			LOG.debug(page + " had no recommendation");
//			LOG.debug("####################");
//			return;
//		}
//		
//		LOG.debug(page + ": ");
//		for(String recommendation : recommendations) {
//			LOG.debug(recommendation);
//		}
//		LOG.debug("####################");
//	}
//	
//	private class Product {
//		private String code;
//		private double price;
//		public Product(String code, double price) {
//			super();
//			this.setCode(code);
//			this.setPrice(price);
//		}
//		public String getCode() {
//			return code;
//		}
//		public void setCode(String code) {
//			this.code = code;
//		}
//		public double getPrice() {
//			return price;
//		}
//		public void setPrice(double price) {
//			this.price = price;
//		}
//	}
//	
//	private class Customer {
//		public Customer(String uuid, String email) {
//			super();
//			this.uuid = uuid;
//			this.email = email;
//		}
//
//		private String uuid, email;
//
//		public String getEmail() {
//			return email;
//		}
//
//		public String getUuid() {
//			return uuid;
//		}
//	}
}