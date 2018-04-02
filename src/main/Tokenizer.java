package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tokenizer {
	
	Path path;
	Scanner sc;
	String token;
	String line = "";
	protected List<String> linesToParse = new ArrayList<String>();
	String command;
	List<String> lines;
	protected List<String> tokens = new ArrayList<String>();
	Boolean addString = true;
	String lineToParse = "";
	
	public Tokenizer(Path path)throws FileNotFoundException {
		this.path = path;
		try {
			lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			
			 for(String s : lines) {
				 String l = s.trim();
				 if (l.contains("/**") && !l.contains("*/")){
					 addString = false;
					 continue;
				 }
				 if (l.contains("*/")) {
					 addString = true;
					 continue;
				 }
				 if (!l.startsWith("//") && l != null && !l.isEmpty() && addString == true){
					 linesToParse.add(l);
					 //System.out.println(jenny);
					 l.trim();
					 lineToParse = lineToParse + l + " ";
				 }
			 }
			 //System.out.println(lineToParse);
			 for(String s : linesToParse) {
				 //System.out.println(s);
			 }
			 String s = "";
			 for (int i = 0 ; i < lineToParse.length() ; i++) {
				 char c = lineToParse.charAt(i);
				 if (c == ' ') {
					 if (!s.isEmpty()) {
						 tokens.add(s.trim());
						 s = "";
					 }
				 }
				 if (c == '(' || c == ')' || c == ';' || c == ',' || c == '.') {
					 if (!s.isEmpty()) {
						 tokens.add(s.trim());
						 s = "";
					 }
					 tokens.add("" + c);
						 s = "";
				 } else {
					 s = s + c; 
				 }
				 //System.out.println(c);
			 }
			 //System.out.println(s);
			 
			 for(String t : tokens) {
				 System.out.println(t);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(tokens.toString());
	    }
	
	
	
	public boolean hasMoreTokens() {
		return !tokens.isEmpty();
	}
	
	public void advance() {
		
	}
	
	public String tokenType() {
		return "tokentype";
	}
	
	public String keyWord() {
		return "keyword";
	}
	
	public char symbol() {
		char c = 'x';
		return c;
	}
	
	public String identifier() {
		return "identifier";
	}
	
	public int intVal() {
		return 1;
	}
	
	public String stringVal() {
		return "String";
	}

}
