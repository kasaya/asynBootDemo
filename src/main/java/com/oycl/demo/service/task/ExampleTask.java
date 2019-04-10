package com.oycl.demo.service.task;

import com.oycl.demo.common.async.RunnableService;
import com.oycl.demo.common.base.BusinessLogic;
import com.oycl.demo.orm.dao.MTaskMapper;
import com.oycl.demo.orm.model.MTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class ExampleTask extends RunnableService {

    private final BusinessLogic logic;

    public ExampleTask(BusinessLogic logic){
        this.logic = logic;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void run() {
        logic.doLogic(this.getInfo());
        this.removeInfo();
    }
}
