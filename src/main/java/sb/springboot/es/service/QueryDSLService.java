package sb.springboot.es.service;

import java.util.List;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sb.springboot.es.model.Customer;

@Service
@RequiredArgsConstructor
public class QueryDSLService {

	private final ElasticsearchTemplate elasticsearchTemplate;

	public List<Customer> searchMultiField(String firstName, int age) {
		QueryBuilder builder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("firstName", firstName))
				.must(QueryBuilders.matchQuery("age", age));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
		return elasticsearchTemplate.queryForList(nativeSearchQuery, Customer.class);
	}

	public List<Customer> searchFirstName(String firstName) {
		QueryBuilder builder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("firstName", firstName));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
		return elasticsearchTemplate.queryForList(nativeSearchQuery, Customer.class);
	}

	public List<Customer> getCustomerSearchData(String input) {
		String search = ".*" + input + ".*";
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.regexpQuery("firstName", search)).build();
		return elasticsearchTemplate.queryForList(searchQuery, Customer.class);

	}

	public List<Customer> multiMatchQuery(String input) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(input)
				.field("firstName").field("lastName").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
		return elasticsearchTemplate.queryForList(searchQuery, Customer.class);
	}

}
