//package com.zzg.spider.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.springframework.stereotype.Component;
//
//import com.mysql.jdbc.PreparedStatement;
//import com.zzg.spider.cache.RedisUtil;
//import com.zzg.spider.entity.CrawlHtml;
//import com.zzg.spider.util.DateTimeUtil;
//
//@Component
//public class CrawlHtmlDao {
//
//	private static String DBDRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";//todo
//	private static String DBURL = "jdbc:mysql://192.168.1.100:3306/spider?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
//	private static Connection con = null;
//	private static PreparedStatement sql;
//	
//	static{
//		try {
//			Class.forName(DBDRIVER);
//			con = DriverManager.getConnection(DBURL, "root", "root");
//		} catch (Exception e) {
//			System.err.println("SQLException:" + e.getMessage());
//		}
//		
//	}
//	public int save(CrawlHtml  crawlHtml) {
//
//		try {
//			sql=  (PreparedStatement) con.prepareStatement("insert into crawlhtml(`id`,`url`,`source`,`addr`,`create_date`) values(0,?,?,?,?)");
//			sql.setString(1, crawlHtml.getUrl());
//			sql.setString(2, crawlHtml.getSource());
//			sql.setString(3, crawlHtml.getAddr());
//			sql.setString(4, crawlHtml.getDate());
//			return sql.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.err.println("SQLException:" + e.getMessage());
//			return -1;
//		} 
////		finally {
////			if (con != null) {
////				try {
////					con.close();
////				} catch (SQLException e) {
////				}
////			}
////		}
//
//	}
//	
//	public static void main(String[] args) throws Exception {
////		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
////		 
////		BloomFilter cust = (BloomFilter)context.getBean("bloomFilter");
////		        System.out.println(cust);
//		final CrawlHtmlDao dao = new CrawlHtmlDao();
//		ExecutorService pool = Executors.newFixedThreadPool(100);
//		
//		for(int i=0;i<100;i++){
//			Runnable ra = new Runnable(){
//				@Override
//				public void run() {
//					CrawlHtml ct = new CrawlHtml();
//					ct.setUrl("url");
//					ct.setSource("123test");
//					ct.setAddr("addr");
//					ct.setDate(DateTimeUtil.getAddDate("yyyy-MM-dd hh:mm:ss",0));
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					dao.save(ct);
//				}
//			};
//			pool.submit(ra);
//		}
//		pool.shutdown();
//	}
//	
//	
//}
