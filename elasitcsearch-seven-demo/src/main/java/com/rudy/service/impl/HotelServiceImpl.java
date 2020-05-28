package com.rudy.service.impl;

import com.rudy.entity.HotelResource;
import com.rudy.repository.HotelResourceRepository;
import com.rudy.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>ClassName: HotelServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/9
 * @since JDK 1.8
 */
@Slf4j
@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private HotelResourceRepository repository;

    @Override
    public long count() {
        long count = repository.count();
        return count;
    }

    @Override
    public List<HotelResource> pageQuery(int start, int length) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, length);
        Page<HotelResource> all = repository.findAll(pageable);
        List<HotelResource> list = all.stream().map(house -> {
            return house;
        }).collect(Collectors.toList());
        return list;
    }
}
