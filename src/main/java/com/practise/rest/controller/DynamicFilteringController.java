package com.practise.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.practise.rest.model.DynamicFilterBean;

@RestController
public class DynamicFilteringController {
	@GetMapping(path = "/dynamic-filtering")
	MappingJacksonValue dynamicFilterBean() {
		DynamicFilterBean dynamicFilterBean = new DynamicFilterBean("value1", "value2", "value3");
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", simpleBeanPropertyFilter);
		MappingJacksonValue value =new MappingJacksonValue(dynamicFilterBean);
		value.setFilters(filters);
		return value;
	}

	@GetMapping(path = "/dynamic-filtering-list")
	MappingJacksonValue dynamicFilterListBean() {
		
		List<DynamicFilterBean> dynamicFilterBeanList = Arrays.asList(new DynamicFilterBean("value1", "value2", "value3"),
				new DynamicFilterBean("value11", "value12", "value13"));
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", simpleBeanPropertyFilter);
		MappingJacksonValue value =new MappingJacksonValue(dynamicFilterBeanList);
		value.setFilters(filters);
		return value ;
	}
}
