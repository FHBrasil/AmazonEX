package de.kpfamily.core.job;

//import de.hybris.platform.webfoundation.constants.CONFIGURATION;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.core.Registry;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.jalo.AbortCronJobException;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.kpfamily.core.model.UpdateTrustedShopImageCronJobModel;
//import de.babyartikel.system.job.util.JobResult;


public class UpdateTrustedShopImageJobPerformable extends AbstractJobPerformable<UpdateTrustedShopImageCronJobModel>
{
	
	private static Logger LOG = Logger.getLogger(UpdateTrustedShopImageJobPerformable.class);
	

	@Override
	public PerformResult perform(final UpdateTrustedShopImageCronJobModel cronJobModel) {
		
		UpdateTrustedShopImageCronJobModel updateTrustedShopImageCronJobModel;
		
		// writing the image url in a String
		String urlImg = updateTrustedShopImageCronJobModel.getTrustedShopImageUrl();
		
		//creating the string that contains the path where the image should be saved
		String hybrisPath = updateTrustedShopImageCronJobModel.getHybrisBinDirManual();
		String kpfamilyTrustedShopDir = updateTrustedShopImageCronJobModel.getKpfamilyTrustedShopImageDir();		
		
		File file = new File(hybrisPath + kpfamilyTrustedShopDir);

		try
		{
			//putting that String in a URL object
			URL url = new URL(urlImg);						
			BufferedImage imagem = ImageIO.read(url);											
			ImageIO.write(imagem, "gif", file);						
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		
		catch (Exception e)
		{				
			LOG.error("Error updating image", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
		}

	}

	
}