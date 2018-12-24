package com.cocunrrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��д���������ǹ�������������countdownlatch��semaphore��cyclibarrier��ֻ��û�д�����֪��
 * д���Ƕ�ռʽ(������ReentrantLock)
 * @author JCM
 * ���������� ��д���� дд����
 *
 */
public class ReentrantReadWriterLockTest {
	
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock() ;
	Lock readLock = lock.readLock();
	Lock writeLock = lock.writeLock() ;
	
	private void sleep() throws InterruptedException{
		System.out.println(Thread.currentThread()+"��ʼ˯��");
		Thread.currentThread().sleep(5000);
		System.out.println(Thread.currentThread()+"����˯��");
	}
	
	public void read(){
		readLock.lock();
		try{
			System.out.println("����������");
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
			System.out.println("д������"+str);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writeLock.unlock(); 
		}
	}
	
	public static void main(String[] args) {
		ReentrantReadWriterLockTest test = new ReentrantReadWriterLockTest();
		Thread readThread1 = new Thread(new ReadThread(test),"read�߳�1") ;
		Thread readThread2 = new Thread(new ReadThread(test),"read�߳�2") ;
		Thread writeThread = new Thread(new WriteThread(test),"write�߳�") ;
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
			test.write("����");
		}
	}

}
