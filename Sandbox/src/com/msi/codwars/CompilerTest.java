package com.msi.codwars;

import com.msi.codwars.Compiler.*;

import java.util.List;

public class CompilerTest {
	
	public static void main(String[] args) {
		testBasicProg();
		//testSimpleProg();
	}
	
	public static void testBasicProg() {
		String prog = "[ x y ] ( x + y ) / 2";
		Compiler compiler = new Compiler();
		
		// {'op':'/','a':{'op':'+','a':{'op':'arg','n':0},'b':{'op':'arg','n':1}}}
		Ast t1 = new BinOp("/", new BinOp("+", new UnOp("arg", 0), new UnOp("arg", 1)), new UnOp("imm", 2));
		Ast p1 = compiler.pass1(prog);
		System.out.println(t1.op());
		System.out.println(p1.op());
	}

	public static void testSimpleProg() {
		String prog = "[ x y z ] ( 2*3*x + 5*y - 3*z ) / (1 + 3 + 2*2)";
		Compiler compiler = new Compiler();

	    // {'op':'/','a':{'op':'-','a':{'op':'+','a':{'op':'*','a':{'op':'*','a':{'op':'imm','n':2},'b':{'op':'imm','n':3}},'b':{'op':'arg','n':0}},'b':{'op':'*','a':{'op':'imm','n':5},'b':{'op':'arg','n':1}}},'b':{'op':'*','a':{'op':'imm','n':3},'b':{'op':'arg','n':2}}},'b':{'op':'+','a':{'op':'+','a':{'op':'imm','n':1},'b':{'op':'imm','n':3}},'b':{'op':'*','a':{'op':'imm','n':2},'b':{'op':'imm','n':2}}}}
	    Ast t1 = new BinOp("/", new BinOp("-", new BinOp("+", new BinOp("*", new BinOp("*", new UnOp("imm", 2), new UnOp("imm", 3)), new UnOp("arg", 0)), new BinOp("*", new UnOp("imm", 5), new UnOp("arg", 1))), new BinOp("*", new UnOp("imm", 3), new UnOp("arg", 2))), new BinOp("+", new BinOp("+", new UnOp("imm", 1), new UnOp("imm", 3)), new BinOp("*", new UnOp("imm", 2), new UnOp("imm", 2))));
	    Ast p1 = compiler.pass1(prog);
	    System.out.println("Pass 1:\n" + t1 + "\n" + p1);
	    //assertEquals("Pass 1", t1, p1);

	    // {'op':'/','a':{'op':'-','a':{'op':'+','a':{'op':'*','a':{'op':'imm','n':6},'b':{'op':'arg','n':0}},'b':{'op':'*','a':{'op':'imm','n':5},'b':{'op':'arg','n':1}}},'b':{'op':'*','a':{'op':'imm','n':3},'b':{'op':'arg','n':2}}},'b':{'op':'imm','n':8}}
	    Ast t2 = new BinOp("/", new BinOp("-", new BinOp("+", new BinOp("*", new UnOp("imm", 6), new UnOp("arg", 0)), new BinOp("*", new UnOp("imm", 5), new UnOp("arg", 1))), new BinOp("*", new UnOp("imm", 3), new UnOp("arg", 2))), new UnOp("imm", 8));
	    Ast p2 = compiler.pass2(p1);
	    System.out.println("Pass 2:\n" + t1 + "\n" + p1);
	    //assertEquals("Pass 2", t2, p2);

		List<String> p3 = compiler.pass3(p2);
		System.out.println("prog(4,0,0) == 3\n" + Simulator.simulate(p3, 4, 0, 0));
		System.out.println("prog(4,8,0) == 8\n" + Simulator.simulate(p3, 4, 8, 0));
		System.out.println("prog(4,8,16) == 2\n" + Simulator.simulate(p3, 4, 8, 16));
	}
}
