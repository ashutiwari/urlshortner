package com.infracloud.urlshortner.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.infracloud.urlshortner.entity.ActualURL;
import com.infracloud.urlshortner.entity.ShortedURL;
import com.infracloud.urlshortner.exceptions.InvalidInputException;
import com.infracloud.urlshortner.exceptions.NotFoundException;
import com.infracloud.urlshortner.model.ShortedURLRequest;

@RunWith(MockitoJUnitRunner.class)
public class URLShortnerServiceTest {

	String filePath = "src/test/java/test_shorted_url.txt";

	@InjectMocks
	private URLShortnerService urlShortnerService;

	@Test
	public void testCreateShortedURL() throws Exception {
		try {
			ShortedURLRequest shortedURLRequest = new ShortedURLRequest();
			shortedURLRequest.setUrl("https://www.google.com/");
			urlShortnerService.createShortedURL(shortedURLRequest);
			assertTrue(true);

		} catch (Exception e) {
			assert (false);
		}
	}

	@Test
	public void testCreateShortedURLInvalidURL() throws Exception {
		try {
			ShortedURLRequest shortedURLRequest = new ShortedURLRequest();
			shortedURLRequest.setUrl("www.google.com");
			urlShortnerService.createShortedURL(shortedURLRequest);
			assertTrue(false);

		} catch (InvalidInputException e) {
			assert (true);
		}
	}

	@Test
	public void testGetUrlByShortedURL() throws Exception {
		try {
			ShortedURLRequest shortedURLRequest = new ShortedURLRequest();
			shortedURLRequest.setUrl("https://www.google.com/");
			ShortedURL shortedURL = urlShortnerService.createShortedURL(shortedURLRequest);
			ActualURL actualURL = urlShortnerService.getUrlByShortedURL(shortedURL.getShortedURL());
			assertTrue(shortedURLRequest.getUrl().equalsIgnoreCase(actualURL.getActualURL()));

		} catch (Exception e) {
			assert (false);
		}
	}

	@Test
	public void testGetUrlByShortedURLWhenNotFound() throws Exception {
		try {
			ActualURL actualURL = urlShortnerService.getUrlByShortedURL("vgfyvcfycvf");

			assertTrue(false);

		} catch (NotFoundException e) {
			assert (true);
		}
	}
}
