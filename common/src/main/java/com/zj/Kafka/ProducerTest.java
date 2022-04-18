package com.zj.Kafka;//package com.Kafka;
//
//import com.SpringbootApplication;
//
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.KafkaOperations;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.MessageHeaders;
//import org.springframework.messaging.support.GenericMessage;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.concurrent.ListenableFuture;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {SpringbootApplication.class})
//public class ProducerTest {
//
//    @Resource
//    private KafkaTemplate defaultKafkaTemplate;
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    @Autowired
//    private KafkaSendResultHandler producerListener;
//
//
//    @Test
//    public void testDemo() throws InterruptedException {
//        kafkaTemplate.send("topic.quick.initial",new Integer(2),2,"firstdata");
//        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
//        Thread.sleep(5000);
//    }
//
//
////    @Test
////    public void testDemo2() throws InterruptedException {
////        defaultKafkaTemplate.sendDefault(1,"321423");
////        //休眠5秒，为了使监听器有足够的时间监听到topic的数据
////        Thread.sleep(5000);
////    }
//
//
//    @Test
//    public void testTemplateSend() {
//        //发送带有时间戳的消息
//        kafkaTemplate.send("topic.quick.initial", 0, System.currentTimeMillis(), 0, "send message with timestamp");
//
//        //使用ProducerRecord发送消息
//        ProducerRecord record = new ProducerRecord("topic.quick.initial", "use ProducerRecord to send message");
//        kafkaTemplate.send(record);
//
//        //使用Message发送消息
//        Map map = new HashMap();
//        map.put(KafkaHeaders.TOPIC, "topic.quick.initial");
//        map.put(KafkaHeaders.PARTITION_ID, 0);
//        map.put(KafkaHeaders.MESSAGE_KEY, 0);
//        GenericMessage message = new GenericMessage("use Message to send message",new MessageHeaders(map));
//        kafkaTemplate.send(message);
//    }
//
//
//    /**
//     * 生产监听回调
//     * */
//    @Test
//    @Transactional
//    public void testProducerListen() throws InterruptedException {
//        kafkaTemplate.setProducerListener(producerListener);
//        kafkaTemplate.send("topic.quick.initial",0,1,"huidaotest");
//        Thread.sleep(1000);
//    }
//
//    /**
//     * kakfa事物测试(方式1)
//     * 配置Kafka事务管理器并使用@Transactional注解
//     * @throws InterruptedException
//     */
//    @Test
//    @Transactional
//    public void testTransactionalAnnotation() throws InterruptedException {
//        kafkaTemplate.send("topic.quick.initial", 0,0,"test transactional annotation");
//        throw new RuntimeException("fail");
//    }
//
//    /**
//     * 使用KafkaTemplate.executeInTransaction开启事务
//     * @throws InterruptedException
//     */
//    @Test
//    public void testExecuteInTransaction() throws InterruptedException {
//        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback() {
//            @Override
//            public Object doInOperations(KafkaOperations kafkaOperations) {
//                kafkaOperations.send("topic.quick.initial", 0,0,"test executeInTransaction");
//                throw new RuntimeException("fail");
//                //return true;
//            }
//        });
//    }
//
//}
