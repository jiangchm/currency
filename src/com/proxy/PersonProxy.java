package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonProxy implements InvocationHandler{
	
	private Person person ;
	
	PersonProxy(Person person){
		this.person = person ;
	}
	public Object getProxy(){
		return Proxy.newProxyInstance(Person.class.getClassLoader(), person.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("调用方法前");
		Object obj = method.invoke(person, args);
		System.out.println(obj);
		System.out.println("调用方法后");
		return obj;
	}
	
	public static void main(String[] args) {
		Person person = new PersonImpl();
		PersonProxy proxy = new PersonProxy(person) ;
		Person obj =  (Person) proxy.getProxy() ;
		obj.eat("ab") ;
	}

}
