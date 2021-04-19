package com.hpf.kafkaserver.Controller;

import com.hpf.kafkaserver.Server.kafkaServes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName kafkaController
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/19  9:36
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/kafkaServer")
public class kafkaController {

    @Autowired
    private kafkaServes kafkaServers;

    @GetMapping(value = "/send")
    @Transactional(rollbackFor =  Exception.class)
    public String sendMsg(){
        kafkaServers.send("this is kafka topic message");
        return "消息中间件调用成功~！";
    }
}
