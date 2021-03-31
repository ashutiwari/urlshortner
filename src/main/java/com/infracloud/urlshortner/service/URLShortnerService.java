package com.infracloud.urlshortner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.infracloud.urlshortner.entity.ShortedURL;


@Service
public class URLShortnerService {
	private static final Logger logger = LoggerFactory.getLogger(URLShortnerService.class);

	public ShortedURL getShortedURL(String url) {

		ShortedURL shortedURL = new ShortedURL();
		shortedURL.setShortedURL(url);
		return shortedURL;
		
	}

}
