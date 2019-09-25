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
import java.util.HashMap;

public class Tokenizer {
	
	Path path;
	int r;
	char ch;
	String token;
	String nextToken = null;
	String command;
	String tokenType;
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
		
		if (nextToken != null) {
			token = nextToken;
			nextToken = null;
			return token;
		}
		
		while (hasMoreTokens()) {
			if (ch == '/') {
				token = token + ch;
				buffer.mark(r);
				if (hasMoreTokens() && (ch == '/' || ch == '*')) {
					token = token + ch;
					if (token.contains("//")) {
						while(hasMoreTokens() && (ch != '\n')) {
							buffer.mark(r);
							token = token + ch;
						}
					}
					if (token.contains("/*")) {
						while(hasMoreTokens() && !token.contains("*/")) {
							buffer.mark(r);
							token = token + ch;
						}
					}
					buffer.reset();
					return token;	
				}
				buffer.reset();
			}
			
			if (ch == '"') {
				nextToken = "" + ch;
				while(hasMoreTokens() && ch != '"') {
					nextToken = nextToken + ch;
				}
				if (token.isEmpty()) {
					
					advance();
				} else {
					
					return token;
				}
			}
			
			if (ch=='{' || ch=='}' || ch=='(' || ch==')' || ch=='[' || ch==']' || ch=='.'
				|| ch==',' || ch==';' || ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='&' || ch=='|' || ch=='<'
				|| ch=='>' || ch=='='  || ch=='_'){
				if(token.isEmpty()) {
					token = "" + ch;
					return token;
				} else {
					nextToken = "" + ch;
					return token;
				}
				
			}
			
			if (!Character.isWhitespace(ch)) {
				token = token + ch;
			} else {
				if (token.isEmpty()) {
					advance();
				} 
				if(!token.isEmpty()) {
					return token;
				}
			}
				
			
		}
		
		token = "/end";
		return token;
	}
	
	public String tokenType() { 
		
		HashMap<String, String> typemap = new HashMap<String, String>();
		
		typemap.put("class", "keyword");
		typemap.put("constructor", "keyword");
		typemap.put("function", "keyword");
		typemap.put("method", "keyword");
		typemap.put("field", "keyword");
		typemap.put("static", "keyword");
		typemap.put("var", "keyword");
		typemap.put("int", "keyword");
		typemap.put("char", "keyword");
		typemap.put("boolean", "keyword");
		typemap.put("void", "keyword");
		typemap.put("true", "keyword");
		typemap.put("false", "keyword");
		typemap.put("null", "keyword");
		typemap.put("this", "keyword");
		typemap.put("let", "keyword");
		typemap.put("do", "keyword");
		typemap.put("if", "keyword");
		typemap.put("else", "keyword");
		typemap.put("while", "keyword");
		typemap.put("return", "keyword");
		typemap.put("{", "symbol");
		typemap.put("}", "symbol");
		typemap.put("(", "symbol");
		typemap.put(")", "symbol");
		typemap.put("[", "symbol");
		typemap.put("]", "symbol");
		typemap.put(".", "symbol");
		typemap.put(",", "symbol");
		typemap.put(";", "symbol");
		typemap.put("+", "symbol");
		typemap.put("-", "symbol");
		typemap.put("*", "symbol");
		typemap.put("/", "symbol");
		typemap.put("&", "symbol");
		typemap.put("|", "symbol");
		typemap.put("<", "symbol");
		typemap.put(">", "symbol");
		typemap.put("=", "symbol");
		typemap.put("_", "symbol");
		
		if (typemap.containsKey(token)) {
			return typemap.get(token);
		} else if (token.startsWith("\"") && token.endsWith("\"")) {
			return "string";
		}
		
		try 
        { 
            // checking valid integer using parseInt() method 
            Integer.parseInt(token, 10); 
            return "integer"; 
        }  
        catch (NumberFormatException e)  
        {  
        }
		if (token.startsWith("//") || token.startsWith("/*") && token.endsWith("*/")) {
			return "comment";
		}
		if (token == "/end") {
			return "end";
		}
		
		return "identifier";
		
	}
	
	public String comment() {
		return "<comment> " + token + " </comment>";
	}
	
	public String keyWord() {
		return "<keyword> " + token + " </keyword>";
	}
	
	public char symbol() {
		char c = token.charAt(0);
		return c ;
	}
	
	public String identifier() {
		return "<identifier> " + token + " </identifier>";
	}
	
	public int intVal() {
		return Integer.parseInt(token);
	}
	
	public String stringVal() {
		return token.substring(1, token.lastIndexOf('"'));
	}

}
