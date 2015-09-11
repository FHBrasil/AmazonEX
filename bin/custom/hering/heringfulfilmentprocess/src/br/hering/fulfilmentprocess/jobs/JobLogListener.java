package br.hering.fulfilmentprocess.jobs;

import org.apache.log4j.Level;
import org.apache.log4j.MDC;
import org.apache.log4j.spi.LoggingEvent;

import de.hybris.platform.util.logging.HybrisLogListener;
import de.hybris.platform.util.logging.HybrisLogger;

public class JobLogListener {

	private StringBuffer stringBuffer = new StringBuffer();
	private InternalListener listener;
	private String job;

	public JobLogListener() {
	}

	public void startListening() {
		listener = new InternalListener();
		job = (String) MDC.get("CronJob");
		HybrisLogger.addListener(listener);
	}

	public void stopListening() {
		HybrisLogger.removeListener(listener);
	}

	public String getLogs() {
		return stringBuffer.toString();
	}

	private class InternalListener implements HybrisLogListener {

		@Override
		public boolean isEnabledFor(Level level) {
			return true;
		}

		@Override
		public void log(LoggingEvent event) {
			if(job.equals(event.getMDC("CronJob"))) {
				stringBuffer.append(event.getRenderedMessage() + "\n");
			}
		}
	}
}