package sb.springboot.es.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sb.springboot.es.model.Customer;
import sb.springboot.es.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapData implements CommandLineRunner {

	private final CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		if (customerRepository.count() < 1) {
			Customer customer = Customer.builder().firstName("firstName").lastName("lastName").age(22).build();
			customerRepository.save(customer);

			Customer customer2 = Customer.builder().firstName("firstName").lastName("lastName").age(22).build();
			customerRepository.save(customer2);

			Customer customer3 = Customer.builder().firstName("firstName").lastName("lastName").age(22).build();
			customerRepository.save(customer3);

			Customer customer4 = Customer.builder().firstName("firstName").lastName("lastName").age(22).build();
			customerRepository.save(customer4);

			log.info("saved Customers" + customerRepository.count());
		}

	}

}
