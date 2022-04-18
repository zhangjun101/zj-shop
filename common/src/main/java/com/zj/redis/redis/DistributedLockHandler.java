//package com.zj.redis.redis;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.util.concurrent.TimeUnit;
//
///**
// * redis分布式锁
// *
// */
//@Component
//public class DistributedLockHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(DistributedLockHandler.class);
//
//    private final static long LOCK_EXPIRE = 30 * 1000L;//单个业务持有锁的时间30s，防止死锁
//    private final static long LOCK_TRY_INTERVAL = 30L;//默认30ms尝试一次
//    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;//默认尝试20s
//
//    @Resource
//    private StringRedisTemplate template;
//
//    /**
//     * 尝试获取全局锁
//     *
//     * @param lock 锁的名称
//     * @return true 获取成功，false获取失败
//     */
//    public boolean tryLock(RedisLock lock) {
//        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
//    }
//
//    /**
//     * 尝试获取全局锁
//     *
//     * @param lock    锁的名称
//     * @param timeout 获取超时时间 单位ms
//     * @return true 获取成功，false获取失败
//     */
//    public boolean tryLock(RedisLock lock, long timeout) {
//        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
//    }
//
//    /**
//     * 尝试获取全局锁
//     *
//     * @param lock        锁的名称
//     * @param timeout     获取锁的超时时间
//     * @param tryInterval 多少毫秒尝试获取一次
//     * @return true 获取成功，false获取失败
//     */
//    public boolean tryLock(RedisLock lock, long timeout, long tryInterval) {
//        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
//    }
//
//    /**
//     * 尝试获取全局锁
//     *
//     * @param lock           锁的名称
//     * @param timeout        获取锁的超时时间
//     * @param tryInterval    多少毫秒尝试获取一次
//     * @param lockExpireTime 锁的过期
//     * @return true 获取成功，false获取失败
//     */
//    public boolean tryLock(RedisLock lock, long timeout, long tryInterval, long lockExpireTime) {
//        return getLock(lock, timeout, tryInterval, lockExpireTime);
//    }
//
//
//    /**
//     * 操作redis获取全局锁
//     *
//     * @param lock           锁的名称
//     * @param timeout        获取的超时时间
//     * @param tryInterval    多少ms尝试一次
//     * @param lockExpireTime 锁的过期时间
//     * @return true 获取成功，false获取失败
//     */
//    public boolean getLock(RedisLock lock, long timeout, long tryInterval, long lockExpireTime){
//        //参数非空验证，如果为空则无法获取到锁
//        if (StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())) {
//            return false;
//        }
//        //记录开始时间
//        long startTime = System.currentTimeMillis();
//        String lockName = lock.getName();
//        String lockValue = lock.getValue();
//        try{
//
//            //循环获取锁，直到获取锁或者超时
//            do {
//                //key不存在，即获取锁成功
//                if(!template.hasKey(lockName)){
//                    template.opsForValue().set(lockName,lockValue,lockExpireTime, TimeUnit.MICROSECONDS);
//                    return true;
//                }
//                //key存在，获取锁失败
//                else{
//                    logger.debug("lock is exist!！！");
//                    //尝试超过了设定最大时间值之后直接跳出循环
//                    if (System.currentTimeMillis() - startTime > timeout) {
//                        return false;
//                    }
//                    //线程休眠设定值（间隔多少时间尝试获取一次锁）
//                    Thread.sleep(tryInterval);
//                }
//            }
//            while (template.hasKey(lockName));
//        }catch (Throwable t){
//            logger.error(t.getMessage());
//            return false;
//        }
//        return false;
//    }
//
//    /**
//     * 释放锁
//     */
//    public void releaseLock(RedisLock lock) {
//        if (!StringUtils.isEmpty(lock.getName())) {
//            template.delete(lock.getName());
//        }
//    }
//
//
//}
