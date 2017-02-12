package com.ing.atm.locator.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.ing.atm.locator.constants.ATMConstants;
import com.ing.atm.locator.model.ATMLocation;
import com.ing.atm.locator.model.Address;
import com.ing.atm.locator.model.GeoLocation;


@Component
public class ATMLocatorDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ATMLocatorDAO.class);
	
	/**
	 * dao method which return the list of ATM Location.
	 * @param city, city as parameter 
	 * @return  return the list of ATM Location for given City.
	 * 
	 */
	
	public List<ATMLocation> getATMLocations(String city) {
		List<ATMLocation> responses = new ArrayList<ATMLocation>();
		
		JSONArray arry = null;
		try{
			String urlResult = getCompleteATMList(ATMConstants.URI);
			String parsedResult = urlResult.substring(urlResult.indexOf('['));
			arry = new JSONArray(parsedResult);
			Iterator<Object> itr = arry.iterator();
			while(itr.hasNext()){
				JSONObject obj = (JSONObject) itr.next();
				JSONObject addrObj = obj.getJSONObject(ATMConstants.ADDRESS);
				if(city != null && city.equals(addrObj.getString(ATMConstants.CITY))){
					ATMLocation atmLocation = new ATMLocation();
					
					GeoLocation geoLocation = new GeoLocation();
					JSONObject gl = addrObj.getJSONObject(ATMConstants.GEOLOCATION);
					geoLocation.setLat(gl.getDouble(ATMConstants.LAT));
					geoLocation.setLng(gl.getDouble(ATMConstants.LNG));
					
					Address address = new Address();
					address.setStreet(addrObj.getString(ATMConstants.STREET));
					address.setHousnumber(addrObj.getString(ATMConstants.HOUSENUMBER));
					address.setPostalCode(addrObj.getString(ATMConstants.POSTALCODE));
					address.setCity(addrObj.getString(ATMConstants.CITY));
					address.setGeoLocation(geoLocation);
					
					atmLocation.setAddress(address);
					atmLocation.setDistance(obj.getDouble(ATMConstants.DISTANCE));
					atmLocation.setType(obj.getString(ATMConstants.TYPE));
					
					responses.add(atmLocation);
				}
			}
		}catch(IOException ioe){
			logger.info("URL is unavailable"+ioe.getMessage());
		}
		
		return responses;
	}
	
	/**
	 * This method return the complete list of ATM 
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	
	public static String getCompleteATMList(String urlStr) throws IOException {
		  URL url = new URL(urlStr);
		  HttpURLConnection conn =
		      (HttpURLConnection) url.openConnection();

		  if (conn.getResponseCode() != 200) {
		    throw new IOException(conn.getResponseMessage());
		  }

		  // Buffer the result into a string
		  BufferedReader rd = new BufferedReader(
		      new InputStreamReader(conn.getInputStream()));
		  StringBuilder sb = new StringBuilder();
		  String line;
		  while ((line = rd.readLine()) != null) {
		    sb.append(line);
		  }
		  rd.close();

		  conn.disconnect();
		  return sb.toString();
		}

}
