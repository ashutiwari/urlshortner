package com.infracloud.urlshortner.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilityTest {
	
	
	String filePath = "src/test/java/test_shorted_url.txt";
	
	
	@Test
	public void testGetgetUUID() throws Exception {
		assertNotNull(Utility.getUUID());
	}

	@Test
	public void testIsValidUrl() throws Exception {
		String url = "https://github.com/ashutiwari/urlshortner";
		assertTrue(Utility.isValidUrl(url));
	}
	
	@Test
	public void testIsValidUrlFalse() throws Exception {
		String url = "github.com/ashutiwari/urlshortner";
		assertFalse(Utility.isValidUrl(url));
	}
	
	@Test
	public void testWriteMapIntoTextFile() throws Exception {
		
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("key", "value");
		String textFilePath = filePath;
		Utility.writeMapIntoTextFile(map, textFilePath);
		assertTrue(true);

	}
	
	@Test
	public void testWriteMapIntoTextFileWhenFileNotFound() throws Exception {
		
		try {
		    HashMap<String, String> map = new HashMap<String, String>();
		    map.put("key", "value");
			String textFilePath = "test"+filePath;
			Utility.writeMapIntoTextFile(map, textFilePath);
			assertTrue(false);
		}catch(FileNotFoundException e){
			System.out.println("error is : "+ e);
			assertTrue(true);
		}

	}
	
	@Test
	public void testReadTextFileIntoMap() throws Exception {
		
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("key", "value");
		String textFilePath = filePath;
		Utility.writeMapIntoTextFile(map, textFilePath);
		
		HashMap<String, String> allMapValues  = Utility.readTextFileIntoMap(textFilePath);
		assertTrue(allMapValues.get("key").equalsIgnoreCase("value"));

	}
	
	
	@Test
	public void testReadTextFileIntoMapWhenFileNotFound() throws Exception {
		
		try {
			HashMap<String, String> map = new HashMap<String, String>();
		    map.put("key", "value");
			String textFilePath = "test"+filePath;
			Utility.writeMapIntoTextFile(map, textFilePath);
			
			HashMap<String, String> allMapValues  = Utility.readTextFileIntoMap(textFilePath);
			assertFalse(allMapValues.get("key").equalsIgnoreCase("value"));

		}catch(FileNotFoundException e){
			System.out.println("error is : "+ e);
			assertTrue(true);
		}
	    
	}
	
	@Test
	public void testGetKeyOfHashMapByValue() throws Exception {
		
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("key", "value");
		String textFilePath = filePath;
		Utility.writeMapIntoTextFile(map, textFilePath);
		
		HashMap<String, String> allMapValues  = Utility.readTextFileIntoMap(textFilePath);
		
		String key = Utility.getKeyOfHashMapByValue(allMapValues, "value");
		
		assertTrue(key.equalsIgnoreCase("key"));

	}
	
	
	@Test
	public void testGetKeyOfHashMapByValueWhenValueNotExist() throws Exception {
		
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("key", "value");		
		assertNull(Utility.getKeyOfHashMapByValue(map, "value1"));

	}
}
