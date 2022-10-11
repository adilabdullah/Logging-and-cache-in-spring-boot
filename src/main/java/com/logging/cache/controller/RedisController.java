 package com.logging.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.logging.cache.model.CacheItem;
import com.logging.cache.service.RedisService;

@RestController
public class RedisController {

	@Autowired
    private RedisService redisService;

	   @GetMapping("/Logging")
	    public String logging() {
	        return "This is logging service";
	    }
	
	   @GetMapping("/Cache")
	    public String cache() {
	        return "This is cache service";
	    }
	   
    @GetMapping("/Save")
    public String save() {
        return redisService.save();
    }

    @GetMapping
    public List<CacheItem> getMenus() {
        return redisService.getAll();
    }

    @GetMapping("/{key}")
    public String findItemById(@PathVariable("key") String key) {
        return redisService.getSpecific(key);
    }


    @DeleteMapping("/{id}")
    public String deleteMenuById(@PathVariable String key)   {
    	return redisService.deleteSpecific(key);
	}
	
}
