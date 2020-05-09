package com.rudy.elasticsearch.elasticsearch.impl;

import com.rudy.elasticsearch.elasticsearch.CurdIndex;
import com.rudy.elasticsearch.entity.HotelResource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>ClassName: HotelCurdIndexImpl</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/9
 * @since JDK 1.8
 */
@Service
public class HotelCurdIndexImpl implements CurdIndex<HotelResource> {



    @Override
    public void insert(HotelResource hotelResource) {

    }

    @Override
    public void insert(List<HotelResource> list) {

    }
}
