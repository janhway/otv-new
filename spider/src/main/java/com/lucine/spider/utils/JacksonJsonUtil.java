package com.lucine.spider.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

//是否可重入
public class JacksonJsonUtil { 
    private static ObjectMapper mapper = new ObjectMapper();      
       
    /**
     * 将java对象转换成json字符串
     * @param obj 准备转换的对象
     * @return json字符串
     * @throws Exception 
     */ 
    public static String beanToJson(Object obj) throws Exception { 
        try {             
            String json =mapper.writeValueAsString(obj); 
            return json; 
        } catch (Exception e) { 
            throw new Exception(e.getMessage()); 
        }
    }      
 
       
    /**
     * 将json字符串转换成java对象
     * @param json 准备转换的json字符串
     * @param cls  准备转换的类
     * @return 
     * @throws Exception 
     */ 
    public static Object jsonToBean(String json, Class<?> cls) throws Exception { 
        try { 
        Object vo = mapper.readValue(json, cls); 
        return vo; 
        } catch (Exception e) { 
            throw new Exception(e.getMessage()); 
        }    
    }    
}
