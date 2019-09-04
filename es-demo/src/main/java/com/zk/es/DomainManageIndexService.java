package com.zk.es;

import com.zk.bo.DocDomainManage;

/**
 * <p>ClassName: DomainManageIndexService</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/4
 * @since JDK 1.8
 */
public interface DomainManageIndexService {

    /**
     * 索引管理的域名
     * @param houseId
     */
    void index(DocDomainManage doc);

    /**
     * 删除索引
     * @param doc
     */
    void deleteIndex(Long id);

    /**
     * 更新索引
     * @param doc
     */
    void updateIndex(DocDomainManage doc);
}
