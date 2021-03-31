package com.infracloud.urlshortner.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infracloud.urlshortner.entity.ShortedURL;
import com.infracloud.urlshortner.service.URLShortnerService;


@RestController
@RequestMapping("/shorted_url")
@CrossOrigin(origins = "*")
public class URLShortnerController {
	
	private static final Logger logger = LoggerFactory.getLogger(URLShortnerController.class);

	@Autowired
	private URLShortnerService urlShortnerService;
	
	/**
	 * get shorted url
	 * @param url url
	 * @throws IOException IOException
	 */
	@GetMapping("/{url}")
	public ShortedURL getShortedURL(HttpServletResponse response, @PathVariable("url") String url) throws IOException{
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			return urlShortnerService.getShortedURL(url);
		}catch (Exception e) {
			logger.error(e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
		return null;
	}

}
