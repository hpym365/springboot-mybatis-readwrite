package com.example.habendemo;

import java.util.LinkedList;

/**
 * @Author: Haben
 * @Description:
 * @Date: 2017-11-22 06:55
 * @Version: 1.0
 **/
public class LinkedTest {

	public static void main(String[] args) {

		Node head = new Node(0);
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		head.setNext(node1);
		node1.setNext(node2);
		node2.setNext(node3);

//		print(head);
//		print(head);

		Node fz = fz1(head);

		print(fz);
//		while

	}

	public static Node fz1(Node head) {
		Node current = head;

		Node newHead = null;
		Node temp;

		while (current.getNext() != null) {
			if (newHead == null)
				newHead = current;

			temp = current.getNext();
			current.setNext(temp.getNext());
			temp.setNext(newHead);

			newHead = temp;
		}
		return newHead;

	}

	public static void print(Node head) {
		Node current = head;

		while (current != null) {
			System.out.println(current.getData());
			current = current.getNext();
		}
	}

	static class Node {
		private int Data;// 数据域
		private Node Next;// 指针域

		public Node(int Data) {
			// super();
			this.Data = Data;
		}

		public int getData() {
			return Data;
		}

		public void setData(int Data) {
			this.Data = Data;
		}

		public Node getNext() {
			return Next;
		}

		public void setNext(Node Next) {
			this.Next = Next;
		}
	}
}
