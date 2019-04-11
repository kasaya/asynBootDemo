package com.oycl.demo.common.async;

/**
 * 业务逻辑实现接口
 *
 * @author oycl
 */
@FunctionalInterface
public interface IBusinessLogic {

  void doLogic(TaskInfo info);
}
