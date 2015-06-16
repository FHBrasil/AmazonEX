package de.kpfamily.core.job;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.kpfamily.core.model.UpdateTrustedShopImageCronJobModel;



public class UpdateTrustedShopImageJobPerformable extends AbstractJobPerformable<UpdateTrustedShopImageCronJobModel>
{
		
	private static Logger LOG = Logger.getLogger(UpdateTrustedShopImageJobPerformable.class);
	

	@Override
	public PerformResult perform(final UpdateTrustedShopImageCronJobModel cronJobModel) {
		
		String urlImg = cronJobModel.getTrustedShopImageUrl();
		
		//creating the string that contains the path where the image should be saved
		String hybrisPath = cronJobModel.getHybrisBinDirManual();
		String kpfamilyTrustedShopDir = cronJobModel.getKpfamilyTrustedShopImageDir();	
		
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