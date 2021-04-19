package com.hpf.kafkaserver.Server;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Header;

import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

/**
 * @ClassName kafkaConsumer
 * @Description TODO
 * @Author hepengfei
 * @Date 2021/4/11  13:35
 * @Version 1.0
 **/
@Component
@Slf4j
public class kafkaConsumer {

    @KafkaListener(topics = kafkaServes.TOPIC_TEST, groupId = kafkaServes.TOPIC_GROUP1)
    public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info(("topic_test 消费了： Topic:" + topic + ",Message:" + msg));
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = kafkaServes.TOPIC_TEST, groupId = kafkaServes.TOPIC_GROUP2)
    public void topic_test1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info(("topic_test1 消费了： Topic:" + topic + ",Message:" + msg));
            ack.acknowledge();
        }
    }





















    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.164.131:9092");
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("myTopic"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println("=====================>");
            }
//            records.forEach(reco ->{
//                reco.offset()
//            });

        }

    }
}
