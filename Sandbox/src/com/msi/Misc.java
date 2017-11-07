package com.msi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Misc {
	private final static Random rand = new Random();

	public void doStuff() {
		//for (Integer i : betterUniqueRandom(20, 10))
		//	System.out.print(i + " ");
		System.out.println("The longest palindrome in \"a abcba palindrome tacocat is a palindrome\" is " + longestPalindrome("a abcba palindrome acocat is a palindrome"));
		//List<Interval> list = new ArrayList<>();
		//list.add(new Interval(2, 3));
		//list.add(new Interval(4, 5));
		//list.add(new Interval(6, 7));
		//list.add(new Interval(8, 9));
		//list.add(new Interval(1, 10));
		//List<Interval> res = merge(list);
		//List<String> fl = fixedLength("abcdefggasdfasdfasdfasdfasd", 3);
		//System.out.println(strRev("Hello"));
		//System.out.println("Single element: " + singleEle(new int[]{1, 1, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7}));
		//steppingNums(10, 10000);
		//hanoi(10, "A", "B", "C");
		//infixPostfix("A+B*C");
		//int[] range = searchRange(new int[]{0, 1, 2, 3, 5, 5, 7, 8, 8, 10}, 5);
		//int[] range = searchRange(new int[]{1, 2, 2, 3, 4, 4, 4}, 4);
		//System.out.println(range[0] + ", " + range[1]);
		//System.out.println(numIslands(islands2));
		//System.out.println(isValidSudoku(sudokuBoard));
		//System.out.println(canCompleteCircuit(new int[]{1, 2}, new int[]{2, 1}));
		//System.out.println(isSubsequence("abcx", "ahbgdc"));
		//System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));
		//String[] strs = new String[]{"aabbaa", "abc", "abcabcabcabc", "eerreerr", "abbddab"};
		//System.out.println(mostRepeated(strs));
		//System.out.println(fibSum(10));
		//knapsack();
		//System.out.println(steppingStairs(51));
		//System.out.println(fib(16));
		//System.out.println(fibSum(9));
		int[] arr = new int[]{3, 0, 0, 2, 0 , 4};
		System.out.println(rainwater(arr, arr.length));
		//System.out.println(rainwater(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
	}
	
	public int rainwater(int[] arr, int index) {
		int max = 0, max_index = 0;
		int sum = 0;
		for (int i = 0; i < index; i++) {
			if (arr[i] > max) {
				max = arr[i];
				max_index = i;
			}
		}
		if (max == 0)
			return 0;
		sum += rainwater(arr, max_index);
		int left_max = 0;
		max = 0;
		// left side
		for (int i = 0; i < max_index; i++) {
			if (arr[i] > max) {
				max = arr[i];
				left_max = i;
			}
		}
		if (max > 0) {
			for (int i = left_max + 1; i < max_index; i++) {
				sum += arr[left_max] - arr[i];
			}
		}
		max = 0;
		int right_max = 0;
		// right side
		for (int i = max_index; i < index; i++) {
			if (arr[i] > max) {
				max = arr[i];
				right_max = i;
			}
		}
		if (max > 0) {
			for (int i = max_index + 1; i < right_max; i++) {
				sum += arr[right_max] - arr[i];
			}
		}
		return sum;
	}
	
	public int steppingStairs(int n) {
		int sum = 0;
		int steps = 0;
		while (sum < n) {
			sum += 2;
			steps++;
		}
		return steps;
	}
	
	private long fib(int n) {
		long val = 1, prev = 0, tmp;
		
		for (int i = 1; i < n; i++) {
			tmp = val;
			val += prev;
			prev = tmp;
		}
		return val;
	}
	
	public void knapsack() {
		int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};
        int W = 50;
    	System.out.println(_knapsack(W, wt, val, val.length));
	}
	
	private int _knapsack(int W, int wt[], int val[], int n) {
		if (n == 0)
			return 0;
		if (wt[n - 1] > W)
			return _knapsack(W, wt, val, n - 1);
		else
			return Math.max(val[n - 1] + _knapsack(W - wt[n - 1], wt, val, n - 1), _knapsack(W, wt, val, n - 1));
	}
	
	public int fibSum(int k) {
		int sum = 0;
		int val = 1, prev = 0, tmp;
		
		System.out.print("0 ");
		for (int i = 0; i < k; i++) {
			System.out.print(val + " ");
			sum += val;
			tmp = val;
			val += prev;
			prev = tmp;
		}
		System.out.println();
		return sum;
	}
	
	public String mostRepeated(String[] strs) {
		List<HashMap<String, Integer>> map = new ArrayList<>();
		
		for (int i = 0; i < strs.length; i++) {
			map.add(new HashMap<>());
			for (int j = 0; j < strs[i].length(); j++) {
				for (int k = j + 2; k <= strs[i].length(); k++) {
					String s = strs[i].substring(j, k);
					if (map.get(i).get(s) == null)
						map.get(i).put(s, 1);
					else
						map.get(i).put(s, map.get(i).get(s) + 1);
				}
			}
		}
		int max = 0, index = 0;
		String key = "";
		for (int i = 0; i < map.size(); i++) {
			for (Map.Entry<String, Integer> en : map.get(i).entrySet()) {
				if (en.getValue() > max) {
					max = en.getValue();
					key = en.getKey();
					index = i;
				}
			}
		}
		System.out.println(key);
		return strs[index];
	}
	
	public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {
        	int mid = (low + high) >>> 1;
        	if (mid < nums.length - 1 && nums[mid] < nums[mid + 1]) { // mid+1 is increasing
        		low = mid + 1;
        	} else if (mid > 0 && nums[mid] < nums[mid - 1]) { // mid - 1 is increasing
        		high = mid - 1;
        	} else // found peak
        		return mid;
        }
        return -1;
    }
	
	public boolean isSubsequence(String s, String t) {
		if (s.length() == 0)
			return true;
		if (t.length() == 0)
			return false;
        int ptrT = 0, ptrS = 0; // i belongs to t, j belongs to s
		while (true) {
			while (ptrT < t.length() && t.charAt(ptrT) != s.charAt(ptrS))
				ptrT++;
			if (ptrT == t.length())
				return false;
			ptrS++;
			ptrT++;
			if (ptrS == s.length())
				return true;
		}
    }
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas.length == 0 || cost.length == 0)
			return -1;
		int curGas = 0;
		
		for (int i = 0; i < cost.length; i++) {
			curGas += gas[i];
			curGas -= cost[i];
			if (curGas < 0)
				return -1;
		}
		
        return 0;
    }
	
	public double myPow(double x, int n) {
		if (n == 0)
			return 1;
		if (n < 0) {
			n *= -1;
			x = 1 / x;
		}
		if (n % 2 == 0)
			return myPow(x * x, n / 2);
		else
			return myPow(x * x, n / 2) * x;
	}
	
	private final byte[][] sudokuBoard = 
		{
				{5, 3, 0, 0, 7, 0, 0, 0, 0},
				{6, 0, 0, 1, 9, 5, 0, 0, 0},
				{0, 9, 8, 0, 0, 0, 0, 6, 0},
				{8, 0, 0, 0, 6, 0, 0, 0, 3},
				{4, 0, 0, 8, 0, 3, 0, 0, 1},
				{7, 0, 0, 0, 2, 0, 0, 0, 6},
				{0, 6, 0, 0, 0, 0, 2, 8, 0},
				{0, 0, 0, 4, 1, 9, 0, 0, 5},
				{0, 0, 0, 0, 8, 0, 0, 7, 9}
		};
	
	public boolean isValidSudoku(byte[][] board) {
        HashSet<Byte> set = new HashSet<>();
        
        // check rows
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board.length; j++) {
        		if (board[i][j] != 0 && !set.add(board[i][j]))
        			return false;
        	}
        	set.clear();
        }
        
        // check columns
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board.length; j++) {
        		if (board[j][i] != 0 && !set.add(board[j][i]))
        			return false;
        	}
        	set.clear();
        }
        
        // check boxes
        int h = 0, v = 0;
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < 3; j++) {
        		for (int k = 0; k < 3; k++) {
        			if (board[j + h][k + v] != 0 && !set.add(board[j + h][k + v]))
        				return false;
        		}
        	}
        	set.clear();
        	h += 3;
        	v += 3;
        	if (h >= 9)
        		h = 0;
        	if (v >= 9)
        		v = 0;
        }
        return true;
    }
	
	private final char[][] islands = 
		{
				{1, 1, 1, 1, 1},
				{1, 1, 0, 0, 1},
				{0, 0, 0, 1, 1},
				{0, 1, 0, 0, 0},
				{0, 0, 0, 1, 0}
		};
	
	private final char[][] islands2 = 
		{
				{1, 1, 1, 1, 0},
				{1, 1, 0, 1, 0},
				{1, 1, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}
		};
	
	public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Stack<Point> stack = new Stack<>();
        int numIslands = 0;
        
        while (!allVisited(visited)) {
        	// find first piece of land
        	if (stack.isEmpty())
	            for (int i = 0; i < grid.length; i++) {
	            	for (int j = 0; j < grid[i].length; j++) {
	            		if (visited[i][j])
	            			continue;
	            		visited[i][j] = true;
	            		if (grid[i][j] == 1) {
	            			stack.push(new Point(j, i));
	            			break;
	            		}
	            	}
	            	if (!stack.isEmpty())
	            		break;
	            }
	            
            if (stack.isEmpty())
            	return numIslands;
        	
            if (!stack.isEmpty()) {
	        	Point p = stack.pop();
	        	// check each direction
	        	// N
	        	if (p.y > 0 && !visited[p.y - 1][p.x] && grid[p.y - 1][p.x] == 1) {
	        		stack.push(new Point(p.y - 1, p.x));
	        		visited[p.y - 1][p.x] = true;
	        	}
	        	// S
	        	if (p.y < grid.length - 1 && !visited[p.y + 1][p.x] && grid[p.y + 1][p.x] == 1) {
	        		stack.push(new Point(p.y + 1, p.x));
	        		visited[p.y + 1][p.x] = true;
	        	}
	        	// E
	        	if (p.x < grid[p.y].length - 1 && !visited[p.y][p.x + 1] && grid[p.y][p.x + 1] == 1) {
	        		stack.push(new Point(p.y, p.x + 1));
	        		visited[p.y][p.x + 1] = true;
	        	}
	        	// W
	        	if (p.x > 0 && !visited[p.y][p.x - 1] && grid[p.y][p.x - 1] == 1) {
	        		stack.push(new Point(p.y, p.x - 1));
	        		visited[p.y][p.x - 1] = true;
	        	}
            }
        	if (stack.isEmpty())
        		numIslands++;
        }
        return numIslands;
    }
	
	private boolean allVisited(boolean[][] grid) {
		for (int i = 0; i < grid.length; i++)
        	for (int j = 0; j < grid[i].length; j++)
        		if (!grid[i][j])
        			return false;
		return true;
	}
	
	private static class Point {
		int x, y;
		
		Point(int x, int y) {
			this.y = x;
			this.x = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			if (!(o instanceof Point))
				return false;
			Point p = (Point) o;
			return x == p.x && y == p.y;
		}
		
		@Override
		public String toString() {
			return x + ", " + y;
		}
	}
	
	public int[] searchRange(int[] nums, int target) {
		if (nums.length == 0)
            return new int[]{-1, -1};
		int pos = nums.length / 2, min = 0, max = nums.length;
		int start, end;
		HashSet<Integer> set = new HashSet<>();
		// find start value
		do {
			System.out.print(pos + " ");
			if (target < nums[pos]) {
				max = pos;
				pos -= Math.max((max - min) / 2, 1);
			} else if (target > nums[pos]) {
				min = pos;
				pos += Math.max((max - min) / 2, 1);
			} else if (target == nums[pos]) {
				if (pos == 0 || nums[pos - 1] != target)
					break;
				max = pos;
				pos -= Math.max((max - min) / 2, 1);
			}
			if (pos >= nums.length || pos < 0)
				return new int[]{-1, -1};
			if (!set.add(pos))
				return new int[]{-1, -1};
		} while (true);
		start = pos;
		System.out.println();
		pos = nums.length / 2;
		min = 0;
		max = nums.length;
		set.clear();
		do {
			System.out.print(pos + " ");
			if (target < nums[pos]) {
				max = pos;
				pos -= Math.max((max - min) / 2, 1);
			} else if (target > nums[pos]) {
				min = pos;
				pos += Math.max((max - min) / 2, 1);
			} else if (target == nums[pos]) {
				if (pos == nums.length - 1 || nums[pos + 1] != target)
					break;
				min = pos;
				pos += (max - min) / 2;
			}
			if (pos >= nums.length || pos < 0)
				return new int[]{-1, -1};
			if (!set.add(pos))
				return new int[]{-1, -1};
		} while (true);
		end = pos;
		
		return new int[]{start, end};
	}
	
	public void infixPostfix(String s) {
		String exp = "";
		Stack<Character> operands = new Stack<>();
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				if (operands.isEmpty())
					operands.push(c);
				else if ((c == '-' || c == '+') && (operands.peek() == '*' || operands.peek() == '/')){
					exp += operands.pop();
					operands.push(c);
				} else {
					operands.push(c);
				}
			} else
				exp += c;
		}
		while (!operands.isEmpty())
			exp += operands.pop();
		
		System.out.println(exp);
	}
	
	public void hanoi(int discs, String a, String b, String c) {
		if (discs == 1)
			System.out.println(a + " -> " + c);
		else {
			hanoi(discs - 1, a, c, b);
			System.out.println(a + " -> " + c);
			hanoi(discs - 1, b, a, c);
		}
	}
	
	public void steppingNums(int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			if (isStepping(i))
				System.out.print(i + " ");
		}
		System.out.println();
	}
	
	private boolean isStepping(int n) {
		int[] digits = new int[(int)(Math.log10(n) + 1)];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = n % 10;
			n /= 10;
		}
		for (int i = 0; i < digits.length - 1; i++) {
			if (Math.abs(digits[i] - digits[i + 1]) > 1)
				return false;
		}
		return true;
	}
	
	private int sum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum += arr[i];
		return sum;
	}
	
	public int singleEle(int[] arr) {
		for (int i = 0; i < arr.length; i+=2) {
			if (i + 1 >= arr.length)
				return arr[i];
			if (arr[i] != arr[i + 1])
				return arr[i];
		}
		/*HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++)
			map.put(arr[i], map.get(arr[i]) == null ? 1 : map.get(arr[i]) + 1);
		for (Map.Entry<Integer, Integer> en : map.entrySet())
			if (en.getValue() == 1)
				return en.getKey();*/
		return -1;
	}
	
	private boolean isPalindrome(long n) {
		int len = (int) (Math.log10(n) + 1);
		byte[] digits = new byte[len];
		for (int i = 0; i < len; i++) {
			digits[i] = (byte) (n % 10);
			n /= 10L;
		}
		for (int i = 0; i < len / 2; i++)
			if (digits[i] != digits[len - i - 1])
				return false;
		return true;
	}
	
	public String strRev(String s) {
		return new String(rev(s.getBytes(), 0));
	}
	
	private byte[] rev(byte[] s, int i) {
		if (i == s.length / 2)
			return s;
		byte b = s[i];
		s[i] = s[s.length - i - 1];
		s[s.length - i - 1] = b;
		return rev(s, i + 1);
	}
	
	public List<String> fixedLength(String str, int len) {
        List<String> res = new ArrayList<>();
        int iterations = str.length() / len;
        if (str.length() % len != 0)
        	iterations++;
        
        for (int i = 0; i < iterations; i++) {
            if ((i + 1) * len >= str.length())
                res.add(str.substring(i * len));
            else
                res.add(str.substring(i * len, (i + 1) * len));
        }
        return res;
    }
	
	public List<Interval> merge(List<Interval> intervals) {
        for (int i = 0; i < intervals.size(); i++) {
            Interval i1 = intervals.get(i);
            for (int j = intervals.size() - 1; j >= 0; j--) {
                Interval i2 = intervals.get(j);
                if (i1.equals(i2))
                	continue;
                if (i1.end >= i2.start && i1.start <= i2.end) {
                    i1.end = i2.end > i1.end ? i2.end : i1.end;
                    i1.start = i1.start < i2.start ? i1.start : i2.start;
                    intervals.remove(j);
                    if (j > intervals.size())
                    	j--;
                }
            }
        }
        
        return intervals;
	}

	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	private void uniqueElements() {
		List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			l1.add(rand.nextInt(10));
			l2.add(rand.nextInt(10));
		}
		System.out.println("List 1");
		for (int i = 0; i < 20; i++) {
			System.out.print(l1.get(i) + " ");
		}
		System.out.println("\nList 2");
		for (int i = 0; i < 20; i++) {
			System.out.print(l2.get(i) + " ");
		}

		HashMap<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();

		// O(n) = O(n^2) due to remove() taking O(n) time
		for (int i = 0; i < l1.size(); i++) {
			int i1 = l1.get(i);
			int i2 = l2.get(i);
			if (l1.remove((Object) i2))
				l2.remove((Object) i2);
			if (l2.remove((Object) i1))
				l1.remove((Object) i1);
		}
		System.out.println("\nUnique elements in l1");
		for (Integer i : l1)
			System.out.print(i + " ");
		System.out.println();
		System.out.println("Unique elements in l2");
		for (Integer i : l2)
			System.out.print(i + " ");

		// O(n) is O(n) runtime with O(2n) space
		/*for (int i = 0; i < l1.size(); i++) {
			int i1 = l1.get(i);
			int i2 = l2.get(i);
			// add l1 in map1
			if (map1.get(i1) == null)
				map1.put(i1, 1);
			else
				map1.put(i1, map1.get(i1) + 1);

			// add l2 in map2
			if (map2.get(i2) == null)
				map2.put(i2, 1);
			else
				map2.put(i2, map2.get(i2) + 1);

			// subtract l1 in map2
			if (map2.get(i1) == null)
				map2.put(i1, -1);
			else
				map2.put(i1, map2.get(i1) - 1);

			// subtract l2 in map1
			if (map1.get(i2) == null)
				map1.put(i2, -1);
			else
				map1.put(i2, map1.get(i2) - 1);
		}
		// map1 is now a list of elements not in l2 where map1.value > 0 and
		// vice versa
		System.out.println("\nUnique elements in l1");
		for (Map.Entry<Integer, Integer> entry : map1.entrySet())
			for (int i = 0; i < entry.getValue(); i++)
				System.out.print(entry.getKey() + " ");
		System.out.println();
		System.out.println("Unique elements in l2");
		for (Map.Entry<Integer, Integer> entry : map2.entrySet())
			for (int i = 0; i < entry.getValue(); i++)
				System.out.print(entry.getKey() + " ");*/
	}

	private Set<Integer> uniqueRandom(int max, int count) {
		if (count > max)
			throw new IllegalStateException("Invalid parameters");
		Set<Integer> set = new HashSet<>();

		int i = 0;

		while (i < count)
			if (set.add(rand.nextInt(max)))
				i++;

		return set;
	}
	
	public static ArrayList<Integer> betterUniqueRandom(int max, int count) {
		List<Integer> list = new ArrayList<>();
		//Set<Integer> set = new HashSet<>();
		ArrayList<Integer> set = new ArrayList<>();

		for (int i = 0; i < max; i++)
			list.add(i);

		for (int i = 0; i < count; i++) {
			int r = rand.nextInt(list.size());
			set.add(list.get(r));
			list.remove(r);
		}
		return set;
	}

	private String longestPalindrome(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return "";
		String longestPal = "";
		int longestLength = 0;
		str = str.toLowerCase();

		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				String pal = str.substring(i, j);
				if (isPalindrome(pal)) {
					if (pal.length() > longestLength) {
						longestPal = pal;
						longestLength = pal.length();
					}
				}
			}
		}
		return longestPal;
	}
	
	private boolean isPalindrome(String s) {
		for (int i = 0; i < s.length() / 2; i++)
			if (s.charAt(i) != s.charAt(s.length() - i - 1))
				return false;
		
		return true;
	}
}
