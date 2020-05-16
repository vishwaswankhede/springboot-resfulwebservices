package com.spring.filtering.dynamic;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilteringController {

	// field1,field2
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retiveDynamicSomeBean() {
		SomeBeanDynamic someBeanDynamic = new SomeBeanDynamic("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBeanDynamic);

		mapping.setFilters(filters);
		return mapping;
	}

	// field2, field3
	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue retiveDynamicSomeBeanList() {

		List<SomeBeanDynamic> beanList = Arrays.asList(new SomeBeanDynamic("value11", "value12", "value13"),
				new SomeBeanDynamic("value21", "value22", "value23"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(beanList);
		mapping.setFilters(filters);
		
		return mapping;
		
	}

}
