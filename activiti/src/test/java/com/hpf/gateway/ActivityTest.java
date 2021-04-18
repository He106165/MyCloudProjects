package com.hpf.gateway;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @ClassName ActivityTest
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/18  20:26
 * @Version 1.0
 **/
public class ActivityTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private HistoryService historyService;

    @Test
    public void deploymentProcesses_zip() throws IOException {

        Deployment deploy = repositoryService.createDeployment()//创建一个部署的对象
                .name("test")//创建流程名称
                .addClasspathResource("processes/test.bpmn20.zip")//指定zip完成部署
                .deploy();
        System.out.println("部署id" + deploy.getId());
        System.out.println("部署名称" + deploy.getName());

    }
}
