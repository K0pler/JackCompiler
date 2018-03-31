package main;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	
	Tokenizer tokenizer;
	
	public static void main(String[] args) {
		
		Path userHome = Paths.get(System.getProperty("user.home"));
		Path path = Paths.get(userHome + "/nand2tetris/projects/10/ArrayTest/");
		
		Tokenizer tokenizer = null;
		
		if (path.toFile().isDirectory() && Files.exists(path)) {
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{jack}")) {
				
				for (Path file: stream) {
					tokenizer = new Tokenizer(file);
					
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
