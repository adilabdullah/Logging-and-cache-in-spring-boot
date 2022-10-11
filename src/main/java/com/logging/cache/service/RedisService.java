package com.logging.cache.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.logging.cache.model.CacheItem;
import com.logging.cache.repository.CacheValueRepo;

@Service
public class RedisService {

	
	@Autowired
	private CacheValueRepo cacheValueRepo; 
	
	public String save()
	{
		try {
		File file = ResourceUtils.getFile("D:\\Config\\adil.json");
		if(file.exists()) {
		  byte[] fileData = Files.readAllBytes(file.toPath());
		  String fileContent = new String(fileData);
		
		 JSONParser parser = new JSONParser();
         JSONObject jsonObject = (JSONObject) parser.parse(fileContent);
         Set<String> keys = jsonObject.keySet();
         for(String key:keys)
         {
        	JSONObject obj=(JSONObject) jsonObject.get(key);
        	Set<String> kobj=obj.keySet();
        	 for(String ob:kobj)
             {
        		// System.out.println(key+"-"+ob+"=="+obj.get(ob));	 
        		 cacheValueRepo.save(new CacheItem(key+"_"+ob,obj.get(ob).toString()));
             }
         }
         return "Cache updated";
		}
		else
		{
		  return "File not exist";	
		}
		}
		catch(Exception ex)
		{
			return ex.getMessage();
		}
	}  
	
	
	
	public List<CacheItem> getAll()
	{
      return cacheValueRepo.findAll();
     
	}
	
	
	public String getSpecific(String key)
	{
      return cacheValueRepo.findItemById(key).toString();
     
	}
	
	public String deleteSpecific(String key)
	{
      return cacheValueRepo.deleteCacheValue(key);
     
	}
		
/*		public static void main(String[] args) throws IOException, ParseException
		{
			File file = ResourceUtils.getFile("D:\\Config\\adil.json");
			byte[] fileData = Files.readAllBytes(file.toPath());
			String fileContent = new String(fileData);
			
		//	System.out.println(fileContent);	
	        JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(fileContent);
           
            Set<String> keys = jsonObject.keySet();

      /*      Iterator<String> iterate = jsonObject.keySet().iterator();
            while(iterate.hasNext()) {
                JSONObject obj=(JSONObject) jsonObject.get(iterate.next());
            	System.out.println(obj);
              }  */
   /*        for(String key:keys)
            {
            	JSONObject obj=(JSONObject) jsonObject.get(key);
            	Set<String> kobj=obj.keySet();
            	 for(String ob:kobj)
                 {
            		 System.out.println(key+"-"+ob+"=="+obj.get(ob));	 
                 }
            }  
		  
		}   */
}
