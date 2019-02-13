package com.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用典型的应用是在threadlocal中threadLocalMap维护的Entry中的key
 * 当gc的时候，key会回收，如果没有做get或set或remove操作，会造成内存泄漏
 * @author JCM
 *
 */
public class WeakReferenceTest {

	public static void main(String[] args) {
		 WeakReference<String> weak = new WeakReference<String>(new String("abc")) ;
		 System.out.println(weak.get());
		 System.gc();
		 System.out.println(weak.get());
//		WeakReference<String> sr = new WeakReference<String>(
//				new String("hello"));
//		System.out.println(sr.get());
//		System.gc(); // 通知JVM的gc进行垃圾回收
//		System.out.println(sr.get());
	}
}
