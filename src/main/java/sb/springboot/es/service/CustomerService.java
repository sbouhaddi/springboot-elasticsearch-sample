package sb.springboot.es.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sb.springboot.es.model.Customer;
import sb.springboot.es.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	public void saveAll(List<Customer> customers) {
		customerRepository.saveAll(customers);
	}

	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	public List<Customer> findByFirstName(String firstName) {
		return customerRepository.findByFirstName(firstName);
	}

}
