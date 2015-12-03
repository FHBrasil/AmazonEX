package de.fliegersoftware.amazon.core.compatibility;

import org.springframework.beans.factory.annotation.Required;


public class MapMergeDirective  {

	private Object key;
	private Object value;
	private String mapPropertyDescriptor; 
	private String fieldName;
	
	public Object getKey() {
		return key;
	}
	
	@Required
	public void setKey(Object key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	@Required
	public void setValue(Object value) {
		this.value = value;
	}

	public String getMapPropertyDescriptor() {
		return mapPropertyDescriptor;
	}

	public void setMapPropertyDescriptor(String mapPropertyDescriptor) {
		this.mapPropertyDescriptor = mapPropertyDescriptor;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
