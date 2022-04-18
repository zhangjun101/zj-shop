package com.zj.Kafka;//package com.Kafka;
//
//import com.SpringbootApplication;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * 主题监听
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {SpringbootApplication.class})
//public class TopicListenerTest {
//
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    @Autowired
//    private KafkaSendResultHandler producerListener;
//
//    /**
//     * 测试 Bean方式创建监听容器
//     * @throws InterruptedException
//     */
//    @Test
//    @Transactional
//    public void test() throws InterruptedException {
//        kafkaTemplate.setProducerListener(producerListener);
//        kafkaTemplate.send("topic.quick.initial", 0,0,"send msg to beanListener0");
//        kafkaTemplate.send("topic.quick.initial", 6,0,"send msg to beanListener1");
//        Thread.sleep(1000);
//    }
//
//    /**
//     * 利用@KafkaListener主题监听
//     * @param msgData
//     */
//    @KafkaListener(id = "consumer1", topics = "topic.quick.initial",groupId="groupid1")
//    public void listen(String msgData) {
//        log.info("==================consumer1 receive : "+msgData);
//    }
//
//    /**
//     * 利用@KafkaListener主题监听
//     * 同一个消费者组中只有一个消费者可以消费同一个主题中的同一个消息
//     * @param msgData
//     */
//    @KafkaListener(id = "consumer2", topics = "topic.quick.initial",groupId="groupid1")
//    public void listen2(String msgData) {
//        log.info("==================consumer2 receive : "+msgData);
//    }
//
//
//    @KafkaListener(id = "consumer3", topics = "topic.quick.initial")
//    public void consumerListener(ConsumerRecord<Integer, String> record) {
//        log.info("=================consumer3 receivet : " + record.toString());
//    }
//
//
//
//    //=================================================================================================
//    @Test
//    @Transactional
//    public void testBatch() throws InterruptedException{
//        for (int i = 0; i < 5; i++) {
//            kafkaTemplate.send("topic.quick.default", "test batch listener,dataNum-" + i);
//        }
//        Thread.sleep(5000);
//    }
//
//
//
//    //批量消费（这里必须指定消费工厂）
//    @KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"topic.quick.default"},containerFactory = "batchContainerFactory")
//    public void batchListener(List<String> data) {
//        log.info("拉取=====================================");
//        for (String s : data) {
//           log.info("==================topic.quick.default  receive : "+s);
//        }
//    }
//
//
//
//    //==============================================================================================
//    /**
//     * 监听带有ack机制的消息方式
//     * 如果注释掉ack.acknowledge()，则监听器可能会再次消费刚才未确认的消息
//     * @param record
//     * @param ack
//     */
//    @KafkaListener(id = "ack", topics = "topic.quick.ack",containerFactory = "ackContainerFactory")
//    public void ackListener(ConsumerRecord record, Acknowledgment ack) {
//        log.info("@@@@@@@@@@@@@@@@@@@@topic.quick.ack receive : " + record.value());
//        ack.acknowledge();
//    }
//
//    /**
//     * 测试ack
//     * @throws InterruptedException
//     */
//    @Test
//    @Transactional
//    public void testAck() throws InterruptedException {
//        for (int i = 0; i < 1; i++) {
//            kafkaTemplate.send("topic.quick.ack", i+"ack机制测试");
//        }
//        Thread.sleep(5000);
//    }
//
//}
