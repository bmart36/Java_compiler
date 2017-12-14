package myUtils;

import java.util.Map;
import java.util.HashMap;
import ast.*;

public class Scope{

	// Each scope has a name, a hashmap containing the variables and their values
	// (which can be integers or strings), and a reference to its parent scope
	public String name_;
	private HashMap<String, Object> current_scope=null;
	private Scope parent_scope=null;

	// Construct will create a new HashMap that copies all the variables from
	// it's parent scope
	public Scope(String name, Scope parent){
		name_=name;
		parent_scope=parent;
		current_scope = new HashMap<String, Object>();
		if(parent_scope!=null){
			for(Map.Entry<String, Object> entry : parent_scope.getCurrentScope().entrySet())
				current_scope.put(entry.getKey(), entry.getValue());
		}
	}

	// Retrieves the scope's name
	public String getScopeName(){
		return name_;
	}

	// Retrieves the HashMap current scope
	public HashMap<String, Object> getCurrentScope(){
		return current_scope;
	}

	// Retrieves the parent scope
	public Scope getParent(){
		return parent_scope;
	}

	// declaration=flase
	//			->	if the variable has not ben declared it can't be affected, throwing an exception
	//			->	if it has been declared, the value is changed in the HashMap
	// delcaration=true
	//			->	if the variable has not been declared, it is added to the scopes HashMap
	//			->	if it has been declared, an exception is thrown
	public Object declareVariable(String var_name, Object var_value, Boolean declaration) throws Exception{
		if(!declaration && !current_scope.containsKey(var_name)){
			StringBuilder sb = new StringBuilder("Variable ");
			sb.append(var_name);sb.append(" not declared");
			throw new Exception(sb.toString());
		}else if(declaration && current_scope.containsKey(var_name)){
			StringBuilder sb = new StringBuilder("Variable ");
			sb.append(var_name);sb.append(" is already declared");
			throw new Exception(sb.toString());
		}
		
		current_scope.put(var_name, var_value);
		return current_scope.get(var_name);
	}

	// Retrieves the value of a given variable from the scope
	// 		-> if the variable is not in the scope's HashMap or it is but doesn't have a value, the name of the variable is retrieved
	// 		-> if it's not in the scope's HashMap it throws an exception
	//		-> otherwise it retrieves the existant value for the variable in the scope's HashMap
	public VarType getValue(String var_name, boolean b) throws Exception{
		try{
			if(b && (!current_scope.containsKey(var_name) || (current_scope.containsKey(var_name) && current_scope.get(var_name)==null))){
				if(!current_scope.containsKey(var_name)) declareVariable(var_name, null, b);
				return new VarType("var", var_name);
			}
		}catch (Exception e) {}
		if(!b && !current_scope.containsKey(var_name)){
			StringBuilder sb = new StringBuilder("Variable ");
			sb.append(var_name);sb.append(" not declared");
			throw new Exception(sb.toString());
		}
		return new VarType("value", current_scope.get(var_name));
	}

	// Removes a variable from scope according to it's name
	public Object removeValue(String var_name){
		return current_scope.remove(var_name);
	}

	// Deletes a scope, for when a block ends
	public void delete(){
		current_scope = null;
	}

	// tries to retrieve value from HashMap and depending on
	// the type converts the result to the corresponding
	// expression
	public Exp getValueType(String key,Boolean... b){
		boolean b2 = b.length>0?b[0]:false;
		VarType v = null;
		try{
			v = getValue(key, b2);
		} catch(Exception e) { }
		if(v==null) return null;
		if(v.type=="var"){
			if(v.content instanceof String)
				return new Var(v.content.toString());
			
		}else if(v.type=="value"){
			if(v.content instanceof Integer)
				return new Num((int)v.content);
			else if(v.content instanceof String)
				return new MyString(v.content.toString());
		}
		return null;
	}
}