package com.hpf.consumerser.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName providerserServer
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/11  16:18
 * @Version 1.0
 **/
@FeignClient(value="providerser")
public interface ProviderserServer {

    //这里写要调用的远程服务接口
    @RequestMapping(value = "/provider/getUserList",method = RequestMethod.POST)
    List<String> getFeignList();
}
