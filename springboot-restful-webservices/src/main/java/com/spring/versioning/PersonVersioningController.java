package com.spring.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personv1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personv2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	/* Request Param */
	
	@GetMapping(value = "/person/param" , params = "version1")
	public PersonV1 param1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/param" , params = "version2")
	public PersonV2 param2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	
	/* Header version */
	
	@GetMapping(value = "/person/header" , headers = "X-API-VERSION=1")
	public PersonV1 header1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/header" , headers = "X-API-VERSION=2")
	public PersonV2 header2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	
	/* Produces or Accept */
	
	@GetMapping(value = "/person/produces" , produces = "application/company-name-v1+json")
	public PersonV1 produces1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value = "/person/produces" , produces = "application/company-name-v2+json")
	public PersonV2 produces2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
}
