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
public class LockAQSTest {
	
	private static ReentrantLock lock = new ReentrantLock() ;
	
	public static void main(String[] args) {
		Eag eag = new Eag();
		TX tx1 = new TX();
		TX2 tx2 = new TX2();
		new Thread(eag).start();
		new Thread(tx1).start();
		new Thread(tx2).start();
	}
	
	private static class Eag implements Runnable{

		@Override
		public void run() {
			lock.lock();
			System.out.println("�߳�eag������ȡ��");
		}
		
	}
	
	private static class TX implements Runnable{

		@Override
		public void run() {
			System.out.println("�߳�TX����׼����ȡ��");
			try{
				lock.lock();
				System.out.println("�߳�TX���ڻ�ȡ��");
			}finally{
				lock.unlock() ;
			}
		}
		
	}
	private static class TX2 implements Runnable{
		
		@Override
		public void run() {
			System.out.println("�߳�TX����׼����ȡ��");
			try{
				lock.lock();
				System.out.println("�߳�TX���ڻ�ȡ��");
			}finally{
				lock.unlock() ;
			}
		}
		
	}

}
