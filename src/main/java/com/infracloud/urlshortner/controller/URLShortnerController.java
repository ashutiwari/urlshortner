package com.infracloud.urlshortner.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infracloud.urlshortner.entity.ActualURL;
import com.infracloud.urlshortner.entity.ShortedURL;
import com.infracloud.urlshortner.exceptions.InvalidInputException;
import com.infracloud.urlshortner.exceptions.NotFoundException;
import com.infracloud.urlshortner.model.ShortedURLRequest;
import com.infracloud.urlshortner.service.URLShortnerService;


/**
 * @author ashutiwari
 *
 */

@RestController
@CrossOrigin(origins = "*")
public class URLShortnerController {
	
	private static final Logger logger = LoggerFactory.getLogger(URLShortnerController.class);

	@Autowired
	private URLShortnerService urlShortnerService;
	
	/**
	 * Get Url By Shorted URL
	 * @param shorted_url shorted_url
	 * @throws IOException IOException
	 * @throws NotFoundException NotFoundException
	 */
	@GetMapping("get_actual_url_by_shorted_url")
	public ActualURL getUrlByShortedURL(HttpServletResponse response, @RequestParam("shortedURL") String shortedURL) throws IOException{
		try {
			logger.info("Received shorted_url:"+ shortedURL);
			response.setStatus(HttpServletResponse.SC_OK);
			return urlShortnerService.getUrlByShortedURL(shortedURL);
		}catch (NotFoundException e) {
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
		}
		return null;
	}
	
	
	
	/**
	 * Create Shorted URL
	 * @param shortedURLRequest Shorted URL
	 * @throws IOException IOException
	 * @throws InvalidInputException InvalidInputException
	 */
	@PostMapping("/crete_short_url")
	public ShortedURL shortedURL(HttpServletResponse response, @RequestBody ShortedURLRequest shortedURLRequest) throws IOException{
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			return urlShortnerService.createShortedURL(shortedURLRequest);
		}catch (InvalidInputException e) {
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		return null;
	}
}
