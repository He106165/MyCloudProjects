package com.hpf.kafkaserver.Server;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName kafkaServes
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/11  13:33
 * @Version 1.0
 **/
@Component
@Slf4j
public class kafkaServes {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //自定义topic
    public static final String TOPIC_TEST = "topic.test";

    //
    public static final String TOPIC_GROUP1 = "topic.group1";

    //
    public static final String TOPIC_GROUP2 = "topic.group2";

    public void send(Object obj) {
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });

    }






    public static void main(String[] args){
//        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "192.168.164.131:9092");
//        properties.put("acks", "all");
//        properties.put("retries", 0);
//        properties.put("batch.size", 16384);
//        properties.put("linger.ms", 1);
//        properties.put("buffer.memory", 33554432);
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        Producer<String, String> producer = null;
//        try {
//            producer = new KafkaProducer<String, String>(properties);
////            producer.send(new ProducerRecord<String, String>("HelloWorld","aaaaaaaaaaaa"));
//            for (int i = 0; i < 100; i++) {
//                String msg = "This is Message " + i;
//                producer.send(new ProducerRecord<String, String>("myTopic", msg));
//                System.out.println("Sent:" + msg);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        } finally {
//            producer.close();
//
        Map<String,Integer> identityMap = new IdentityHashMap<>();
        identityMap.put(new String("a"),2);
        identityMap.put(new String("a"),1);
        identityMap.put("a",3);
        identityMap.put("b",2);
        System.out.println(identityMap.size());
        System.out.println(identityMap.get("a"));
    }
}
