package com.server.model;

import com.server.constants.Constants;

public class Message
{
	private String value = "";
	private int type = Constants.TYPE_NONE;

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}