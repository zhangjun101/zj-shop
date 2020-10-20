package com.zj.Kafka;//package com.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.IntegerDeserializer;
//import org.apache.kafka.common.serialization.IntegerSerializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.AbstractMessageListenerContainer;
//import org.springframework.kafka.listener.KafkaMessageListenerContainer;
//import org.springframework.kafka.listener.MessageListener;
//import org.springframework.kafka.listener.config.ContainerProperties;
//import org.springframework.kafka.transaction.KafkaTransactionManager;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * kafka配置
// * */
//@Configuration
//public class KafkaConfig {
//
//    /**
//     * 主题创建topic.quick.initial
//     */
//    @Bean
//    public NewTopic initialTopic() {
//        return new NewTopic("topic.quick.initial",5, (short) 1 );
//    }
//
//    /**
//     * 主题创建topic.quick.default
//     */
//    @Bean
//    public NewTopic initialTopic2() {
//        return new NewTopic("topic.quick.default",5, (short) 1 );
//    }
//
//
//    /**
//     * 主题创建topic.quick.ack
//     */
//    @Bean
//    public NewTopic initialTopic3() {
//        return new NewTopic("topic.quick.ack",5, (short) 1 );
//    }
//
//
//    //=====================================生产者配置=======================================================
//    /**
//     * 生产者配置
//     * */
//    private Map<String, Object> senderProps (){
//        Map<String, Object> props = new HashMap<>();
//        //连接地址
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        //重试，0为不启用重试机制
//        props.put(ProducerConfig.RETRIES_CONFIG, 1);
//        //控制批处理大小，单位为字节
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        //生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024000);
//        //键的序列化方式
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
//        //值的序列化方式
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return props;
//    }
//
//
//    /**
//     * 根据senderProps（生产者配置）填写的参数创建生产者工厂
//     * 默认工厂
//     * */
//    @Bean
//    public ProducerFactory<Integer, String> producerFactory1() {
//        return new DefaultKafkaProducerFactory<>(senderProps());
//    }
//
//    /**
//     * 根据senderProps（生产者配置）填写的参数创建生产者工厂
//     * 这里开启事务配置（配置了事物的工厂）
//     * */
//    @Bean
//    public ProducerFactory<Integer, String> producerFactory() {
//        DefaultKafkaProducerFactory factory = new DefaultKafkaProducerFactory<>(senderProps());
//        //开启事务
//        factory.transactionCapable();
//        //TransactionIdPrefix是用来生成Transactional.id的前缀。
//        factory.setTransactionIdPrefix("tran-");
//        return factory;
//    }
//
//    /**
//     * 设置事物管理bean.
//     * @param producerFactory
//     * @return manager
//     */
//    @Bean
//    public KafkaTransactionManager transactionManager(ProducerFactory producerFactory) {
//        KafkaTransactionManager manager = new KafkaTransactionManager(producerFactory);
//        return manager;
//    }
//
//
//    /**
//     * kafkaTemplate实现了Kafka发送接收等功能
//     * @Primary 的作用注意哈
//     */
//    @Primary
//    @Bean("kafkaTemplate")
//    public KafkaTemplate<Integer, String> kafkaTemplate() {
//        KafkaTemplate template = new KafkaTemplate<Integer, String>(producerFactory());
//        return template;
//    }
//
//    /**
//     * kafkaTemplate实现了Kafka发送接收等功能
//     * 这个使用默认方法
//     */
//    @Bean("defaultKafkaTemplate")
//    public KafkaTemplate<Integer, String> defaultKafkaTemplate() {
//        KafkaTemplate template = new KafkaTemplate<Integer, String>(producerFactory());
//        template.setDefaultTopic("topic.quick.default");
//        return template;
//    }
//
//
//
//
////====================================普通消费者配置（单条拉取）==========================================
//    /**
//     * 消费者配置参数
//     */
//    private Map<String, Object> consumerProps() {
//        Map<String, Object> props = new HashMap<>();
//        //连接地址
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        //GroupID
//        //props.put(ConsumerConfig.GROUP_ID_CONFIG, "bootKafka");
//        //是否自动提交
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        //自动提交的频率
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
//        //Session超时设置
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
//        //键的反序列化方式
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//        //值的反序列化方式
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return props;
//    }
//
//
////    //根据consumerProps填写的参数创建消费者工厂
////    @Bean
////    public ConsumerFactory<Integer, String> consumerFactory() {
////        return new DefaultKafkaConsumerFactory<>(consumerProps());
////    }
//
//
//
////=============================批量消费者工厂配置====================================
//private Map<String, Object> consumerProps2() {
//    Map<String, Object> props = new HashMap<>();
//    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//    //自动提交
//    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
//    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
//    //一次拉取消息数量
//    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");
//    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    return props;
//}
//
//    @Bean("batchContainerFactory")
//    public ConcurrentKafkaListenerContainerFactory listenerContainer() {
//        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
//        container.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps2()));
//        //设置并发量，小于或等于Topic的分区数
//        container.setConcurrency(5);
//        //设置为批量监听
//        container.setBatchListener(true);
//        return container;
//    }
//
////=============================使用Ack机制确认消费=====================================
//    /**
//     *   使用Ack机制确认消费：
//     *   1设置ENABLE_AUTO_COMMIT_CONFIG=false，禁止自动提交
//     *   2设置AckMode=MANUAL_IMMEDIATE
//     *   3监听方法加入Acknowledgment ack 参数
//     */
//    private Map<String, Object> consumerProps3() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return props;
//    }
//
//    @Bean("ackContainerFactory")
//    public ConcurrentKafkaListenerContainerFactory ackContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
//        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps3()));
//        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
//        return factory;
//    }
//
//
//
//    //================================================================================================
//    /** ConcurrentKafkaListenerContainerFactory
//     * 创建Kafka监听器的工程类，这里只配置了消费者
//     * 非注解方式去监听Topic 方式
//     */
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
////        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(consumerFactory());
////        return factory;
////    }
//
//    /**
//     *    Bean方式创建监听容（消费）另外的方式是通过@KafkaListener方式建立监听
//     */
////    @Bean
////    public KafkaMessageListenerContainer demoListenerContainer() {
////        ContainerProperties properties = new ContainerProperties("topic.quick.initial");
////        properties.setGroupId("bean");
////        properties.setMessageListener(new MessageListener<Integer,String>() {
////            private Logger log = LoggerFactory.getLogger(this.getClass());
////            @Override
////            public void onMessage(ConsumerRecord<Integer, String> record) {
////                log.info("=================topic.quick.initial : " + record.toString());
////            }
////        });
////        return new KafkaMessageListenerContainer(consumerFactory(), properties);
////    }
//
//
//}
