package com.infracloud.urlshortner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.infracloud.urlshortner.service.URLShortnerService;
import com.infracloud.urlshortner.utils.Constant;

@SpringBootApplication
public class UrlshortnerApplication {
	private static final Logger logger = LoggerFactory.getLogger(URLShortnerService.class);

	public static void main(String[] args) {
		SpringApplication.run(UrlshortnerApplication.class, args);
		logger.info(Constant.APPNAME + "Started Successfully");  
	}

}
