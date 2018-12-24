package com.cocunrrency;

import java.util.concurrent.locks.Lock;

/**
 * join�����������ͷ���
 * @author JCM
 *
 */
public class ThreadJoinTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread provide = new Thread(new Provide(),"provide�߳�") ;
		Thread eat = new Thread(new EatThread(provide),"eat�߳�") ;
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
				System.out.println(Thread.currentThread().getName()+"׼���Զ����ˣ�����û�гԵģ�����provide�ṩ����");
				provide.start();
				provide.join();
				System.out.println(Thread.currentThread().getName()+"�Զ�����");
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
				System.out.println(Thread.currentThread().getName()+"������˯5s��˵");
				Thread.currentThread().sleep(5000);
				System.out.println(Thread.currentThread().getName()+"�ṩ����");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			}
		}
		
	}

}
