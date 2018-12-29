package com.taobao.rest.pojo;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMain {
	
	public static void main(String[] args) throws Exception { 
		CatNode catNode = new CatNode(); 
		catNode.setName("zhangsan");
		catNode.setUrl("www.baidu.com");
		catNode.setItems(Arrays.asList("a","b","c"));
        System.out.println(new ObjectMapper().writeValueAsString(catNode)); 
    } 

}
