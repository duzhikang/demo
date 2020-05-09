package com.rudy.elasticsearch.entity;

import javax.persistence.*;

/**
 * <p>ClassName: HotelResource</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/9
 * @since JDK 1.8
 */
@Entity
@Table(name = "web_resource_hotel")
public class HotelResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hotel_type")
    private String hotelType;

    @Column(name = "address")
    private String address;

    @Column(name = "openYear")
    private Integer openYear;

    @Column(name = "score")
    private float score;

    @Column(name = "content")
    private String content;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "location_detail")
    private String location_detail;

    @Column(name = "traffic_info_html")
    private String trafficInfoHtml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOpenYear() {
        return openYear;
    }

    public void setOpenYear(Integer openYear) {
        this.openYear = openYear;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocation_detail() {
        return location_detail;
    }

    public void setLocation_detail(String location_detail) {
        this.location_detail = location_detail;
    }

    public String getTrafficInfoHtml() {
        return trafficInfoHtml;
    }

    public void setTrafficInfoHtml(String trafficInfoHtml) {
        this.trafficInfoHtml = trafficInfoHtml;
    }
}
