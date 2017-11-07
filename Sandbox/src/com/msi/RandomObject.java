package com.msi;

import java.util.Random;

public class RandomObject {
	
	private final static Random rand = new Random();
	
	private RandomObject(){}
	
	public static Object next() {
		int type = rand.nextInt(6);
		switch (type) {
		case 0:
			return rand.nextBoolean();
		case 1:
			return rand.nextInt();
		case 2:
			return rand.nextDouble();
		case 3:
			return rand.nextFloat();
		case 4:
			return rand.nextLong();
		case 5:
			String s = "";
			int len = rand.nextInt(20);
			for (int i = 0; i < len; i++)
				s += (char)(rand.nextInt(26) + 'a');
			return s;
		}
		throw new IllegalStateException("Random.next() is broken :(");
	}

}
