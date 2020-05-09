package com.rudy.elasticsearch.elasticsearch;

import java.util.List;

/**
 * <p>ClassName: CurdIndex</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2020/5/9
 * @since JDK 1.8
 */
public interface CurdIndex<T> {

    void insert(T t);

    void insert(List<T> list);

}
