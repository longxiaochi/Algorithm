package com.longchi;

public class LinkedList<E> extends AbstractList<E>{
	// 链表的首节点
	private Node<E> first;
	
	// 内部类，节点结构
	private static class Node<E> {
		E element;
		Node<E> next;
		
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
	}

	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	@Override
	public E get(int index) {
		return nodeOf(index).element;
	}

	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return 原来的元素ֵ
	 */
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		Node<E> node = nodeOf(index);
		E oldValue = node.element;
		node.element = element;
		return oldValue;
	}

	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param element
	 */
	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		// 需要区别头部插入和其他位置插入
		if (index == 0) {
			first = new Node<E>(element, first);
		} else {
			Node<E> prev = nodeOf(index-1);
			prev.next = new Node<E>(element, prev.next);
		}
		size++;
	}

	/**
	 * 删除index位置的元素
	 * @param index
	 * @return
	 */
	@Override
	public E remove(int index) {
		rangeCheck(index);
		E oldValue = null;
		if (index == 0) {
		   oldValue = first.element;
		   first = first.next;
		} else {
			Node<E>pre = nodeOf(index-1);
			oldValue = pre.next.element;
			pre.next = pre.next.next;
		}
		size --;
		return oldValue;
	}

	/**
	 * 查看元素的索引
	 * @param element
	 * @return
	 */
	@Override
	public int indexOf(E element) {
		Node<E> node = first;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node != null && node.element == null) {
					return i;
				}
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (node != null && element.equals(node.element)) {
					return i;
				}
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	/**
	 * 清除所有元素
	 * @param element
	 * @return
	 */
	@Override
	public void clear() {
		first = null;
		size = 0;
	}
	
	/**
	 * 获取某个下标的元素
	 * @param element
	 * @return
	 */
	public Node<E> nodeOf(int index) {
		rangeCheck(index);
		Node<E> node = first; 
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder buider = new StringBuilder();
		buider.append("size: ").append(size).append(" element: ").append("[");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				buider.append(",");
			}
			buider.append(node.element);
			node = node.next;
		}
		buider.append("]");
		return buider.toString();
	}
}
