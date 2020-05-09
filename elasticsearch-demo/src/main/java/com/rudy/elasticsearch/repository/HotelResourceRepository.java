package com.rudy.elasticsearch.repository;

import com.rudy.elasticsearch.entity.HotelResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * <p>ClassName: HotelResourceRepository</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/9
 * @since JDK 1.8
 */
public interface HotelResourceRepository extends JpaRepository<HotelResource, Long> {

}
