/**
 * 
 */
package br.hering.core.evaluator;

import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.hering.core.model.restrictions.UrlPatternRestrictionModel;

/**
 * @author franthescollymaneira
 *
 */
public class UrlPatternRestrictionEvaluator implements CMSRestrictionEvaluator<UrlPatternRestrictionModel>
{
	private static final Logger LOG = Logger.getLogger(UrlPatternRestrictionEvaluator.class);

	/* (non-Javadoc)
	 * @see de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator#evaluate(de.hybris.platform.cms2.model.restrictions.AbstractRestrictionModel, de.hybris.platform.cms2.servicelayer.data.RestrictionData)
	 */
	@Override
	public boolean evaluate(UrlPatternRestrictionModel restriction, RestrictionData context)
	{
		try
		{
			Collection<String> urlPatterns = restriction.getUrlPatterns();
			boolean matchAll = restriction.isMatchAllPatterns();

			if(CollectionUtils.isEmpty(urlPatterns))
			{
				LOG.error("empty urlPatterns");
				return false;
			}
			
			
			final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			final String parameter = request.getParameter("q");

			if(StringUtils.isBlank(parameter))
			{
				return false;
			}

			LOG.debug("patterns: " + urlPatterns);
			LOG.debug("match all patterns: " + matchAll);
			
			for(final String pattern : urlPatterns)
			{
				final String regexPattern = buildRegex(pattern);
				
				LOG.debug("pattern:" + regexPattern);
				//boolean matches = StringUtils.containsIgnoreCase(parameter, regexPattern);
				boolean matches = parameter.matches(regexPattern);
				if (matches && !matchAll)
				{
					LOG.debug("true");
					return true;
				}
				
				if(!matches && matchAll)
				{
					LOG.debug("false");
					return false;
				}
			}
			
			LOG.debug("result: " + matchAll);
			return matchAll;
		}
		catch (Exception e)
		{
			LOG.error("UrlPatternRestrictionEvaluator error", e);
			return false;
		}
	}

	/**
	 * @param pattern
	 * @return
	 */
	private String buildRegex(final String pattern)
	{
		boolean endsWithRegex = pattern.endsWith("$") || pattern.endsWith(".*");
		String regexPattern = "(?i).*" + pattern;
		
		if(!endsWithRegex) {
			regexPattern += ".*";
		}
		
		return regexPattern;
	}
}