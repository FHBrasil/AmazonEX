package com.fliegersoftware.addons.newsletteraddon.selectoption;

/**
 * @author luiza
 *
 */


public class SelectOption
{
	
	private final String code;
	private final String name;

	public SelectOption(final String code, final String name)
	{
		this.code = code;
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}
	
}	
	




/*public abstract class AbstractSelectOption
{
	
	public static class SelectOption
	{
		private final String code;
		private final String name;

		public SelectOption(final String code, final String name)
		{
			this.code = code;
			this.name = name;
		}

		public String getCode()
		{
			return code;
		}

		public String getName()
		{
			return name;
		}
	}	
	
}*/
