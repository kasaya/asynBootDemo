package com.oycl.demo.orm.dao;


import com.oycl.demo.common.log.annotation.CustomLog;
import com.oycl.demo.orm.model.MTask;

/**
 * <p>
 * CODE定义 Mapper 接口
 * </p>
 *
 * @author cango
 * @since 2019-01-15
 */
@CustomLog
public interface MTaskMapper {

    void insert(MTask param);

    int select();

}
