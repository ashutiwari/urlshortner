package com.infracloud.urlshortner.controller;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infracloud.urlshortner.entity.ActualURL;
import com.infracloud.urlshortner.entity.ShortedURL;
import com.infracloud.urlshortner.exceptions.InvalidInputException;
import com.infracloud.urlshortner.exceptions.NotFoundException;
import com.infracloud.urlshortner.model.ShortedURLRequest;
import com.infracloud.urlshortner.service.URLShortnerService;



@SpringBootTest
@RunWith(SpringRunner.class)
public class URLShortnerControllerTest {

	
	@InjectMocks
	private URLShortnerController urlShortnerController;
	
	@Mock
	HttpServletResponse response;
	@Mock
	URLShortnerService urlShortnerService;
	
	@Test
	public void testGetUrlByShortedURL() throws Exception {
		try {
			ActualURL actualURL = new ActualURL();
			actualURL.setActualURL("actual_url");
			Mockito.when(urlShortnerService.getUrlByShortedURL("shorten_url")).thenReturn(actualURL);
			urlShortnerController.getUrlByShortedURL(response, "shorten_url");
			assert (true);
		} catch (Exception e) {
			assert (false);
		}
	}
	
	@Test
	public void testGetUrlByShortedURLWhenNotFound() throws Exception {
		try {
			ActualURL actualURL = new ActualURL();
			actualURL.setActualURL("actual_url");
			Mockito.when(urlShortnerService.getUrlByShortedURL("shorten_url")).thenThrow(NotFoundException.class);
			urlShortnerController.getUrlByShortedURL(response, "shorten_url");
			assert (true);
		} catch (Exception e) {
			assert (false);
		}
	}
	
	
	
	@Test
	public void testShortedURL() throws Exception {
		try {
			ShortedURL shortedURL = new ShortedURL();
			ShortedURLRequest shortedURLRequest = new ShortedURLRequest();
			shortedURLRequest.setUrl("url");
			shortedURL.setShortedURL("sorted_url");
			Mockito.when(urlShortnerService.createShortedURL(shortedURLRequest)).thenReturn(shortedURL);
			urlShortnerController.shortedURL(response, shortedURLRequest);
			assert (true);
		} catch (Exception e) {
			assert (false);
		}
	}
	
	@Test
	public void testShortedURLWithInvalidURL() throws Exception {
		try {
			ShortedURL shortedURL = new ShortedURL();
			ShortedURLRequest shortedURLRequest = new ShortedURLRequest();
			shortedURLRequest.setUrl("url");
			shortedURL.setShortedURL("sorted_url");
			
			Mockito.doThrow(InvalidInputException.class).when(urlShortnerService).createShortedURL(shortedURLRequest);
			urlShortnerController.shortedURL(response, shortedURLRequest);
			assert (true);
		} catch (Exception e) {
			assert (false);
		}
	}
}
