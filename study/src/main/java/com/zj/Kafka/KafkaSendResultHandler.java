package com.zj.Kafka;//package com.Kafka;
//
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.support.ProducerListener;
//import org.springframework.stereotype.Component;
//
///**
// * 这里要注意ProducerListener版本问题，spring-kafka依赖要引入合适版本
// * */
//@Component
//public class KafkaSendResultHandler implements ProducerListener {
//
//    private static final Logger log = LoggerFactory.getLogger(KafkaSendResultHandler.class);
//
//    @Override
//    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
//        log.info("===================Message send success : " + producerRecord.toString());
//    }
//
//    @Override
//    public void onError(ProducerRecord producerRecord, Exception exception) {
//        log.info("========================================Message send error : " + producerRecord.toString());
//    }
//}