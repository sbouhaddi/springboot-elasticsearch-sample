package sb.springboot.es.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "sb.springboot.es.repository")
public class EsConfig extends ElasticsearchConfigurationSupport {

	private final String EsHost;

	private final int EsPort;

	private final String EsClusterName;

	public EsConfig(@Value("${elasticsearch.host}") String esHost, @Value("${elasticsearch.port}") int esPort,
			@Value("${elasticsearch.clustername}") String esClusterName) {
		super();
		EsHost = esHost;
		EsPort = esPort;
		EsClusterName = esClusterName;
	}

	@Bean
	public Client elasticsearchClient() throws UnknownHostException {
		Settings settings = Settings.builder().put("cluster.name", EsClusterName).build();
		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(new TransportAddress(InetAddress.getByName(EsHost), EsPort));
		return client;
	}

	@Bean(name = { "elasticsearchOperations", "elasticsearchTemplate" })
	public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
		return new ElasticsearchTemplate(elasticsearchClient());
	}
}
