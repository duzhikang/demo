package com.rudy.service;

import com.rudy.entity.HotelResource;

import java.util.List;

/**
 * <p>ClassName: IHotelService</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/9
 * @since JDK 1.8
 */
public interface IHotelService {

    long count();

    List<HotelResource> pageQuery(int start, int length);
}
