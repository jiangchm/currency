package com.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用主要用在缓存中，当系统内存不够的时候，可以回收软引用所引用的对象
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
