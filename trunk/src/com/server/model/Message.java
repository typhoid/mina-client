package com.server.model;

import com.server.constants.CONSTANTS;

public class Message
{
	private String value = "";
	private int type = CONSTANTS.TYPES.TYPE_NONE;

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
