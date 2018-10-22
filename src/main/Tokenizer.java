package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;

public class Tokenizer {
	
	Path path;
	int r;
	char ch;
	String token;
	String command;
	InputStream in;
	Reader reader;
	Reader buffer;
	ArrayList<String> tokens = new ArrayList<String>();
	
	public Tokenizer(Path path)throws FileNotFoundException, IOException {
		this.path = path;
		token = "";
	    in = new FileInputStream(path.toFile());
		reader = new InputStreamReader(in, StandardCharsets.UTF_8);
		buffer = new BufferedReader(reader);
	}
	
	public boolean hasMoreTokens() throws IOException {
		r = buffer.read();
		if (!tokens.isEmpty()) {
			return true;
		} else {
			return false;
		}	
	}
	
	public void advance() throws IOException {
		
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
