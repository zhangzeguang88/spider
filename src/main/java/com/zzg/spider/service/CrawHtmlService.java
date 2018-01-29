package com.zzg.spider.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzg.spider.entity.CrawlHtml;
import com.zzg.spider.mapper.CrawlHtmlMapper;

@Service
public class CrawHtmlService {

	@Resource
	CrawlHtmlMapper crawlHtmlMapper;
	
	public int save(CrawlHtml crawlHtml){
		return crawlHtmlMapper.save(crawlHtml);
	}
	
}
