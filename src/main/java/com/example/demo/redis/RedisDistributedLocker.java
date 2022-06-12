package com.example.demo.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: Mr.wang-Demo
 * @BelongsPackage: com.example.demo.redis
 * @Author: Mr.wang
 * @Date: 2022/6/4 19:50
 * @Description: Redisson 分布式锁
 */
public class RedisDistributedLocker {

    private static final long DEFAULT_WAIT_TIME = 30;
    private static final long DEFAULT_TIMEOUT = 5;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private static RedissonClient redisson;

    public void setRedissonClient(RedissonClient locker) {
        redisson = locker;
    }

    /**
     * 使用分布式锁，使用锁默认超时时间。
     *
     * @param lockName
     * @param fairLock 是否使用公平锁
     * @return
     */
    public static RLock lock(String lockName, boolean fairLock) {
        return lock(lockName, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
    }

    /**
     * 使用分布式锁。自定义锁的超时时间
     *
     * @param lockName
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     * @param timeUnit
     * @param fairLock  是否使用公平锁
     * @return
     */
    public static RLock lock(String lockName, long leaseTime, TimeUnit timeUnit, boolean fairLock) {
        RLock lock = getLock(lockName, fairLock);
        lock.lock(leaseTime, timeUnit);
        return lock;
    }

    /**
     * 释放锁
     *
     * @param lockName
     */
    public static void unlock(String lockName) {
        RLock lock = redisson.getLock(lockName);
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public static void unlock(RLock lock) {
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 尝试分布式锁，使用锁默认等待时间、超时时间。
     *
     * @param lockName
     * @param fairLock 是否使用公平锁
     * @return
     */
    public static boolean tryLock(String lockName, boolean fairLock) {
        return tryLock(lockName, DEFAULT_WAIT_TIME, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT, fairLock);
    }

    /**
     * 尝试分布式锁，自定义等待时间、超时时间。
     *
     * @param lockName
     * @param waitTime  获取锁最长等待时间
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     * @param timeUnit
     * @param fairLock  是否使用公平锁
     * @return
     */
    public static boolean tryLock(String lockName,
                                  long waitTime,
                                  long leaseTime,
                                  TimeUnit timeUnit,
                                  boolean fairLock) {
        RLock lock = getLock(lockName, fairLock);
        try {
            return lock.tryLock(waitTime, leaseTime, timeUnit);
        } catch (InterruptedException e) {
            System.out.println("获取锁失败");
            return false;
        }
    }

    public static RLock getLock(String lockName, boolean fairLock) {
        RLock lock;
        if (fairLock) {
            lock = redisson.getFairLock(lockName);
        } else {
            lock = redisson.getLock(lockName);
        }
        return lock;
    }
}
