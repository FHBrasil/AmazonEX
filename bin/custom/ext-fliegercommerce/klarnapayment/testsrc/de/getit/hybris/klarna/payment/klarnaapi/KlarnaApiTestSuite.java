package de.getit.hybris.klarna.payment.klarnaapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ KlarnaFetchPClassesTest.class, KlarnaGetAddressTest.class, KlarnaHasAccountTest.class, KlarnaCheckOrderStatusTest.class, KlarnaCalcMonthlyCostTest.class, KlarnaAddArticleTest.class,
    KlarnaSetAddressTest.class, KlarnaReserveAmountTest.class })
public class KlarnaApiTestSuite {

}
