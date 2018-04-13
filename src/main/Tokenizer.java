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
		    token = "";
	        while ((r = buffer.read()) != -1) {
	            char ch = (char) r;
	            //Removes single- and multi-line comments
	            String comment = "";
	            if (ch == '/') {
	            	if (!token.isEmpty()) {
	            		tokens.add(token);
	            		token = "";
	            	}
	            	buffer.mark(r);
	            	char c = (char) buffer.read();
	            	if (c == '/') {
	            		while ((char) buffer.read() != '\n') {
	            		}
	            	} else if (c == '*') {
	            		while(!comment.contains("*/")) {
	            			comment = comment + (char) buffer.read();          			
	            		}
	            	} else {
	            		buffer.reset();
	            		tokens.add(""+ch);
	            	}
	            }
	            //Tokenizes doublequotes.
	            else if (ch == '"') {
	            	if (!token.isEmpty()) {
	            		tokens.add(token);
	            		token = "";
	            	}
	            	tokens.add("" + ch);
	            	String quote = "";
	            	ch = (char) buffer.read();
	            	while (ch != '"'){
	            		quote = quote + ch;
	            		ch = (char) buffer.read();
	            	}
	            	if (!quote.isEmpty()){
	            		tokens.add(quote);
	            	}
	            	tokens.add("" + ch);
	            }
	            else if (ch == '\n' || ch == '\t' || ch == '\b' || ch == '\f' || ch == '\r' || ch == ' ') {
	            	if (!token.isEmpty()) {
	            		tokens.add(token);
	            		token = "";
	            	}
	            }
	            else if (ch == ';' || ch == '.' || ch == ',' || ch == '(' || ch == ')' || ch == '{' || ch == '}' || ch == '[' || ch == ']') {
	            	if (!token.isEmpty()) {
	            		tokens.add(token);
	            		token = "";
	            	}
	            	tokens.add("" + ch);
	            } else {
	            	token = token + ch;
	            }
	        }
	        for (String t : tokens) {
	        		System.out.print(t + " ");
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
