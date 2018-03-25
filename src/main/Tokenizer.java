package main;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Tokenizer {
	
	Path path;
	Scanner filescanner;
	String line;
	String command;
	
	public Tokenizer(Path path)throws FileNotFoundException {
		this.path = path;
	    filescanner = new Scanner(path.toFile());
	}
	
	public boolean hasMoreTokens() {
		return filescanner.hasNextLine();
	}
	
	public void advance() {
		line = filescanner.nextLine();
		if (line.startsWith("//") || line.isEmpty() && hasMoreTokens()) {
			advance();
		} else {
			
		}
		
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
