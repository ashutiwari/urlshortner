package com.infracloud.urlshortner.service;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infracloud.urlshortner.entity.ActualURL;
import com.infracloud.urlshortner.entity.ShortedURL;
import com.infracloud.urlshortner.exceptions.InvalidInputException;
import com.infracloud.urlshortner.exceptions.NotFoundException;
import com.infracloud.urlshortner.model.ShortedURLRequest;
import com.infracloud.urlshortner.utils.Utility;


@Service
public class URLShortnerService {
	private static final Logger logger = LoggerFactory.getLogger(URLShortnerService.class);

	@Value("${base.url}")
	private String baseURL;
	

	@Value("${shorturl.filepath}")
	private String filepath;
	
	
	public ShortedURL createShortedURL(ShortedURLRequest shortedURLRequest) throws InvalidInputException, IOException {

		if(Utility.isValidUrl(shortedURLRequest.getUrl())) {
			logger.info("Url:"+ shortedURLRequest.getUrl() +" is valid");
			ShortedURL shortedURL = new ShortedURL();

			if(filepath==null && baseURL==null) {
				// for unit tests execution
				
				filepath = "src/test/java/test_shorted_url.txt";
				baseURL = "http://localhost:8080/";
			}
			HashMap<String, String>  allExistingShortedURL  = Utility.readTextFileIntoMap(filepath);
			
			if(allExistingShortedURL.containsKey(shortedURLRequest.getUrl())) {
				logger.info("Shorted Url:"+ shortedURLRequest.getUrl() +" is alreday created returning existing one");
				shortedURL.setShortedURL(allExistingShortedURL.get(shortedURLRequest.getUrl()));
				
			}else {
				logger.info("Shorted Url:"+ shortedURLRequest.getUrl() +" not created creating a new one");
				String id = Utility.getUUID();
				shortedURL.setShortedURL(baseURL+id);
				allExistingShortedURL.put(shortedURLRequest.getUrl(), baseURL+id);
				Utility.writeMapIntoTextFile(allExistingShortedURL, filepath);
			}
			return shortedURL;

		}
		else {
			logger.error("Url:"+ shortedURLRequest.getUrl() +" is valid");
			throw new InvalidInputException("Url:"+ shortedURLRequest.getUrl() +" is not valid");
			
		}		
	}
	
	public ActualURL getUrlByShortedURL(String shortedURL) throws NotFoundException, IOException {
		

		if(filepath==null && baseURL==null) {
			// for unit tests execution
			
			filepath = "src/test/java/test_shorted_url.txt";
			baseURL = "http://localhost:8080/";
		}
		
		HashMap<String, String>  allExistingShortedURL  = Utility.readTextFileIntoMap(filepath);

		if(allExistingShortedURL.containsValue(shortedURL)){
			System.out.println("The hashmap contains value:"+ shortedURL);
			
			ActualURL actualURL = new ActualURL();
			actualURL.setActualURL(Utility.getKeyOfHashMapByValue(allExistingShortedURL, shortedURL));
			return actualURL;
		} else {
			System.out.println("The hashmap does not contains value "+ shortedURL);
			throw new NotFoundException("shortedURL:"+ shortedURL +" does not exist");

		}
		
	}

}
