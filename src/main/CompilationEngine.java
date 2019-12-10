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

public class CompilationEngine {
	
	Path path;
	InputStream in;
	Reader reader;
	Reader buffer;
	
	public CompilationEngine(Path path)throws FileNotFoundException, IOException {
		this.path = path;
	    in = new FileInputStream(path.toFile());
		reader = new InputStreamReader(in, StandardCharsets.UTF_8);
		buffer = new BufferedReader(reader);
	}
	
	private void CompileClass(){
		
	}
	
	private void CompileClassVarDec(){
		
	}
	
	private void CompileSubroutine(){
		
	}
	
	private void CompileParameterList(){
		
	}
	
	private void CompileVarDec(){
		
	}
	
	private void CompileStatements(){
		
	}
	
	private void CompileDo(){
		
	}
	
	private void CompileLet(){
		
	}
	
	private void CompileWhile(){
		
	}
	
	private void CompileReturn(){
		
	}
	
	private void CompileIf(){
		
	}
	
	private void CompileExpression(){
		
	}
	
	private void CompileTerm(){
		
	}
	
	private void CompileExpressionList(){
		
	}
}
