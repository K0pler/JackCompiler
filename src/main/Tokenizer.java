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

public class Tokenizer {
	
	Path path;
	int r;
	char ch;
	String token;
	String command;
	InputStream in;
	Reader reader;
	Reader buffer;
	
	public Tokenizer(Path path)throws FileNotFoundException, IOException {
		this.path = path;
	    in = new FileInputStream(path.toFile());
		reader = new InputStreamReader(in, StandardCharsets.UTF_8);
		buffer = new BufferedReader(reader);
	}
	
	public boolean hasMoreTokens() throws IOException {
		r = buffer.read();
		
		if (r != -1) {
			ch = (char) r;
			return true;
		} else {
			return false;
		}	
	}
	
	public String advance() throws IOException {
		token = "";
		if (hasMoreTokens()) {
			if (ch == '/') {
				token = token + ch;
				buffer.mark(r);
				if (hasMoreTokens() && (ch == '/' || ch == '*')) {
					token = token + ch;
					while(hasMoreTokens() && ch != '\n' && !token.contains("*/")) {
						token = token + ch;	
					}			
					return token;	
				}
				buffer.reset();
				return token;
				
			}
			if (Character.isWhitespace(ch)) {
				advance();
			} else {
				token = token + ch;
			}
			
			while (hasMoreTokens())  {
				if (Character.isWhitespace(ch))
					return token;
				token = token + ch;
			}
			return token;
			
			}
		return "/end";
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
