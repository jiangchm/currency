package com.alog;

/**
 * ��������ò����ʶ
 * @author JCM
 *
 */
public class IntegerTest {
	
	public static void main(String[] args) throws InterruptedException {
		Integer i = new Integer(1) ;
		print(i);
		i = i<<2;
		print(i) ;
		i = -1 ;
		print(i);
		print(i.MAX_VALUE);
		stringTest("abc") ;
		Thread.currentThread().sleep(100000);
	}
	
	private static void print(Integer i){
		System.out.println(i+",�����ƴ�:"+i.toBinaryString(i));
	}
	
	public static void stringTest(String str){
		System.out.println(str.intern());
	}

}
