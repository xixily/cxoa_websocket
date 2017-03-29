package com.chaoxing.oa.util.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.chaoxing.oa.test.TestThreads.FutureContext;

public class LoggerUtil implements Runnable{
	//线程池
	ExecutorService executorService;
	//保存异步计算的Future,多线程监听
	private FutureContext<String> context;
	private static List<Object> firstQueue = new Vector<Object>();//70-100
	private static List<Object> secondQueue = new Vector<Object>();//90-250
	private static List<Object> thirdQueue = new Vector<Object>();//240-1000
	private static int flag;

	@Override
	public void run() {
		
	}
	
	public static void add(Object obj){
		
	}
	
	public static void remove(Object obj){
		
	}
	
	public static void removeAll(){
		
	}
	
	public static void addLogger(List<Object> lis){
		
	}
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Future<String>> futures = new ArrayList<Future<String>>(5);
	}
}
