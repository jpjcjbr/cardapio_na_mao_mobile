package br.com.jplr.utils.http;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

public class HttpClient {
	
	public static byte[] getImagem(String urlImagem)
	{
		return getBytes(urlImagem);
	}
	public static byte[] getBytes(String caminho)
	{
		try
		{
			URL url = new URL(caminho);
			URLConnection ucon = url.openConnection();
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is,128);
			ByteArrayBuffer baf = new ByteArrayBuffer(128);
			int current = 0;
			while ((current = bis.read()) != -1) {
			        baf.append((byte) current);
			}
			
			return baf.toByteArray();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public static String getJSON(String caminho) {
		byte[] bytes = getBytes(caminho);
		String stringJSON = new String(bytes);
		
		if(!stringJSON.startsWith("{"))
		{
			stringJSON = "{\"itens\":" + stringJSON + "}";
		}
		
		return stringJSON;
	}
	
}
