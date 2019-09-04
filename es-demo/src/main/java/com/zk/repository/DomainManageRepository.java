package com.zk.repository;

import com.zk.entity.DomainManage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * <p>ClassName: DomainManageRepository</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/4
 * @since JDK 1.8
 */
public interface DomainManageRepository extends CrudRepository<DomainManage, Long> {

    @Override
    Iterable<DomainManage> findAll();

    @Override
    Optional<DomainManage> findById(Long id);
}
