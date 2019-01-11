package com.practise.rest.component;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstComponent {
	@GetMapping(path="/hello")
	public String getString() {
		return "Hello World1";
	}
	
	@GetMapping(path="/hellobean")
	public HelloBean getBean() {
		return new HelloBean("Test Bean");
	}
	@GetMapping(path="/hellobean/{input}")
	public HelloBean getBeanById(@PathVariable String input) {
		return new HelloBean(input); 	
	}

}
