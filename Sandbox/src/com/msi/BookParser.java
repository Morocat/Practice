package com.msi;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class BookParser {

    public static void main(String[] args) {
        BookParser bp = new BookParser();
        long timeS, timeE;
        try {
            timeS = System.nanoTime();
            bp.expParse();
            timeE = System.nanoTime();
            System.out.println("Naive parse: " + ((timeE - timeS) / 1000000) + "ms");
            timeS = System.nanoTime();
            bp.betterParse();
            timeE = System.nanoTime();
            System.out.println("Tree parse: " + ((timeE - timeS) / 1000000) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final String[] words;

    private HashMap<Integer, List<byte[]>> map = new HashMap<>();
    private HashMap<Integer, MyTree> treeMap = new HashMap<>();

    private BookParser() {
        File file = new File("book.txt");
        FileInputStream fis;
        byte[] buf = new byte[1024];
        StringBuilder contents = new StringBuilder("");
        try {
            fis = new FileInputStream(file);
            while (fis.read(buf) != -1)
                contents.append(new String(buf));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String book = contents.toString().replaceAll("[^a-zA-Z\\s]", ""); // remove all non-letter non-space chars
        book = book.replaceAll("[\\n\\r]", "");
        words = book.split(" ");
    }

    private void betterParse() throws Exception {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            int len = word.length();
            if (len <= 1)
                continue;
            treeMap.computeIfAbsent(len, k -> new MyTree());
            MyTree tree = treeMap.get(len);
            tree.addNode(word.getBytes());
            count.putIfAbsent(len, 0);
            count.put(len, count.get(len) + 1);
        }
        /*for (Map.Entry<Integer, Integer> en : count.entrySet()) {
            System.out.println(en.getKey() + " letter words: " + en.getValue());
        }*/
    }

    private void expParse() throws Exception {
        int anagramCount;
        // add every word to a list based on word length
        for (String word : words) {
            word = word.toLowerCase();
            int len = word.length();
            if (len <= 1)
                continue;
            map.computeIfAbsent(len, k -> new ArrayList<>());
            byte[] arr = new byte[26];
            for (int i = 0; i < word.length(); i++)
                if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z')
                    arr[word.charAt(i) - 'a']++;

            map.get(len).add(arr);
        }

        for (Map.Entry<Integer, List<byte[]>> en : map.entrySet()) { // for each word length
            List<byte[]> words = en.getValue();
            anagramCount = 0;
            for (int j = 0; j < words.size(); j++) { // for each word of that length
                byte[] arr = words.get(j);
                for (int k = j + 1; k < words.size(); k++) {
                    if (Arrays.equals(arr, words.get(k))) {
                        words.remove(k--);
                        anagramCount++;
                    }
                }
            }
            //System.out.println(en.getKey() + " letter words: " + anagramCount);
        }
    }

}
