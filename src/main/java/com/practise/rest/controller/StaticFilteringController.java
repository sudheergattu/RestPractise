package com.practise.rest.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.rest.model.StaticFilterBean;

@RestController
public class StaticFilteringController {
	@GetMapping(path = "/static-filtering")
	StaticFilterBean staticFilterBean() {
		return new StaticFilterBean("value1", "value2", "value3");
	}

	@GetMapping(path = "/static-filtering-list")
	List<StaticFilterBean> staticFilterListBean() {
		return  Arrays.asList(new StaticFilterBean("value1", "value2", "value3"),
				new StaticFilterBean("value11", "value12", "value13"));
	}
}
