package com.hpf.activiti.Services;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName ActivitiServices
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/18  20:21
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ActivitiServices")
public class ActivitiServices {

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

    @RequestMapping(value = "/getUserList")
    public void deploymentProcesses_zip() throws IOException {

        Deployment deploy = repositoryService.createDeployment()//创建一个部署的对象
                .name("test")//创建流程名称
                .addClasspathResource("processes/test.bpmn20.xml")//指定zip完成部署
                .key("plan1")
                .deploy();
        System.out.println("部署id" + deploy.getId());
        System.out.println("部署名称" + deploy.getName());
        System.out.println("key" + deploy.getKey());

    }

    /**
     * 启动流程
     * RuntimeService
     */
    @RequestMapping(value = "/startProcess")
    public void startProcess() {
        //可根据id、key、message启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test");
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
        System.out.println(processInstance.getId());
    }

    /**
     * 查看任务
     * TaskService
     */
    @RequestMapping(value = "/queryTask")
    public void queryTask() {
        TaskService taskService = processEngine.getTaskService();
        //根据assignee(代理人)查询任务
        String assignee = "emp2";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);

        }
        for (Task task : tasks) {
            System.out.println("taskId:" + task.getId() +
                    ",taskName:" + task.getName() +
                    ",assignee:" + task.getAssignee() +
                    ",createTime:" + task.getCreateTime());
        }
    }

    @RequestMapping(value = "/handleTask")
    public void handleTask() {
        TaskService taskService = processEngine.getTaskService();
        //根据上一步生成的taskId执行任务
        String taskId = "2502";
        taskService.complete(taskId);
    }

    @RequestMapping(value = "/findHistoryTask")
    public void findHistoryTask() {
        String taskAssignee = "emp2";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()// 与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()// 创建历史任务实例查询
                .taskAssignee(taskAssignee)// 指定历史任务的办理人
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance hti : list) {
                System.out.println(hti.getId() + "    " + hti.getName() + "    " + hti.getProcessInstanceId() + "   "
                        + hti.getStartTime() + "   " + hti.getEndTime() + "   " + hti.getDurationInMillis());
                System.out.println("################################");
            }
        }
    }
}
