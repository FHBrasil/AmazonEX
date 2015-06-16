package de.kpfamily.core.job;

import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;



public class UpdateTrustedShopImageJobPerformable extends AbstractJobPerformable/*
																										   * AbstractJobPerformable<
																										   * UpdateTrustedShopImageCronJobModel>
																										   */
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final CronJobModel arg0)
	{
		// YTODO Auto-generated method stub
		return null;
	}
	//
	//	private static Logger LOG = Logger.getLogger(UpdateTrustedShopImageJobPerformable.class);
	//
	//
	//	@Override
	//	public PerformResult perform(final UpdateTrustedShopImageCronJobModel cronJobModel) {
	//
	//		String urlImg = cronJobModel.getTrustedShopImageUrl();
	//
	//		//creating the string that contains the path where the image should be saved
	//		String hybrisPath = cronJobModel.getHybrisBinDirManual();
	//		String kpfamilyTrustedShopDir = cronJobModel.getKpfamilyTrustedShopImageDir();
	//
	//		File file = new File(hybrisPath + kpfamilyTrustedShopDir);
	//
	//		try
	//		{
	//			//putting that String in a URL object
	//			URL url = new URL(urlImg);
	//			BufferedImage imagem = ImageIO.read(url);
	//			ImageIO.write(imagem, "gif", file);
	//			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	//		}
	//
	//		catch (Exception e)
	//		{
	//			LOG.error("Error updating image", e);
	//			return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
	//		}
	//
	//	}
	//
	//
}