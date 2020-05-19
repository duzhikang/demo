package com.rudy.elasticsearch.javaapi;

import com.rudy.elasticsearch.entity.HotelResource;
import com.rudy.elasticsearch.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private TransportClient client;

    @Resource
    private IHotelService hotelService;

    public static AtomicInteger count = new AtomicInteger(0);

    public void bulkIndexDocment() {
        long count = hotelService.count();
        long totalPages = count%1000 == 0? count/1000 : count/1000 + 1;
        long lastPageCount = count%1000 == 0? 1000 : count%1000;
        for (int i = 0; i < totalPages; i++) {
            List<HotelResource> hotelResources = hotelService.pageQuery(i * 100 , 100);
            for (HotelResource resource : hotelResources) {
                try {
                    IndexResponse response = client.prepareIndex("hotel", "_doc")
                            .setSource(XContentFactory.jsonBuilder()
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
                            ).get();
                }catch (Exception e) {
                    log.error("error ======{}", resource.getId());
                }

            }
        }
    }
    
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
                    IndexResponse response = client.prepareIndex("hotel", "_doc")
                            .setSource(XContentFactory.jsonBuilder()
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
                            )
                            .get();
                }catch (Exception e) {
                    log.error("error ======{}", resource.getId());
                }

            }
        }
        try {
            IndexResponse response = client.prepareIndex("twitter", "_doc", "1")
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("user", "kimchy")
                            .field("postDate", new Date())
                            .field("message", "trying out Elasticsearch")
                            .endObject()
                    )
                    .get();
            log.info("{}, {}, {}, {}, {}", response.getIndex(), response.getType(),
                    response.getId(), response.getVersion(), response.status());
        }catch (Exception e) {

        }
    }
}
