package com.msi;

import java.util.HashSet;
import java.util.Set;

public class MyHashTable2<K, V> {
	
	private Entry<K, V>[] ele = new Entry[10];
	
	private int size = 10;
	
	public static void doStuff() {
		MyHashTable2<String, Integer> map = new MyHashTable2<>();
		for (int i = 0; i < 20; i++)
			map.put("k" + i, i);
		map.put("k1", 100);
		map.remove("k3");
	}
	
	public void put(K key, V value) {
		int hash = key.hashCode();
		int index = hash % size;
		Entry<K, V> en = new Entry<>(key, value);
		
		// if spot is empty
		if (ele[index] == null) {
			ele[index] = en;
			return;
		}
		else { // chain to last entry or update existing
			Entry<K, V> tmp = ele[index];
			while (tmp.next != null) {
				if (tmp.key.equals(key)) {
					tmp.value = value;
					return;
				}
				tmp = tmp.next;
			}
			tmp.next = en;
		}
		
		if (entrySet().size() > (size * 0.7)) {
			// too many collisions, need to rebuild
			increaseCapacity();
		}
	}
	
	public V get(K key) {
		Entry<K, V> en = ele[key.hashCode() % size];
		
		while (en != null && !en.key.equals(key)) {
			en = en.next;
		}
		if (en != null)
			return en.value;
		return null;
	}
	
	public V remove(K key) {
		int hash = key.hashCode();
		int index = hash % size;
		Entry<K, V> en = ele[index], prev = null;
		if (en == null) // empty slot
			return null;
		if (en.next == null) { // only 1 item in slot
			ele[index] = null;
			return en.value;
		}
		
		while (en != null && !en.key.equals(key)) {
			prev = en;
			en = en.next;
		}
		if (en == null)
			return null;
		V val = en.value;
		prev.next = en.next;
		return val;
	}
	
	private void increaseCapacity() {
		size *= 2;
		Set<Entry<K, V>> entrySet = entrySet();
		ele = new Entry[size];
		for (Entry<K, V> en : entrySet) {
			put(en.key, en.value);
		}
	}
	
	public Set<Entry<K, V>> entrySet() {
		HashSet<Entry<K, V>> set = new HashSet<>();
		for (int i = 0; i < ele.length; i++) {
			Entry<K, V> en = ele[i];
			while (en != null) {
				set.add(en);
				en = en.next;
			}
		}
		return set;
	}

	private static class Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;
		
		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
