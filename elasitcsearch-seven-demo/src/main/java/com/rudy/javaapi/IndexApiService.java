package com.rudy.javaapi;

import com.rudy.entity.HotelResource;
import com.rudy.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>ClassName: TestService</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/4/22
 * @since JDK 1.8
 */
@Slf4j
@Service
public class IndexApiService {

    @Resource
    private RestHighLevelClient client;

    @Resource
    private IHotelService hotelService;

    public static AtomicInteger count = new AtomicInteger(0);
    
    /**
     * @Param 
     * @Description 
     * @Author zhikang.du
     * @Date 2020/4/22
     * @return 
     **/
    public void indexDocument() {
        long count = hotelService.count();
        long totalPages = count%1000 == 0? count/1000 : count/1000 + 1;
        long lastPageCount = count%1000 == 0? 1000 : count%1000;
        for (int i = 0; i < totalPages; i++) {
            List<HotelResource> hotelResources = hotelService.pageQuery(0 * 1000, 1000);
            for (HotelResource resource : hotelResources) {
                try {
                    IndexRequest indexRequest = new IndexRequest( "hotel", "_doc")
                            .source(XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("id", resource.getId())
                                    .field("name", resource.getName())
                                    .field("hotel_type", resource.getHotelType())
                                    .field("address", resource.getAddress())
                                    .field("open_year", resource.getOpenYear())
                                    .field("score", resource.getScore())
                                    .field("content", resource.getContent())
                                    .field("city_name", resource.getCityName())
                                    .field("province_name", resource.getProvinceName())
                                    .field("area_name", resource.getAreaName())
                                    .startObject("location")
                                    .field("lat", resource.getLatitude())
                                    .field("lon", resource.getLongitude())
                                    .endObject()
                                    .field("location_detail", resource.getLocation_detail())
                                    .field("traffic_info_html", resource.getTrafficInfoHtml())
                                    .endObject()
                            );
                    IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
                }catch (Exception e) {
                    System.out.println("error");
                }

            }
        }
    }

    /**
     * 批量操作
     */
    public void bulkDoc() {
        long count = hotelService.count();
        long totalPages = count%100 == 0? count/100 : count/100 + 1;
        // 最后一页的页数
        long lastPageCount = count%100 == 0? 100 : count%100;
        for (int i = 0; i < totalPages; i++) {
            BulkRequest request = new BulkRequest();
            List<HotelResource> hotelResources = hotelService.pageQuery(i , 100);
            System.out.println("size" + hotelResources.size());
            for (HotelResource resource : hotelResources) {
                try {
                    IndexRequest indexRequest = new IndexRequest("hotel", "_doc")
                            .source(XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("id", resource.getId())
                                    .field("name", resource.getName())
                                    .field("hotel_type", resource.getHotelType())
                                    .field("address", resource.getAddress())
                                    .field("open_year", resource.getOpenYear())
                                    .field("score", resource.getScore())
                                    .field("content", resource.getContent())
                                    .field("city_name", resource.getCityName())
                                    .field("province_name", resource.getProvinceName())
                                    .field("area_name", resource.getAreaName())
                                    .startObject("location")
                                    .field("lat", resource.getLatitude())
                                    .field("lon", resource.getLongitude())
                                    .endObject()
                                    .field("location_detail", resource.getLocation_detail())
                                    .field("traffic_info_html", resource.getTrafficInfoHtml())
                                    .endObject()
                            );
                    request.add(indexRequest);
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
            BulkResponse bulk = null;
            try {
                bulk = client.bulk(request, RequestOptions.DEFAULT);
                // System.out.println(bulk.getTook());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
