package com.zzg.spider;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.zzg.spider.entity.CrawlHtml;
import com.zzg.spider.service.CrawHtmlService;
import com.zzg.spider.util.DateTimeUtil;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMybatis {
	
	private static Logger logger = Logger.getLogger(TestMybatis.class);  
	private ApplicationContext ac = null;  
    
    private CrawHtmlService crawHtmlService;  
  
  @Before  
  public void before() {  
      ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
      crawHtmlService = (CrawHtmlService) ac.getBean("crawHtmlService");  
  }  
  
    @Test
    public void test1() {  
    	CrawlHtml ct = new CrawlHtml();
		ct.setUrl("url");
		ct.setSource("123test");
		ct.setAddr("addr");
		ct.setDate(DateTimeUtil.getAddDate("yyyy-MM-dd hh:mm:ss",0));
        crawHtmlService.save(ct);  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(ct));  
    }  

}
