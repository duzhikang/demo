package com.rudy.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ClassName: ElasticsearchConfig</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/4/22
 * @since JDK 1.8
 */
@Slf4j
@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.cluster.name}")
    private String esName;

    /**
     * 协议
     */
    @Value("${elasticsearch.schema}")
    private String schema;

    /** 连接超时时间 */
    @Value("${elasticsearch.connectTimeout}")
    private int connectTimeout;

    /** Socket 连接超时时间 */
    @Value("${elasticsearch.socketTimeout}")
    private int socketTimeout;

    /** 获取连接的超时时间 */
    @Value("${elasticsearch.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    /** 最大连接数 */
    @Value("${elasticsearch.maxConnectNum}")
    private int maxConnectNum;

    /** 最大路由连接数 */
    @Value("${elasticsearch.maxConnectPerRoute}")
    private int maxConnectPerRoute;

    @Bean
    public RestHighLevelClient esClient() {
        // 拆分地址
        List<HttpHost> hostLists = new ArrayList<>();
        String[] hostList = esHost.split(",");
        for (String addr : hostList) {
            String host = addr.split(":")[0];
            String port = addr.split(":")[1];
            hostLists.add(new HttpHost(host, Integer.parseInt(port), schema));
        }

        // 转换成 HttpHost 数组
        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
        // 构建连接对象
        RestClientBuilder builder = RestClient.builder(httpHost);
        // 异步连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeout);
            requestConfigBuilder.setSocketTimeout(socketTimeout);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
            return requestConfigBuilder;
        });
        // 异步连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }

}
