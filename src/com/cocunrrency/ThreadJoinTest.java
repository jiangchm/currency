package com.cocunrrency;

import java.util.concurrent.locks.Lock;

/**
 * join方法并不会释放锁
 * @author JCM
 *
 */
public class ThreadJoinTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread provide = new Thread(new Provide(),"provide线程") ;
		Thread eat = new Thread(new EatThread(provide),"eat线程") ;
		eat.start();
//		provide.start();
	}
	
	private static class EatThread implements Runnable{
		Thread provide ;
		EatThread(Thread provide){
			this.provide = provide ;
		}
		
		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName()+"准备吃东西了，发现没有吃的，唤醒provide提供东西");
				provide.start();
				provide.join();
				System.out.println(Thread.currentThread().getName()+"吃东西了");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
		}
	}
	
	private static class Provide extends Thread{
		@Override
		public void run() {
			try{
				System.out.println(Thread.currentThread().getName()+"老子先睡5s再说");
				Thread.currentThread().sleep(5000);
				System.out.println(Thread.currentThread().getName()+"提供东西");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
		}
		
	}

}
