package com.cocunrrency;

public class ThreadYieldTest {
	
	
	public static void main(String[] args) {
		Thread provide = new Thread(new Provide(),"provide线程") ;
		Thread eat = new Thread(new EatThread(),"eat线程") ;
		eat.start();
		provide.start();
	}
	
	private static class EatThread implements Runnable{
		@Override
		public void run() {
			try {
				Thread.currentThread().yield();
				System.out.println(Thread.currentThread().getName()+"从挂起中醒来，要吃东西");
			} finally{
			}
		}
	}
	
	private static class Provide extends Thread{
		@Override
		public void run() {
			try{
				Thread.currentThread().join();
				System.out.println(Thread.currentThread().getName()+"提供东西");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
		}
		
	}

}
