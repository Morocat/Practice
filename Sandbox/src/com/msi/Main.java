package com.msi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.msi.Lists.TreeNode;
import com.msi.codwars.Graphs;

public class Main {
	private final static Random rand = new Random();

	public static void main(String[] args) {
		Main m = new Main();
		Set<Integer> set = new HashSet<>();
		
		// List<Integer> primes = m.sieve(10000);
		// for (int i = 0; i < primes.size(); i++) {
		// System.out.println(primes.get(i) + " ");
		// }
		/*
		 * MyLinkedList mll = new MyLinkedList(); mll.reverse();
		 * mll.printList();
		 */
		/*
		 * // List<Integer> list = new ArrayList<>(); // for (int i = 0; i < 10;
		 * i++) // list.add(rand.nextInt(10)); // for (int i = 0; i <
		 * list.size(); i++) // System.out.print(list.get(i) + " "); //
		 * m.bubbleSort(list); // System.out.println(); // for (int i = 0; i <
		 * list.size(); i++) // System.out.print(list.get(i) + " ");
		 */
		// String[] spl = "abcabcabc".split("ab");
		// System.out.println(m.AlphabetSoup("Argumentgoeshere"));
		// perimeter(30);
		//int[] arr = { 1, 2, 3, 4, 5 };
		//foldArray(arr, 1);
		//List<String> list = m.strComb("10?001?1??100011?1");
		//System.out.println("Fib sum: " + m.fibSum(5));
		//String[][] str = new String[2][3];
		//System.out.println(str.length);
		/*Lists lists = new Lists();
		TreeNode tn = lists.init();
		lists.flatten(tn);
		int num;
		while(!set.add(num = rand.nextInt(15) + 1));*/
		//long[] primeGap = gap(2, 100L, 110L);
		MyTree mt = new MyTree();
		MyHashTable<String, String> myHash = new MyHashTable<>();
		MyLinkedList mll = new MyLinkedList();
		MyPriorityQueue q = new MyPriorityQueue();
		MyHeap heap = new MyHeap();
		Misc misc = new Misc();
		MyGraph graph = new MyGraph();
		MyDirectedGraph dg = new MyDirectedGraph();
		Markov markov = new Markov();
		MyStack stack = new MyStack();
		MySorts sorts = new MySorts();
		Misc2 misc2 = new Misc2();
		MyTree2 mt2 = new MyTree2();
		MLinkedList mll2 = new MLinkedList();
		PaginationHelper<Character> ph = new PaginationHelper<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
		JosephusSurvivor js = new JosephusSurvivor();
		//AssemblyInterpreter ai = new AssemblyInterpreter();
		BookParser bp = new BookParser();
		
		try {
			//mt.doStuff();
			//MyHashTable.doStuff();
			//q.doStuff();
			//heap.doStuff();
			//doStuff();
			//sorts.doStuff();
			//misc.doStuff();
			//graph.doStuff();
			//dg.doStuff();
			//markov.doStuff();
			//stack.doStuff();
			//mll.doStuff();
			//misc2.doStuff();
			//mll2.doStuff();
			//mt2.doStuff();
			//mt2.doStuff2();
			//ph.runTests();
			//js.doStuff();
			//AssemblyInterpreter.doStuff();
			//MyHashTable2.doStuff();
			Graphs.doStuff();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int fibSum(int n) {
		int tmp, prev = 0, cur = 1, sum = 1;
		System.out.println(cur);
		for (int i = 0; i < n; i++) {
			tmp = cur;
			cur += prev;
			System.out.println(cur);
			sum += cur;
			prev = tmp;
		}
		return sum;
	}

	public List<String> strComb(String str) {
		int qCount = 0;
		List<String> retList = new ArrayList<>();

		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == '?')
				qCount++;

		String binaryStr = "";
		for (int i = 0; i < qCount; i++)
			binaryStr += "1";

		int target = Integer.parseInt(binaryStr, 2);
		for (int i = 0; i < target; i++) {
			byte[] bytes = str.getBytes();
			int k = 0;
			for (int j = 0; j < str.length(); j++) {
				if (bytes[j] == '?')
					bytes[j] = binaryStr.getBytes()[k++];
			}
			retList.add(new String(bytes));
			int val = Integer.parseInt(binaryStr, 2) - 1;
			binaryStr = Integer.toBinaryString(val);
			while (binaryStr.length() != qCount)
				binaryStr = "0" + binaryStr;
		}
		return retList;
	}

	public static int[] foldArray(int[] array, int runs) {
		int i, j, size;
		int[] arr = Arrays.copyOf(array, array.length);
		int[] fold = new int[0];

		for (j = 0; j < runs; j++) {
			size = arr.length / 2;
			if (arr.length % 2 == 1)
				size++;
			fold = Arrays.copyOf(arr, size);
			for (i = 0; i < arr.length / 2; i++) {
				fold[i] += arr[arr.length - i - 1];
			}
			arr = Arrays.copyOf(fold, size);
		}
		return fold;
	}

	public static int perimeter(int n) {

		System.out.println((fibonacci(n) * 4) + " ");

		return 0;
	}

	public static int fibonacci(int n) {
		int val = 1, prev = 0, sum = 0, tmp;

		for (int i = 0; i <= n; i++) {
			sum += val;
			tmp = val;
			val += prev;
			prev = tmp;
		}
		return sum;
	}

	public static long[] gap(int g, long m, long n) {
        boolean[] primes = sieve((int)n);
        int prev = -1, start = -1;
        for (int i = ((int)m); i < primes.length; i++)
            if (primes[i]) {
                prev = i;
                break;
            }
        
        for (int i = prev; i <= n - g; i++) {
            if (!primes[i] || !primes[i + g])
                continue;
            boolean inbetween = false;
            for (int j = i + 1; j < i + g; j++)
            	if (primes[j]) {
            		inbetween = true;
            		break;
            	}
            if (inbetween)
            	continue;
            start = i;
            break;
        }
        
        if (start == -1)
        	return null;
        
        long[] res = new long[2];
        res[0] = start;
        res[1] = start + g;
        return res;
    }
    
    public static boolean[] sieve(int end) {
        boolean[] nums = new boolean[end + 1];
        
        for (int i = 0; i <= end; i++)
            nums[i] = true;
        
        for (int i = 2; i < end / 2; i++) {
            for (int j = i + 1; j < end; j++) {
                if (j % i == 0) {
                    nums[j] = false;
                }
            }
        }
        return nums;
    }

	private void bubbleSort(List<Integer> list) {
		for (int i = list.size(); i >= 0; i--) {
			for (int j = i; j < list.size() - 1; j++) {
				if (list.get(j) > list.get(j + 1)) {
					int tmp = list.remove(j);
					list.add(j + 1, tmp);
				}
			}
		}
	}

	public String AlphabetSoup(String str) {

		byte[] bytes = bubbleSort(str.getBytes());

		return new String(bytes);

	}

	private byte[] bubbleSort(byte[] arr) {
		for (int i = arr.length; i >= 0; i--) {
			for (int j = i; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					byte tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
		return arr;
	}

	public static int dontGiveMeFive(int start, int end) {
		int count = 0;
		for (int i = start; i <= end; i++) {
			if (!String.valueOf(i).contains("5"))
				count++;
		}
		return count;
	}

}
