package com.example.logan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAddressFromGeo {

	public static String getAddressByLatLng(String latLng) {
		String address = "";
		BufferedReader in = null;
		try {
			URL url = new URL(
					"http://maps.google.com/maps/api/geocode/json?latlng="
							+ latLng + "&language=zh-CN&sensor=true");
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			httpConn.setDoInput(true);
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			String result = "";
			while ((line = in.readLine()) != null) {
				if (line.contains("formatted_address")) {
					result += line;
					break;
				}
			}
			in.close();
			String[] temp = result.split("\"");
			address = temp[temp.length-2];
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}
}