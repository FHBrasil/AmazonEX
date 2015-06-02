package com.paypal.hybris.addon.postprocessors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Andrei_Krauchanka (EPAM Systems)
 */
public class ExcludeUrlSetPostProcessor implements BeanPostProcessor
{
	private String beanName;
	private Set<String> excludeUrlSet;

	@Override public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException
	{
		return bean;
	}

	@Override public Object postProcessAfterInitialization(Object bean, String name) throws BeansException
	{
		if (StringUtils.isNotEmpty(beanName) && beanName.equals(name))
		{
			HashSet urlSet = (HashSet) bean;
			urlSet.addAll(excludeUrlSet);

		}
		return bean;
	}

	public String getBeanName()
	{
		return beanName;
	}

	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
	}

	public Set<String> getExcludeUrlSet()
	{
		return excludeUrlSet;
	}

	public void setExcludeUrlSet(Set<String> excludeUrlSet)
	{
		this.excludeUrlSet = excludeUrlSet;
	}
}
