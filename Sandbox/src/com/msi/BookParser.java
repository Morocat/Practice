package com.msi;

import java.io.File;
import java.io.FileInputStream;

public class BookParser {
	
	public static void main(String[] args) {
		BookParser bp = new BookParser();
	}
	
	private String[] words;
	
	public BookParser() {
		File file = new File("book.txt");
		FileInputStream fis;
		byte[] buf = new byte[1024];
		String contents = "";
		try {
			fis = new FileInputStream(file);
			while (fis.read(buf) != -1)
				contents += new String(buf);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		contents.replaceAll("[^a-zA-Z\\s]", ""); // remove all non-letter non-space chars
		words = contents.split(" ");
	}
	
	
}
