package sb.springboot.es.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import sb.springboot.es.model.Customer;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

	List<Customer> findByFirstName(String firstName);

}
