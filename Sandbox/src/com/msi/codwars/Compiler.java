package com.msi.codwars;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {
	public List<String> compile(String prog) {
		return pass3(pass2(pass1(prog)));
	}

	/**
	 * Returns an un-optimized AST
	 */
	//String prog = "[ x y ] ( x + y ) / 2";
	public Ast pass1(String prog) {
		Deque<String> tokens = tokenize(prog);
		HashMap<String, Integer> args = parseArgs(tokens);
		
		
		
		return null;
	}
	
	private HashMap<String, Integer> parseArgs(Deque<String> tokens) {
		HashMap<String, Integer> args = new HashMap<>();
		int i = 0;
		
		while (!tokens.isEmpty()) {
			String tok = tokens.poll();
			if (tok.equals("["))
				continue;
			if (tok.equals("]"))
				break;
			args.put(tok, i++);
		}
		return args;
	}

	/**
	 * Returns an AST with constant expressions reduced
	 */
	public Ast pass2(Ast ast) {
		return null;
	}

	/**
	 * Returns assembly instructions
	 */
	public List<String> pass3(Ast ast) {
		return null;
	}

	private static Deque<String> tokenize(String prog) {
		Deque<String> tokens = new LinkedList<>();
		Pattern pattern = Pattern.compile("[-+*/()\\[\\]]|[a-zA-Z]+|\\d+");
		Matcher m = pattern.matcher(prog);
		while (m.find()) {
			tokens.add(m.group());
		}
		tokens.add("$"); // end-of-stream
		return tokens;
	}
	
	public static class BinOp implements Ast {
		public String json;
		
		public BinOp(String s, Ast a, Ast b) {
			
		}
		
		Ast a() {
			return null;
		}
		
		Ast b() {
			return null;
		}

		@Override
		public String op() {
			return json;
		}
		
	}
	
	public static class UnOp implements Ast {
		public String json;
		
		public UnOp(String s, int i) {
			
		}
		
		int n() {
			return 0;
		}

		@Override
		public String op() {
			return json;
		}
		
	}
	
	public interface Ast {
		String op();
	}
}
