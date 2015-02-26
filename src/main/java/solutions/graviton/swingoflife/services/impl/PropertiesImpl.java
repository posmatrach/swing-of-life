package solutions.graviton.swingoflife.services.impl;

import java.util.ResourceBundle;

import solutions.graviton.swingoflife.services.Properties;

public class PropertiesImpl implements Properties
{
	private final ResourceBundle resources;

	public PropertiesImpl()
	{
		this.resources = ResourceBundle.getBundle("app");
	}

	public String getProperty(String key)
	{
		return resources.getString(key);
	}
}
