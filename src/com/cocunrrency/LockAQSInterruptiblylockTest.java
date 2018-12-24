/**
 * JCM2018-12-10
 */
package com.cocunrrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JCM
 * @version 1.0
 * @Note
 */
public class LockAQSInterruptiblylockTest {
	
	private static ReentrantLock lock = new ReentrantLock() ;
	
	public static void main(String[] args) throws InterruptedException {
		Eag eag = new Eag();
		TX tx = new TX();
		Thread eagThread = new Thread(eag);
		eagThread.start();
		Thread txThread = new Thread(tx);
		txThread.start();
		Thread.currentThread().sleep(1000) ;
		txThread.interrupt();
	}
	
	private static class Eag implements Runnable{

		@Override
		public void run() {
			try{
				lock.lock();
				System.out.println("线程eag进来获取锁");
				lock.lock() ;
			} finally{
				lock.unlock() ;
//				lock.unlock() ;
			}
		}
		
	}
	
	private static class TX implements Runnable{

		@Override
		public void run() {
			System.out.println("线程TX进来准备获取可中断锁");
			try{
				lock.lockInterruptibly();
				System.out.println("线程TX终于获取锁");
			} catch (Exception e) {
				System.out.println("线程TX获取可中断锁异常");
				e.printStackTrace();
			}finally{
				lock.unlock() ;
			}
		}
		
	}

}
