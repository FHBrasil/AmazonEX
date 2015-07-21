package com.pixi.webservices.jaxb.adapter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanAdapter extends XmlAdapter<Integer, Boolean>
{
    @Override
    public Boolean unmarshal(Integer asInteger)
    {
        return asInteger == null ? Boolean.FALSE : asInteger == 1;
    }

    @Override
    public Integer marshal(Boolean asBoolean)
    {
        return asBoolean == null ? 0 : asBoolean ? 1 : 0;
    }
}