package com.hpf.providerser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProviderserController
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/11  16:00
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/provider")
public class ProviderserController {

    @RequestMapping(value="/getUserList")
    public List<String> getUserList(){
        log.info("开始调用start");
        List<String> result=new ArrayList<>();
        result.add("test1");
        result.add("test2");
        result.add("test3");
        result.add("test4");
        result.add("test5");
        result.add("test6");
        log.info("调用end");
        return  result;
    }
}
