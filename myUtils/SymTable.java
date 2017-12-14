package myUtils;

import java.util.HashMap;
import ast.*;

// Symbol table that will store the variables declared
public class SymTable{
	 
	public static SymTable sm = null;
	public static int scopeId=0;
	public static Scope scope=null;

	// Constructor will create the root scope for the program
	private SymTable(){
		if(scope==null)
			scope = new Scope("root", null);
	}

	// Function to retrieve the Symbol table or create it if null
	public static SymTable getHM(){
		if(sm==null)
			sm = new SymTable();
		return sm;
	}

	// Function to declare or affect a variable:
	//				declaration ->	true, when expression is a declaration
	//								false, when expression is an affectation
	public Object declareVariable(String var_name, Object var_value, Boolean declaration){
		try{
			return scope.declareVariable(var_name, var_value, declaration);
		} catch (Exception e) {System.out.println(e.getMessage());}
		return null;
	}

	// Removes a variable from the current scope
	public Object removeValue(String var_name){
		return scope.removeValue(var_name);
	}

	// It retrieves a value from the current scope with it's identifier
	public Exp getValueType(String key,Boolean... b){
		try{
			return scope.getValueType(key, b);
		} catch(Exception e) { System.out.println(e.getMessage());}
		return null;
	}

	// Creates a new scope for when a new block is created
	public void openScope(){
		scopeId +=1;
		Scope s = new Scope(Integer.toString(scopeId), scope);
		scope = s;
	}

	// Deletes a scope when a block ends
	public void closeScope(){
		Scope parent = scope.getParent();
		scope.delete();
		scope=parent;
	}
}