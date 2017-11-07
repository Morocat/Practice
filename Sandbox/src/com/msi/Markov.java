package com.msi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rita.RiMarkov;

public class Markov {

	public void doStuff() {
		try {
			RiMarkov m = new RiMarkov(3);
			m.loadText(loadFile("history trimmed.txt"));
			System.out.println(m.generateSentence());
//			for (String s : m.generateSentences(4)) {
//				
//			}
//			String s = loadFile("history.txt");
//			writeFile(s);
//			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private final Pattern p = Pattern.compile("\\[\\[(.*)\\]\\]");
	private final Pattern nonAlpha = Pattern.compile("([a-z]|[A-Z]).*");

	private String loadFile(String name) throws IOException {
		FileReader fr = new FileReader(new File(name));
		BufferedReader br = new BufferedReader(fr);
		String line, parsed;
		String s = "";
		Matcher m, n;
		while((line = br.readLine()) != null) {
			if (line.equals(""))
				continue;
			
			if (line.length() > 22) {
				if (line.contains(": ") && line.contains(", ")) {
					parsed = line.substring(line.indexOf(": ") + 2);
				} else {
					parsed = line;
				}
			} else {
				parsed = line;
			}
			
			m = p.matcher(parsed);
			if (m.matches()) // skip stickers, geolocations, etc
				continue;
			n = nonAlpha.matcher(parsed);
			if (!n.matches())
				continue;
			s += parsed + "\n";
		}
		br.close();
		return s;
	}
	
	private void writeFile(String contents) throws IOException {
		FileWriter fw = new FileWriter(new File("history trimmed.txt"));
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(contents);
		bw.close();
	}

}
