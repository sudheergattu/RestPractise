package com.practise.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	@Autowired
	MessageSource messageSource;
	
	@GetMapping(path="/helloworld")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path="/good-morning-i18n")
	public String goodMorningI18n() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
