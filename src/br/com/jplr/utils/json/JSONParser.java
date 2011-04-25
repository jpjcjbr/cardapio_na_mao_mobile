package br.com.jplr.utils.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONParser {
	
	private static GsonBuilder jsonBuilder;
	
	private static Gson googleJson;
	
	static{
		jsonBuilder = new GsonBuilder();
	
		googleJson = jsonBuilder.create();
	}
	
	public static <T> T parse(String json, Class<T> tipo) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(json);
		return googleJson.fromJson(jsonObject.toString(), tipo);
	}
}
