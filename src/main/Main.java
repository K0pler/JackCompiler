package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
	
	Tokenizer tokenizer;
	
	public static void main(String[] args) {
		
		Path userHome = Paths.get(System.getProperty("user.home"));
		Path path = Paths.get(userHome + "/nand2tetris/projects/10/Square/");
		
		
		Tokenizer tokenizer = null;
		
		if (path.toFile().isDirectory() && Files.exists(path)) {
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{jack}")) {
				for (Path file: stream) {
					Path savePath = Paths.get(userHome + "/nand2tetris/projects/10/Square/Test/" + file.getFileName() + ".xml");
					tokenizer = new Tokenizer(file);
					String token = "";
					try {
						if(Files.exists(savePath)) {
						Files.delete(savePath);
						}
						
					} catch (NoSuchFileException x) {
						System.err.format("%s: no such" + " file or directory%n", path);
					} catch (IOException y) {
						// File permission problems are caught here.
						System.err.println(y);
					}
					try (BufferedWriter writer = Files.newBufferedWriter(savePath, StandardCharsets.UTF_8)) {
						writer.write("<tokens>\n");
						System.out.println("<tokens>");
						while (token != "/end") {
							
							token = tokenizer.advance();
							if (tokenizer.tokenType() == "identifier") {
								System.out.println(tokenizer.identifier());
								writer.write(tokenizer.identifier() + "\n");
							}
							if (tokenizer.tokenType() == "symbol") {
								System.out.println("<symbol> " + tokenizer.symbol() + " </symbol>");
								if (tokenizer.symbol() == '>') {
									writer.write("<symbol> " + "&gt;" + " </symbol>\n");
								} else if (tokenizer.symbol() == '<') {
									writer.write("<symbol> " + "&lt;" + " </symbol>\n");
								} else if (tokenizer.symbol() == '&') {
									writer.write("<symbol> " + "&amp;" + " </symbol>\n");
								} else if (tokenizer.symbol() == '"') {
									writer.write("<symbol> " + "&quot;" + " </symbol>\n");
								} else {
								writer.write("<symbol> " + tokenizer.symbol() + " </symbol>\n");
								}
							}
							if (tokenizer.tokenType() == "string") {
								System.out.println("<string> " + tokenizer.stringVal() + " </string>");
								writer.write("<stringConstant> " + tokenizer.stringVal() + " </stringConstant>\n");
							}
							if (tokenizer.tokenType() == "keyword") {
								System.out.println(tokenizer.keyWord());
								writer.write(tokenizer.keyWord() + "\n");
							}
							if (tokenizer.tokenType() == "integer") {
								System.out.println("<integerConstant> " + tokenizer.intVal() + " </integerConstant>");
								writer.write("<integerConstant> " + tokenizer.intVal() + " </integerConstant>\n");
							}
							if (tokenizer.tokenType() == "end") {
								
							}
							
							
						}
						System.out.println("</tokens>");
						writer.write("</tokens>\n");
					} catch (IOException x) {
					    System.err.format("IOException: %s%n", x);
					}
					
					
		    	}
			} catch (IOException | DirectoryIteratorException x) {
		    	// IOException can never be thrown by the iteration.
		    	// In this snippet, it can only be thrown by newDirectoryStream.
		    	System.err.println(x);
			}
		} else {
			System.out.println("Cannot find file or directory @ path: " + path.getFileName());
		}
	}
}
