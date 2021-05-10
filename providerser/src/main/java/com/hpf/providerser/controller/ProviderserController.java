package com.hpf.providerser.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @SentinelResource(value = "getUserList",blockHandler = "exceptionHandler", fallback = "fallback")
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
    public String exceptionHandler(BlockException be) {
        be.printStackTrace();
        System.out.println("异常getUserList");
        return "异常getUserList";
    }

    public String fallback() {
        System.out.println("降级getUserList");
        return "降级getUserList";
    }

    public static void main(String[] args) {
        long num = chengeNum(100L,(x) ->x+200L);
        List<Integer> list = new ArrayList<>();
        list.stream().distinct().skip(4).limit(1).sorted((x,y)->{
            if(x>y) return x.compareTo(y);
            else return y.compareTo(x);
        });
        List<person> list2 = new ArrayList<>();
        list2.add(new person("aaa","nam1"));
        list2.add(new person("bbb","nam2"));
        Map<String, String> collect = list2.stream().collect(Collectors.toMap(person::getId, person::getName, (key1, key2) -> key2));
    }

    public static long chengeNum(long num1 , Function<Long,Long> fun){

        return fun.apply(num1);
    }
    static  class  person{
        private String id;
        private String name;
        person(String id,String name){}

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
