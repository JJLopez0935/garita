package pe.com.pucp.dp2.api.upload.elasticsearch;

import com.google.common.base.Predicates;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.client.Client;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;



import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "pe.com.pucp.dp2.api.upload.elasticsearch.repository")
//@ComponentScan(basePackages = "pe.com.hiper.bmatic.wssimulator.simulacion",
//                basePackageClasses = Simulador.class)
public class AppConfig {

    @Autowired
    private Environment env;

    @Value("${elasticsearch.host}")
    private String EsHost;
    @Value("${elasticsearch.port}")
    private int EsPort;
    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Bean
    public Client client() throws Exception {
        return new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

//
//    @Bean
//    public TomcatServletWebServerFactory containerFactory() {
//        return new TomcatServletWebServerFactory() {
//            protected void customizeConnector(Connector connector) {
//                String ms = String.valueOf(env.getProperty("spring.servlet.multipart.max-request-size"));
//                int maxSize = Integer.parseInt(ms.substring(0, ms.length()-2)) * 1000;
//                super.customizeConnector(connector);
//                connector.setMaxPostSize(maxSize);
//                connector.setMaxSavePostSize(maxSize);
//                if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
//
//                    ((AbstractHttp11Protocol <?>) connector.getProtocolHandler()).setMaxSwallowSize(maxSize);
//                    logger.info("Set MaxSwallowSize "+ maxSize);
//                }
//            }
//        };
//
//    }



}
