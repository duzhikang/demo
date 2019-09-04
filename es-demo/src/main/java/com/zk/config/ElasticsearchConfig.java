package com.zk.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>ClassName: ElasticsearchConfig</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/8/26
 * @since JDK 1.8
 */
@Slf4j
@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.cluster.name}")
    private String esName;

    @Bean
    public TransportClient esClient() {
        System.out.println(esHost + esName + esPort);
        TransportClient client = null;
        Settings settings = Settings.builder()
                .put("cluster.name", this.esName)
                .put("client.transport.sniff", true) //启用嗅探
                .build();
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(this.esHost), this.esPort));

        } catch (UnknownHostException e) {
            log.error("create TransportClient id Error");
        }
        return client;

    }
}
