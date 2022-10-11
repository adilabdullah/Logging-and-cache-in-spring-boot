package com.logging.cache.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.logging.cache.model.CacheItem;

@Repository
public class CacheValueRepo {
       public static final String HASH_KEY_NAME = "TEST-REDIS";

	    @Autowired
	    private RedisTemplate redisTemplate;

	 //   HashOperations hashOperations = redisTemplate.opsForHash();

	    public CacheItem save(CacheItem cacheItem){
	        // SETS CacheValue object in CacheValue-ITEM hashmap at CacheValueId key
	        redisTemplate.opsForHash().put(HASH_KEY_NAME,cacheItem.getKey(),cacheItem.getValue());
	        return cacheItem;
	    }

	    public List<CacheItem> findAll(){
	        // GET all CacheValue values
	        return redisTemplate.opsForHash().values(HASH_KEY_NAME);
	    }

	    public String findItemById(String key){
	        // GET CacheValue object from CacheValue-ITEM hashmap by CacheValueId key
	        return redisTemplate.opsForHash().get(HASH_KEY_NAME,key).toString();
	    }


	    public String deleteCacheValue(String id){
	        // DELETE the hashkey by CacheValueId from CacheValue-ITEM hashmap
	        redisTemplate.opsForHash().delete(HASH_KEY_NAME,id);
	        return "CacheValue deleted successfully !!";
	    }
	    
}
