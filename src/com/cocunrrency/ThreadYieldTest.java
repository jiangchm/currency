package com.cocunrrency;

public class ThreadYieldTest {
	
	
	public static void main(String[] args) {
		Thread provide = new Thread(new Provide(),"provide�߳�") ;
		Thread eat = new Thread(new EatThread(),"eat�߳�") ;
		eat.start();
		provide.start();
	}
	
	private static class EatThread implements Runnable{
		@Override
		public void run() {
			try {
				Thread.currentThread().yield();
				System.out.println(Thread.currentThread().getName()+"�ӹ�����������Ҫ�Զ���");
			} finally{
			}
		}
	}
	
	private static class Provide extends Thread{
		@Override
		public void run() {
			try{
				Thread.currentThread().join();
				System.out.println(Thread.currentThread().getName()+"�ṩ����");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
		}
		
	}

}
