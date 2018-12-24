package com.cocunrrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：读锁是共享锁（类似于countdownlatch或semaphore、cyclibarrier，只是没有次数先知）
 * 写锁是独占式(类似于ReentrantLock)
 * @author JCM
 * 读读不互斥 读写互斥 写写互斥
 *
 */
public class ReentrantReadWriterLockTest {
	
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock() ;
	Lock readLock = lock.readLock();
	Lock writeLock = lock.writeLock() ;
	
	private void sleep() throws InterruptedException{
		System.out.println(Thread.currentThread()+"开始睡眠");
		Thread.currentThread().sleep(5000);
		System.out.println(Thread.currentThread()+"结束睡眠");
	}
	
	public void read(){
		readLock.lock();
		try{
			System.out.println("读到数据了");
			sleep();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			readLock.unlock(); 
		}
	}
	
	public void write(String str){
		writeLock.lock(); 
		try{
			sleep();
			System.out.println("写入数据"+str);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writeLock.unlock(); 
		}
	}
	
	public static void main(String[] args) {
		ReentrantReadWriterLockTest test = new ReentrantReadWriterLockTest();
		Thread readThread1 = new Thread(new ReadThread(test),"read线程1") ;
		Thread readThread2 = new Thread(new ReadThread(test),"read线程2") ;
		Thread writeThread = new Thread(new WriteThread(test),"write线程") ;
		writeThread.start();
		readThread1.start(); 
		readThread2.start(); 
	}
	
	private static class ReadThread implements Runnable{
		ReentrantReadWriterLockTest test  ;
		
		ReadThread(ReentrantReadWriterLockTest test){
			this.test = test ;
		}
		public void run() {
			test.read();
		}
	}
	private static class WriteThread implements Runnable{
		ReentrantReadWriterLockTest test  ;
		
		WriteThread(ReentrantReadWriterLockTest test){
			this.test = test ;
		}
		public void run() {
			test.write("张三");
		}
	}

}
