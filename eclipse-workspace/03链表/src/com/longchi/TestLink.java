package com.longchi;


public class TestLink {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<Integer> list = new LinkedList<>();
		list.add(10);
		list.add(30);
		
		list.add(1, 20);
		
		list.add(0, 1);
		
		System.out.println(list);
		
		list.remove(0);
		System.out.println(list);
		
		list.add(100, 200);
		
		System.out.println(list.indexOf(10));
		
		list.clear();
	}

}
