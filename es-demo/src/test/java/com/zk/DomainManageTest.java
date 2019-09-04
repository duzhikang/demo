package com.zk;

import com.zk.bo.DocDomainManage;
import com.zk.entity.DomainManage;
import com.zk.es.DomainManageIndexService;
import com.zk.repository.DomainManageRepository;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * <p>ClassName: DomainManageTest</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/4
 * @since JDK 1.8
 */
public class DomainManageTest extends ApplicationTest {

    @Autowired
    private DomainManageRepository manageRepository;

    @Autowired
    private DomainManageIndexService indexService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void findall() {
        Iterable<DomainManage> all = manageRepository.findAll();
        for (DomainManage domain : all) {
            DocDomainManage doc = new DocDomainManage();
            modelMapper.map(domain, doc);
            indexService.index(doc);
        }
    }

    @Test
    public void delete() {
        indexService.deleteIndex(3321102118895872L);
    }

    @Test
    public void update() {
        Optional<DomainManage> optional = manageRepository.findById(3321403701756160L);
        if (optional.isPresent()) {
            DomainManage domainManage = optional.get();
            domainManage.setIyongStatus(9);
            domainManage.setAuthStatus(9);
            domainManage.setAnalysisStatus(9);
            domainManage.setRecordStatus(9);
            DocDomainManage doc = new DocDomainManage();
            modelMapper.map(domainManage, doc);
            indexService.updateIndex(doc);
        }

    }
}
