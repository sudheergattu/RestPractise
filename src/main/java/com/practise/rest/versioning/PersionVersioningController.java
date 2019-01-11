package com.practise.rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersionVersioningController {
	/************** URI Versioning
	// Twitter
	 * This causes URI pollution
	 * *****************/

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Sudheer Gattu");
	}

	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Sudheer", "Gattu"));
	}

	/************** Request param versioning
	// amazon 
	 * This causes URI pollution
	 * 
	 * **************/

	// http://localhost:8080/person/param?version=1
	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Sudheer Gattu");
	}

	// http://localhost:8080/person/param?version=2
	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Sudheer", "Gattu"));
	}

	/************** Headers Versioning
	// Microsoft 
	 * This is misuse of http headers
	 * 
	 * /**************/

	// http://localhost:8080/person/header
	// In Header X-API-VERSION=1
	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Sudheer Gattu");
	}

	// http://localhost:8080/person/header
	// In Header X-API-VERSION=2
	@GetMapping(value = "person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Sudheer", "Gattu"));
	}

	/**************  Media type versioning ( "content negotiation" or "accept header"
	// Used by Github/*
	 * this is misuse of http headers
	 * *************/
	//
	// http://localhost:8080/person/header
	// In Header Accept = application/vnd.company.app-v1+json
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Sudheer Gattu");
	}

	// http://localhost:8080/person/header
	// In Header Accept = application/vnd.company.app-v1+json
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Sudheer", "Gattu"));
	}

}
