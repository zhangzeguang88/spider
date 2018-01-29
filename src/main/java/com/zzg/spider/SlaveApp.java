package com.zzg.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzg.spider.cache.RedisUtil;
import com.zzg.spider.download.Download;
import com.zzg.spider.parse.LinkFilter;


public class SlaveApp {
	
	

/*	//并发执行队列
	private Queue<String> localQueue = new ConcurrentLinkedDeque<String>();
	
	public Queue<String> getLocalQueue() {
		return localQueue;
	}

	public void setLocalQueue(Queue<String> localQueue) {
		this.localQueue = localQueue;
	}

	//定时任务 每次拿100个url ,当发现队列数值《20时，再去url队列拉数据
	@Scheduled(fixedRate = 1*60*1000)
	public void doJob() { 
	   if(localQueue.size()<20){
		   localQueue.addAll(RedisUtil.get("queue", 0, 100));
	   }
	}*/
	
	public static void main(String args[]){
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		final Download download = (Download)context.getBean("download");
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Runnable ra = new Runnable(){
			@Override
			public void run() {
				while(true){
					String url = (String) RedisUtil.rpop("queue");
					if(url==null){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						continue;
					}
					try {
						download.loadPage(url, "GET", null, null, null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
		};
		
		pool.submit(ra);
//		pool.submit(new SlaveThreadRunnable());
//		pool.submit(new SlaveThreadRunnable());
		
		pool.shutdown();
	}
	
	
	
}

