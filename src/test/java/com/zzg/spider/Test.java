package com.zzg.spider;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	static class OOMObject{
		
		public byte[] placeholder = new byte[120*1024];
		
	}
	
	public static void fillHeap(int num) throws InterruptedException{
		List<OOMObject> list = new ArrayList<OOMObject>();
		for(int i=0;i<num;i++){
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		fillHeap(1000);
		
//		int k = 5;
//		Character c = 'd';
//		Integer.valueOf(1);
		
		
		
	}

}
