package com.zzg.spider.parse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class BloomFilterUtil {
	
	private static BloomFilter bloomFilter = null;
	
	
	public static BloomFilter getBloomFilter() {
		try
		{
			if(bloomFilter != null){
				return bloomFilter;
			}
			FileInputStream fi = new FileInputStream("bf.bat");
			ObjectInputStream in = new ObjectInputStream(fi);
			BloomFilter bFilter = (BloomFilter)in.readObject();					
			bloomFilter = bFilter;
			in.close();
		}catch(Exception e){
			synchronized(BloomFilterUtil.class){
				if(bloomFilter==null) {
					bloomFilter = new BloomFilter();
				}
			}
		}
		return bloomFilter;
	}
	
	public static void setBloomFilter(BloomFilter bf) {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bf.bat"));
			out.writeObject(bloomFilter);
			out.close();
		}catch(Exception e){								
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		 
		BloomFilter cust = (BloomFilter)context.getBean("bloomFilter");
		        System.out.println(cust);
		
		BloomFilterUtil bfi = new BloomFilterUtil();
		BloomFilter bf = bfi.getBloomFilter();
		String[] value = new String[]{"1","2","1"};
		if(!bf.contains(value[0])){
			bf.add(value[0]);
		}
		if(!bf.contains(value[1])){
			bf.add(value[1]);
		}
		if(!bf.contains(value[2])){
			bf.add(value[2]);
		}
	}
	
}

