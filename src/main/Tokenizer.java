package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tokenizer {
	
	Path path;
	String token;
	String line = "";
	protected List<String> linesToParse = new ArrayList<String>();
	String command;
	List<String> lines;
	protected List<String> tokens = new ArrayList<String>();
	Boolean addString = true;
	String lineToParse = "";
	
	public Tokenizer(Path path)throws FileNotFoundException, IOException {
		this.path = path;
		try {
			InputStream in = new FileInputStream(path.toFile());
		    Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
		    Reader buffer = new BufferedReader(reader);
		    int r;
	        while ((r = buffer.read()) != -1) {
	            char ch = (char) r;
	            //Removes single- and multi-line comments
	            String comment = "";
	            if (ch == '/') {
	            	char c = (char) buffer.read();
	            	if (c == '/') {
	            		while ((char) buffer.read() != '\n') {
	            		}
	            	} else if (c == '*') {
	            		while(!comment.contains("*/")) {
	            			comment = comment + (char) buffer.read();          			
	            		}
	            	}	
	            	else {
	            		System.out.print(ch);
	            	}	
	            }
	            if (ch != '\n' && ch != '\t' && ch != '\b' && ch != '\f' && ch != '\r' && ch != '/') {
	            	System.out.print(ch);
	            }
	        }
	        buffer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
