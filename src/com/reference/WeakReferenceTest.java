package com.reference;

import java.lang.ref.WeakReference;

/**
 * �����õ��͵�Ӧ������threadlocal��threadLocalMapά����Entry�е�key
 * ��gc��ʱ��key����գ����û����get��set��remove������������ڴ�й©
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
//		System.gc(); // ֪ͨJVM��gc������������
//		System.out.println(sr.get());
	}
}
