package com.infracloud.urlshortner.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.validator.routines.UrlValidator;


public class Utility {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	
	public static boolean isValidUrl(String url) {
		boolean validUrl = false;
		String[] schemes = {"http","https"};
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (urlValidator.isValid(url)) {
			validUrl = true;
		} else {
			validUrl = false;
		}
		
		return validUrl;
	}
	
	
	public static void writeMapIntoTextFile(HashMap<String, String> map, String textFilePath) throws IOException {
        File file = new File(textFilePath);
        
        BufferedWriter bf = null;
        
        try {
        	  
            bf = new BufferedWriter(new FileWriter(file));
  
            for (Map.Entry<String, String> entry :
                 map.entrySet()) {
  
                bf.write(entry.getKey() + "="
                         + entry.getValue());
  
                bf.newLine();
            }
  
            bf.flush();
        }
        catch (FileNotFoundException e) {
        	throw new FileNotFoundException("File Not Found Exception");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
  
            try {
  
                bf.close();
            }
            catch (Exception e) {
            	
            }
        }
    }
	
	public static HashMap<String, String> readTextFileIntoMap(String textFilePath) throws IOException
	{
	    HashMap<String, String> map = new HashMap<String, String>();
	    String line;
	    BufferedReader reader = null;
	   
	    try {
	    	 reader = new BufferedReader(new FileReader(textFilePath));
		     while ((line = reader.readLine()) != null)
			    {
			        String[] parts = line.split("=", 2);
			        if (parts.length >= 2)
			        {
			            String key = parts[0];
			            String value = parts[1];
			            map.put(key, value);
			        } else {
			            System.out.println("ignoring line: " + line);
			        }
			    }
			    return map;
	
	   }catch (FileNotFoundException e) {
        	throw new FileNotFoundException("File Not Found");
        }
        finally {
  
            try {
  
            	reader.close();
            }
            catch (Exception e) {
            	
            }
        }
	}
	   
	
	
	public static String getKeyOfHashMapByValue(HashMap<String, String> map, String value) {
		
		Iterator<Map.Entry<String,String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<String,String> entry = iter.next();
		    if (entry.getValue().equals(value)) {
		        return entry.getKey();
		    }
		}
		return null;
	}
        
}
