package com.zzg.spider.parse;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class Test {

	
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		 
		BloomFilter cust = (BloomFilter)context.getBean("bloomFilter");
		        System.out.println(cust);
		
		
	}
	
}

