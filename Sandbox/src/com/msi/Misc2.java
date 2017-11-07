package com.msi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Misc2 {
	private final static Random rand = new Random();
	
	public void doStuff() throws Exception {
		//fibbSumAndSequence(10);
		/*String word = "tacocat";
		System.out.println(word + (isPalidrome(word) ? " is" : " isn't") + " a palindrome");
		word = "alkhwgohiw";
		System.out.println(word + (isPalidrome(word) ? " is" : " isn't") + " a palindrome");
		int n = 1135311;
		System.out.println(n + (isPalidrome(n) ? " is" : " isn't") + " a palindrome");
		n = 123456;
		System.out.println(n + (isPalidrome(n) ? " is" : " isn't") + " a palindrome");*/
		//listPrimes(100);
		//System.out.println("");
		//findFirstNPrimes(100);
		//System.out.println("The longest palindrome in \"a abcba palindrome tacocat is a palindrome\" is " + longestPalindrome("a abcba palindrome tacocat is a palindrome"));
		/*ArrayList<Integer> l1 = new ArrayList<>();
		ArrayList<Integer> l2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			l1.add(rand.nextInt(10));
			l2.add(rand.nextInt(10));
		}
		uniqueElements(l1, l2);*/
		//removeDuplicates("this is a test string");
		//int[] arr = new int[]{3, 9, 8, 2, 5, 1, 10, 6, 7, 0}; // missing 4
		//findMissingNumber(arr);
		//largestContiguousSum(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
		//System.out.println(sudokuValidate(sudokuboard));
		/*Map<String,Double> map = damagedOrSunk(board, attacks);
		for (Map.Entry<String, Double> en : map.entrySet()) {
			System.out.println(en.getKey() + ": " + en.getValue());
		}*/
		/*int[] arr = deleteNth(new int[] {1,1,3,3,7,2,2,2,2}, 3); // return [1, 1, 3, 3, 7, 2, 2, 2]
		for (int i = 0; i < arr.length; i++) 
			System.out.print(arr[i] + " ");*/
		//permutations("abcd", 0, 4);
		/*System.out.println("Fixed Tests scramble");
        System.out.println(scramble("rkqodlw","world"));
        System.out.println(scramble("cedewaraaossoqqyt","codewars"));
        System.out.println(scramble("katas","steak"));
        System.out.println(scramble("scriptjavx","javascript"));
        System.out.println(scramble("scriptingjava","javascript"));
        System.out.println(scramble("scriptsjava","javascripts"));
        System.out.println(scramble("javscripts","javascript"));
        System.out.println(scramble("aabbcamaomsccdd","commas"));
        System.out.println(scramble("commas","commas"));
        System.out.println(scramble("sammoc","commas"));*/
		/*int[] arr = Interval(new int[] {-4909,-4908,-4907,4,5}, "[-4908,-3743)");
		for (int i : arr)
			System.out.print(i + " ");*/
		/*int[] exampleTest2 = {206847684,1056521,7,17,1901,21104421,7,1,35521,1,7781};
		int[] exampleTest1 = {2,6,8,-10,3}; 
		int[] exampleTest3 = {Integer.MAX_VALUE, 0, 1};
		System.out.print(find(exampleTest3));*/
		//printFibb(12);
		divWithoutDiv(6, 2);
		divWithoutDiv(7, 2);
		divWithoutDiv(2, 7);
		divWithoutDiv(-6, 2);
		divWithoutDiv(6, -2);
		divWithoutDiv(6, 6);
	}
	
	private void divWithoutDiv(int n, int d) {
		int sum = 0, count = 0;
		boolean isNeg = (n * d) < 0;
		n = Math.abs(n);
		d = Math.abs(d);
		
		if (d == 0)
			throw new IllegalArgumentException();
		
		while (sum < n) {
			sum += d;
			if (sum > n)
				break;
			count++;
		}
		if (isNeg)
			count *= -1;
		System.out.println(n + " / " + d + " = " + count);
	}
	
	private void printFibb(int n) {
		int tmp, prev = 0, val = 1;
		
		for (int i = 0; i < n; i++) {
			System.out.println(val);
			tmp = val;
			val += prev;
			prev = tmp;
		}
	}
	
	private int find(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			if ((nums[i] & 1) != (nums[i - 1] & 1)) {
				int reference = i == 1 ? nums[i + 1] : nums[i - 2];
				if ((nums[i] & 1) == (reference & 1))
					return nums[i - 1];
				return nums[i];
			}
		}
		return -1;
	}
	
	public int[] Interval(int[] arr, String str) {
		List<Integer> list = new ArrayList<>();
		if (str.equals(""))
			return new int[0];
		boolean leftClosed = str.charAt(0) == '[';
		boolean rightClosed = str.charAt(str.length() - 1) == ']';
		String[] spl = str.split(",");
		spl[0] = spl[0].substring(1);
		spl[1] = spl[1].substring(0, spl[1].length() - 1);
		int min = Integer.parseInt(spl[0]);
		int max = Integer.parseInt(spl[1]);
		
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];
			if (val >= min && val <= max) {
				if (val > min && val < max) {
					list.add(val);
				} else if (val == min && leftClosed)
					list.add(val);
				else if (val == max && rightClosed)
					list.add(val);
			}
		}
		Collections.sort(list);
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			res[i] = list.get(i);
		return res;
	}

	private boolean scramble(String str1, String str2) {
		HashMap<Character, Integer> map1 = new HashMap<>();
		HashMap<Character, Integer> map2 = new HashMap<>();
		
		for (int i = 0; i < str1.length(); i++) {
			char c = str1.charAt(i);
			if (!map1.containsKey(c))
				map1.put(c, 1);
			map1.put(c, map1.get(c) + 1);
		}
		for (int i = 0; i < str2.length(); i++) {
			char c = str2.charAt(i);
			if (!map2.containsKey(c))
				map2.put(c, 1);
			map2.put(c, map2.get(c) + 1);
		}
		
		for (Map.Entry<Character, Integer> en : map2.entrySet()) {
			if (!map1.containsKey(en.getKey()) || en.getValue() > map1.get(en.getKey()))
				return false;
		}
		return true;
	}
	
	private void permutations(String s, int start, int end) {
		
		if (start == end) {
			System.out.println(s);
			return;
		}
		
		byte[] arr = s.getBytes();
		byte tmp;
		for (int i = start; i < end; i++) {
			tmp = arr[start];
			arr[start] = arr[i];
			arr[i] = tmp;
			permutations(new String(arr), start+1, end);
			tmp = arr[start];
			arr[start] = arr[i];
			arr[i] = tmp;
		}
		
	}
	
	public int[] deleteNth(int[] elements, int maxOcurrences) {
		ArrayList<Integer> list = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < elements.length; i++) {
			if (!map.containsKey(elements[i]))
				map.put(elements[i], 1);
			else
				map.put(elements[i], map.get(elements[i]) + 1);
			if (map.get(elements[i]) <= maxOcurrences)
				list.add(elements[i]);
		}
		int[] arr = new int[list.size()];
		for (int i = 0; i < arr.length; i++)
			arr[i] = list.get(i);
		return arr;
	}
	
	/*int[][] board   = new int[][] {new int[] {0,0,1,0},
        						   new int[] {0,0,1,0},
        						   new int[] {0,0,1,0}};
    int[][] attacks = new int[][] {new int[] {3,1},new int[] {3,2},new int[] {3,3}};*/
    int[][] board   = new int[][] {new int[] {3, 0, 1},
        new int[] {3, 0, 1},
        new int[] {0, 2, 1},
        new int[] {0, 2, 0}};
    int[][] attacks = new int[][] {new int[] {2,1},new int[] {2,2},new int[] {3,2},new int[] {3,3}};
    
    private Map<String,Double> damagedOrSunk(final int[][] board, final int[][] attacks) {
    	Map<String, Double> map = new HashMap<>();
    	double sunk = 0, damaged = 0, notTouched = 0, points = 0;
    	Stack<int[]> stack = new Stack<>();
    	ArrayList<Boat> boats = new ArrayList<>();
    	boolean[][] visited = new boolean[board.length][board[0].length];
    	
    	while (!allVisited(visited)) {
    		Boat boat = null;
    		for (int i = 0; i < board.length; i++) {
    			for (int j = 0; j < board[i].length; j++) {
    				if (!visited[i][j]) {
	    				visited[i][j] = true;
	    				if (board[i][j] > 0) { // if a boat is found
	    					stack.push(new int[]{i, j}); // add the coordinate to the stack
	    					boolean found = false; // create a new boat object if needed
	    					for (int k = 0; k < boats.size(); k++)
	    						if (boats.get(k).which == board[i][j]) {
	    							found = true;
	    							boat = boats.get(k);
	    						}
	    					if (!found) {
	    						boat = new Boat(board[i][j]);
	    						boats.add(boat);
	    					}
	    					break;
	    				}
    				}
    			}
    			if (!stack.isEmpty())
    				break;
    		}
    		
    		while (!stack.isEmpty()) {
    			int x = stack.peek()[0];
    			int y = stack.pop()[1];
    			boat.coordinates.add(new int[]{y + 1, board.length - x});
    			visited[x][y] = true;
    			
    			if (x > 0 && !visited[x - 1][y] && board[x - 1][y] == boat.which) // left
    				stack.push(new int[]{x - 1, y});
    			if (x > 0 && y > 0 && !visited[x - 1][y - 1] && board[x - 1][y - 1] == boat.which) // left-up
    				stack.push(new int[]{x - 1, y - 1});
    			if (y > 0 && !visited[x][y - 1] && board[x][y - 1] == boat.which) // up
    				stack.push(new int[]{x, y - 1});
    			if (x < board.length - 1 && y > 0 && !visited[x + 1][y - 1] && board[x + 1][y - 1] == boat.which) // right-up
    				stack.push(new int[]{x + 1, y - 1});
    			if (x < board.length - 1 && !visited[x + 1][y] && board[x + 1][y] == boat.which) // right
    				stack.push(new int[]{x + 1, y});
    			if (x < board.length - 1 && y < board[0].length - 1 && !visited[x + 1][y + 1] && board[x + 1][y + 1] == boat.which) // right-down
    				stack.push(new int[]{x + 1, y + 1});
    			if (y < board[0].length - 1 && !visited[x][y + 1] && board[x][y + 1] == boat.which) // down
    				stack.push(new int[]{x, y + 1});
    			if (x > 0 && y < board[0].length - 1 && !visited[x - 1][y + 1] && board[x - 1][y + 1] == boat.which) // left-down
    				stack.push(new int[]{x - 1, y + 1});
    		}
    	}
    	
    	// now we've index all the boats, let's process the attacks
    	for (Boat b : boats) {
    		for (int[] attack : attacks) {
    			for (int[] coord : b.coordinates) {
    				if (attack[0] == coord[0] && attack[1] == coord[1]) {
    					b.hits++;
    					break;
    				}
    			}
    		}
    		if (b.hits == b.coordinates.size())
    			sunk++;
    		else if (b.hits > 0)
    			damaged++;
    		else
    			notTouched++;
    	}
    	points = sunk + (0.5 * damaged) - notTouched;
    	
    	map.put("sunk", sunk);
    	map.put("damaged", damaged);
    	map.put("notTouched", notTouched);
    	map.put("points", points);
    	return map;
    }
    
    private static class Boat {
    	private ArrayList<int[]> coordinates = new ArrayList<>();
    	private int which, hits;
    	
    	private Boat(int which) {
    		this.which = which;
    	}
    	
    	@Override
    	public boolean equals(Object o) {
    		if (!(o instanceof Boat))
    			return false;
    		if (o == this)
    			return true;
    		
    		Boat b = (Boat)o;
    		return which == b.which;
    	}
    }
    
    private boolean allVisited(boolean[][] arr) {
    	for (int i = 0; i < arr.length; i++)
    		for (int j = 0; j < arr[i].length; j++)
    			if (!arr[i][j])
    				return false;
    	return true;
    }
	
	int[][] sudokuboard = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };
	
	private boolean sudokuValidate(int[][] sudoku) {
		HashSet<Integer> set1 = new HashSet<>();
		HashSet<Integer> set2 = new HashSet<>();
		HashSet<Integer> set3 = new HashSet<>();
		
		for (int i = 0; i < 9; i++) {
			set1.clear();
			set2.clear();
			for (int j = 0; j < 9; j++) {
				if (sudoku[i][j] == 0 || sudoku[j][i] == 0)
					return false;
				// check rows
				set1.add(sudoku[i][j]);
				// check columns
				set2.add(sudoku[j][i]);
			}
			if (set1.size() != 9 || set2.size() != 9)
				return false;
		}
		
		int a = 0, b = 0;
		boolean c = false;
		// check squares
		for (int i = 0; i < 3; i++) {
			set1.clear();
			set2.clear();
			set3.clear();
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					set1.add(sudoku[j + a][k + b]);
					set2.add(sudoku[j][k + b]);
					set3.add(sudoku[j + a][k]);
				}
			}
			if (set1.size() != 9 || set2.size() != 9 || set3.size() != 9)
				return false;
			if (c)
				a += 3;
			else
				b += 3;
			c = !c;
		}
		return true;
	}
	
	private void largestContiguousSum(int[] arr) {
		int curMax = 0, endMax = 0;
		
		for (int i = 0; i < arr.length; i++) {
			endMax += arr[i];
			if (endMax < 0)
				endMax = 0;
			if (curMax < endMax)
				curMax = endMax;
		}
		System.out.println("Max sum: " + curMax);
	}
	
	private void findMissingNumber(int[] arr) {
		Arrays.sort(arr);
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != i) {
				System.out.println("The missing number is " + i);
				return;
			}
		}
	}
	
	private String longestPalindrome(String str) {
		//int maxLen = 0;
		boolean isPalindrome;
		String palindrome = "";
		if (str == null || str.length() == 0)
			return "";
		str = str.toLowerCase();
		
		for (int len = 1; len < str.length(); len++) { // for each length of word
			for (int j = 0; j < str.length() - len + 1; j++) { // for each position of length len in the string
				isPalindrome = true;
				for (int k = 0; k < len; k++) {
					if (str.charAt(k + j) != str.charAt(j + len - k - 1)) {
						isPalindrome = false;
						break;
					}
				}
				if (isPalindrome)
					palindrome = str.substring(j, j + len);
			}
		}
		return palindrome;
	}
	
	private void listPrimes(int n) {
		int val = 2;
		boolean[] primes = new boolean[n];
		Arrays.fill(primes, true);
		
		for (int i = 0; i < Math.sqrt(n); i++) {
			for (int j = val + 1; j < n; j++) {
				if (j % val == 0)
					primes[j] = false;
			}
			for (int j = val + 1; j < n / 2; j++) {
				if (primes[j]) {
					val = j;
					break;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (primes[i])
				System.out.print(i + " ");
		}
	}
	
	private void findFirstNPrimes(int n) {
		int count = 0;
		
		// i - number we're checking for primage
		// j - divisor
		boolean prime;
		System.out.print("0 1 2 ");
		for (int i = 3; count < n - 3; i += 2) {
			prime = true;
			for (int j = 3; j <= i / 2; j += 2) {
				if (i % j == 0) {
					prime = false;
				}
			}
			if (prime) {
				count++;
				System.out.print(i + " ");
			}
		}
	}
	
	private void fibbSumAndSequence(int len) {
		long sum = 0, val = 1, prev = 0, tmp;
		
		for (int i = 0; i < len; i++) {
			System.out.print(val + " ");
			sum += val;
			tmp = val;
			val += prev;
			prev = tmp;
		}
		System.out.println("\n" + sum);
	}
	
	private boolean isPalidrome(String str) {
		byte[] bytes = str.toLowerCase().getBytes();
		for (int i = 0; i < str.length() / 2; i++) {
			if (bytes[i] != bytes[bytes.length - i - 1])
				return false;
		}
		return true;
	}
	
	// 1135311
	private boolean isPalidrome(long n) {
		int len = (int) Math.log10(n) + 1;
		long digit;
		long[] digits = new long[len];
		
		for (int i = 0; i < len; i++) {
			digit = n % ((int) Math.pow(10.0, i + 1));
			digit /= ((int) Math.pow(10.0, i));
			digits[i] = digit;
		}
		for (int i = 0; i < len; i++) {
			if (digits[i] != digits[len - i - 1])
				return false;
		}
		return true;
	}
	
	private void uniqueElements(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		Set<Integer> set = new HashSet<>(list1);
		Set<Integer> unique = new HashSet<>();
		
		for (Integer i : list2) {
			if (set.add(i))
				unique.add(i);
		}
		set = new HashSet<>(list2);
		for (Integer i : list1) {
			if (set.add(i))
				unique.add(i);
		}
		for (int i = 0; i < list1.size(); i++)
			System.out.print(list1.get(i) + " ");
		System.out.println();
		for (int i = 0; i < list2.size(); i++)
			System.out.print(list2.get(i) + " ");
		System.out.println();
		for (Integer i : unique)
			System.out.print(i + " ");
	}
	
	private void removeDuplicates(String s) {
		
		s = s.toLowerCase();
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(j) == c) {
					s = s.substring(0, j) + s.substring(j + 1, s.length());
				}
			}
		}
		System.out.println(s);
	}
	
}
