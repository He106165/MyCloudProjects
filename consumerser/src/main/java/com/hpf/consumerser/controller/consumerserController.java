package com.hpf.consumerser.controller;

import com.hpf.consumerser.services.ProviderserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName consumerserController
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/11  16:19
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/remoteApi")
@RefreshScope
public class consumerserController {

    @Autowired
    private ProviderserServer providerserServer;

    @Value("${useLocalCache}")
    private String useLocalCache;

    @RequestMapping("/getFeignList")
    public List<String> getFeignList(){
        return providerserServer.getFeignList();
    }
    @RequestMapping("/getLocalCache")
    public String getConfigSet(){
        return useLocalCache;
    }
}
