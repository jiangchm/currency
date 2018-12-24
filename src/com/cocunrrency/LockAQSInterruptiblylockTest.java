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
				System.out.println("�߳�eag������ȡ��");
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
			System.out.println("�߳�TX����׼����ȡ���ж���");
			try{
				lock.lockInterruptibly();
				System.out.println("�߳�TX���ڻ�ȡ��");
			} catch (Exception e) {
				System.out.println("�߳�TX��ȡ���ж����쳣");
				e.printStackTrace();
			}finally{
				lock.unlock() ;
			}
		}
		
	}

}
