package com.zzg.spider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.zzg.spider.cache.RedisUtil;
import com.zzg.spider.parse.BloomFilter;
import com.zzg.spider.parse.LinkFilter;

public class MasterApp {

	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		final LinkFilter linkFilter = (LinkFilter)context.getBean("linkFilter");
		
		List<String> seed = readSeed("D:/spider_init.txt");
		for(int i=0;i<seed.size();i++){
			 RedisUtil.lpush("queue", seed.get(i));
		}
		
		// 启动解析线程
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Runnable ra = new Runnable(){
			@Override
			public void run() {
				while(true){
					String source = (String) RedisUtil.rpop("sourceQueue");
					if(source == null){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						continue;
					}
					try {
						linkFilter.findLinkByJ(source);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
		};
		
		pool.submit(ra);
		//pool.submit(new MasterThreadRunnable());
		
		pool.shutdown();
	}


	public static List<String> readSeed(String filePath){
		List<String> seed= new ArrayList<String>();
	    try {
	            String encoding="utf-8";
	            File file=new File(filePath);
	            if(file.isFile() && file.exists()){ //判断文件是否存在
	                InputStreamReader read = new InputStreamReader(
	                new FileInputStream(file),encoding);//考虑到编码格式
	                BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTxt = null;
	                while((lineTxt = bufferedReader.readLine()) != null){
	                    seed.add(lineTxt);
	                }
	                read.close();
			    }else{
			        System.out.println("找不到指定的文件");
			    }
	    } catch (Exception e) {
	        System.out.println("读取文件内容出错");
	        e.printStackTrace();
	    }
	    return seed;
	}
}

