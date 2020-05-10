package com.rudy.repository;

import com.rudy.entity.HotelResource;
import org.springframework.data.jpa.repository.JpaRepository;

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
