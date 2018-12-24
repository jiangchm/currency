/**
 * JCM2018-12-12
 */
package com.cocunrrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JCM
 * @version 1.0
 * @Note
 */
public class ConditionTest {
	String s ;
	ReentrantLock lock = new ReentrantLock() ;
	
	Condition notFull = lock.newCondition() ;
	Condition notEmpty = lock.newCondition() ;
	Object[] items = new Object[1];
	int putstr ,takestr,count;
	
	public void put(Object x) throws InterruptedException{
		lock.lock();
		try{
			if(count == items.length){
				notFull.await() ;
			}
			items[putstr] = x ;
			System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"����:"+x);
			if(++putstr == items.length) putstr = 0 ;//��ʾ��ǰ�����Ѿ�������
			++count ;
			notEmpty.signal() ;
		}finally{
			lock.unlock();
		}
	}
	
	public Object take(Thread putThread) throws InterruptedException{
		lock.lock() ;
		if(count == 0){//��ʾ��ǰ�����ǿյ�
			putThread.start();
			notEmpty.await();
		}
		Object x = items[takestr] ;
		System.out.println("��ǰ�߳�"+Thread.currentThread().getName()+"ȡ��:"+x);
		if (++takestr == items.length) takestr = 0;
		--count ;
		notFull.signal();
		return x ;
	}
	
	public static void main(String[] args) {
		ConditionTest test = new ConditionTest() ;
		Object obj = "apple" ;
		Thread putThread = new Thread(new PutClass(test,obj),"putThread") ;
		Thread takThread = new Thread(new TakClass(test,putThread),"takThread") ;
		takThread.start();
	}
	
	private static class PutClass implements Runnable{
		private ConditionTest conditionTest ;
		private Object obj ;
		PutClass(ConditionTest conditionTest,Object obj){
			this.obj = obj ;
			this.conditionTest = conditionTest ;
		}
		@Override
		public void run() {
			try {
				conditionTest.put(obj) ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static class TakClass implements Runnable{
		private ConditionTest conditionTest ;
		Thread putThread ;
		TakClass(ConditionTest conditionTest,Thread putThread){
			this.conditionTest = conditionTest ;
			this.putThread = putThread ;
		}
		
		@Override
		public void run() {
			try {
				conditionTest.take(putThread) ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
