package de.fliegersoftware.amazon.login.addon.constants;

public interface AmazonLoginAddonConstants {
	
	final String ADDON_PREFIX = "addon:/amazonloginaddon/";

	interface Views {
		interface Pages {
			String AmazonConfirmAccountPage = ADDON_PREFIX
					+ "pages/amazonConfirmAccountPage";
			
			String AmazonManualAdditionFirstLoginPage = ADDON_PREFIX
					+ "pages/amazonManualAdditionOnFirstLogin";
		}
	}

}
