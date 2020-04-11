package sb.springboot.es.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sb.springboot.es.model.Customer;
import sb.springboot.es.service.CustomerService;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping("/saveCustomer")
	public ResponseEntity<Integer> saveCustomer(@RequestBody List<Customer> customers) {
		customerService.saveAll(customers);
		return new ResponseEntity<>(customers.size(), HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Iterable<Customer>> getAll() {
		return new ResponseEntity<Iterable<Customer>>(customerService.findAll(), HttpStatus.OK);
	}

	@GetMapping("findByName/{firstName}")
	public ResponseEntity<List<Customer>> findByName(@PathVariable String firstName) {
		List<Customer> customer = customerService.findByFirstName(firstName);
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);

	}

}
