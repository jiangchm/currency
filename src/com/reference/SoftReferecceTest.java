package com.reference;

import java.lang.ref.SoftReference;

/**
 * ��������Ҫ���ڻ����У���ϵͳ�ڴ治����ʱ�򣬿��Ի��������������õĶ���
 * @author JCM
 *
 */
public class SoftReferecceTest {

	public static void main(String[] args) {
		Object obj = new Object() ;
		SoftReference<Object> soft = new SoftReference<Object>(obj) ;
		obj = null ;
		System.out.println(obj);
		System.out.println(soft.get());
	}
}
