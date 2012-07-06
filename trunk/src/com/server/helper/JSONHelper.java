package com.server.helper;

import com.server.json.JSONArray;
import com.server.json.JSONException;
import com.server.json.JSONObject;

public final class JSONHelper
{
	public static String getString(JSONObject jsonObject, String keyStr)
	{
		String resultStr = null;
		try
		{
			resultStr = jsonObject.getString(keyStr);
		} catch (JSONException e)
		{
			System.out.println(keyStr + " is not available!");
		}
		if(resultStr == null) return "";
		return resultStr;
	}

	public static int getInt(JSONObject jsonObject, String keyStr)
	{
		int result = 0;
		try
		{
			result = jsonObject.getInt(keyStr);
		} catch (JSONException e)
		{
			System.out.println(keyStr + " is not available!");
		}
		return result;
	}
	public static boolean getBoolean(JSONObject jsonObject, String keyStr)
	{
		boolean result = true;
		try
		{
			result = jsonObject.getBoolean(keyStr);
		} catch (JSONException e)
		{
			System.out.println(keyStr + " is not available!");
		}
		return result;
	}
	public static double getDouble(JSONObject jsonObject, String keyStr)
	{
		double result = 0;
		try
		{
			result = jsonObject.getDouble(keyStr);
		} catch (JSONException e)
		{
			System.out.println(keyStr + " is not available!");
		}
		return result;
	}
	public static long getLong(JSONObject jsonObject, String keyStr)
	{
		long result = 0;
		try
		{
			result = jsonObject.getLong(keyStr);
		} catch (JSONException e)
		{
			System.out.println(keyStr + " is not available!");
		}
		return result;
	}
	public static JSONObject getJSONObject(JSONObject jsonObject, String keyStr)
	{
		JSONObject result = null;
		try
		{
			result = jsonObject.getJSONObject(keyStr);
		} catch (JSONException e)
		{
			System.out.println(keyStr + " is not available!");
		}
		return result;
	}
	public static JSONArray getJSONArray(JSONObject jsonObject, String keyStr)
	{
		JSONArray result = null;
		try
		{
			result = jsonObject.getJSONArray(keyStr);
		} catch (JSONException e)
		{
			System.out.println(keyStr + " is not available!");
		}
		return result;
	}
	
}
