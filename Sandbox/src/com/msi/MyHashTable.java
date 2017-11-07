package com.msi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyHashTable<K, V> {

	private int size = 10;
	private HashNode<K, V>[] array = new HashNode[size];
	
	public static void doStuff() throws Exception {
		MyHashTable<String, String> map = new MyHashTable<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		map.put("key5", "value5");
		map.put("key6", "value6");
		map.put("key7", "value7");
		map.put("key8", "value8");
		map.put("key9", "value9");
		map.put("key10", "value10");
		map.put("key0", "value0");
		map.put("key5", "a new value");
		//map.remove("key10");
		map.resize();
	}

	public void put(K key, V value) {
		if (key == null)
			return;
		int hash = key.hashCode();
		HashNode<K, V> node = new HashNode<>();
		node.key = key;
		node.value = value;

		// no collision; add node and be done
		if (array[hash % size] == null) {
			array[hash % size] = node;
			return;
		}

		// might simply be overwriting an existing key with a new value
		HashNode<K, V> ll = array[hash % size];
		if (ll.key.equals(key)) {
			ll.value = value;
			return;
		}
		// collision, chain a new node to the end of the linked list
		while (ll.next != null)
			ll = ll.next;
		// ll now points to the last item in the LL
		ll.next = node;
	}

	public V get(K key) {
		HashNode<K, V> node = array[key.hashCode() % size];
		
		// search the linked list for the correct node
		while (node != null && !node.key.equals(key))
			node = node.next;

		// doesn't exist
		if (node == null)
			return null;
		return node.value; // does exist
	}
	
	public V remove(K key) {
		HashNode<K, V> node = array[key.hashCode() % size];

		// key doesn't exist
		if (node == null)
			return null;

		// key is the first item in the LL
		if (node.key.equals(key)) {
			HashNode<K, V> ret = node;
			array[key.hashCode() % size] = ret.next;
			return ret.value;
		}

		// key is in the middle or end of LL
		HashNode<K, V> prev = null;
		while(node != null && !node.key.equals(key)) {
			prev = node;
			node = node.next;
		}

		// key doesn't exist
		if (node == null)
			return null;

		// found key
		HashNode<K, V> ret = node;
		prev.next = node.next;
		return ret.value;
	}

	// too many collisions, need to expand array size
	public void resize() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	private List<HashNode<K, V>> getAllNodes(HashNode<K, V>[] arr) {
		List<HashNode<K, V>> list = new ArrayList<>();
		for (HashNode<K, V> node : arr) {
			while (node != null) {
				list.add(node);
				node = node.next;
			}
		}
		return list;
	}

	public static class HashNode<K, V> {
		public K key;
		public V value;
		private HashNode<K, V> next;
	}
}