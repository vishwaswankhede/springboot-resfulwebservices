package com.spring.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retiveSomeBean() {
		
		return new SomeBean("value1", "value2", "value3");
	}
	
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retiveSomeBeanList() {
		
		return Arrays.asList(new SomeBean("value11", "value12", "value13"),
				new SomeBean("value21", "value22", "value23") );
	}
	
	@GetMapping("/home")
	public SomeBean retiveHome() {
		
		return new SomeBean("value1", "value2", "value3");
	}

}
