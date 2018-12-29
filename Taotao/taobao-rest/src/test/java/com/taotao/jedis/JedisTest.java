package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taobao.rest.component.JedisClient;

public class JedisTest {

	@Test
	public void TestJedisCluster(){
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		String result = jedisClient.get("name");
		System.out.println(result);
	}
	
}
