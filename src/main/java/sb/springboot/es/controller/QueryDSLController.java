package sb.springboot.es.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sb.springboot.es.model.Customer;
import sb.springboot.es.service.QueryDSLService;

@RestController
public class QueryDSLController {

	@Autowired
	private QueryDSLService service;

	@GetMapping("/serachMultiField/{firstname}/{age}")
	public List<Customer> serachByMultiField(@PathVariable String firstname, @PathVariable int age) {
		return service.searchMultiField(firstname, age);
	}

	@GetMapping("/customSearch/{firstName}")
	public List<Customer> getCustomerByField(@PathVariable String firstName) {
		return service.getCustomerSearchData(firstName);
	}

	@GetMapping("/search/{text}")
	public List<Customer> doMultimatchQuery(@PathVariable String text) {
		return service.multiMatchQuery(text);
	}

}