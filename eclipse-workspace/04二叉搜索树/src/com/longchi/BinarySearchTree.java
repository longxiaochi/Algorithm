package com.longchi;

import java.beans.FeatureDescriptor;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

@SuppressWarnings("unused")
public class BinarySearchTree<E> {
	private int size;
	private Node<E> root;
	
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	public void add(E element) {
		nodeNotNULLCheck(element);
		
		
		if (root == null) {
			root = new Node<>(element, null);
			size++;
			return ;
		}
		
		// 添加子节点，遍历找到合适的地方进行插入
		
		Node<E> node = root;
		while (node != null) {
			
		}
		
		
		
		
	}

	
	private void nodeNotNULLCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("节点不能为null");
		}
	}
	
	@SuppressWarnings("unused")
	private static class Node<E> {
		E element;
		Node<E> parent;
		Node<E> left;
		Node<E> right;

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
	}
}
