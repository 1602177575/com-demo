package com.smart.lock.utils;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author mtl
 */
@Component
public class RedisLockUtils {
    @Resource
   public Redisson redisson;

    public RLock getLock(String key){
        RLock rLock= redisson.getLock(key);
        return new RedissonRedLock(rLock);
    }

    public RLock getLock(String... keys){
        RLock[] redLocks = new RedissonRedLock[keys.length];
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            redLocks[i] = redisson.getLock(key);
        }
        return new RedissonRedLock(redLocks);
    }

}
