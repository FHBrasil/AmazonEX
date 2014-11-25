package xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class StringAdapter extends XmlAdapter<String, String>
{

	@Override
	public String marshal(final String string) throws Exception
	{
		if (null == string)
		{
			return "";
		}
		return string;
	}

	@Override
	public String unmarshal(final String string) throws Exception
	{
		if ("".equals(string))
		{
			return null;
		}
		return string;
	}

}