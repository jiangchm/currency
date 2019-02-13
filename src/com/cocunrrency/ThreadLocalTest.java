package com.cocunrrency;

public class ThreadLocalTest {

	static ThreadLocal<String > tl = new ThreadLocal<String>() {

		@Override
		protected String initialValue() {
			return super.initialValue();
		}
		
	};
	
	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 0 ;i<5 ;i++ ){
			Thread t = new Thread(new TlThread(i));
			t.start();
		}
		Thread.currentThread().sleep(500);
		
	}
	
	private static class TlThread implements Runnable{

		private int i ; 
		public TlThread(int m ){
			i = m ;
		}
		@Override
		public void run() {
//			System.out.println(Thread.currentThread().getName() +"---" + i);
			tl.set(i+"");
			
			System.out.println(Thread.currentThread().getName() +"-" + tl.get());
		}
	}
}
