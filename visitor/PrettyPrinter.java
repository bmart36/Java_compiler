package visitor;

import ast.*;
import myUtils.*;

public class PrettyPrinter implements TreeVisitor{
	// Prefix for tree nodes
	private StringBuilder prefix = new StringBuilder("|");
	
	// Adds indentation to the prefix
	private void indent(){
		prefix.insert(0, "  ");
	}
	
	// Removes indentation from the prefix
	private void unindent(){
		prefix.delete(0,2);
	}
	
	// Prints the given string wiht the prefix
	private void show(String s){
		System.out.print(prefix.toString());
		System.out.println(s);
	}
	
	// Prints the integer value of a Num
	public Object visit(Num n){
		show(Integer.toString(n.val_));
		return null;
	}
	
	// All binary operations print the root node indicating the operation,
	// indents the prefix and then the left and right expression are visited,
	// finally it unindents the prefix
	public Object visit(Add n){
		show("Add");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(Sub n){
		show("Sub");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(Mul n){
		show("Mul");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(Div n){
		show("Div");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(Eq n){
		show("Eq");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(Neq n){
		show("Neq");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(GreaterThan n){
		show("Greater");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(GreaterEq n){
		show("Greater/Eq");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(LessThan n){
		show("Less");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	public Object visit(LessEq n){
		show("Less/Eq");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}
	
	// Prints the root node "Print", after indenting it visits the
	// expresion of the print and finally unindents
	public Object visit(Print n){
		show("Print");
		indent();
		n.e_.accept(this);
		unindent();
		return null;
	}
	
	// Two principal nodes If and Else, after each the prefix
	// is indented and then the corresponding expressions and 
	// statement are visited befor being unindented
	public Object visit(IfStmt n){
		show("If");
		indent();
		n.ifE_.accept(this);
		n.ifS_.accept(this);
		unindent();
		show("Else");
		indent();
		n.elseS_.accept(this);
		unindent();
		return null;
	}

	// First it is tested if the Aff is a declaration or not to
	// define the root node, then after indentation the id of the 
	// variable is printed followed by the visit to the expression
	// defining it
	public Object visit(Aff n){
		if(n.declaration_)
			show("Declaration");
		else
			show("Affect");
		indent();
		show(n.id_);
		n.rhs_.accept(this);
		unindent();
		return null;
	}

	// Prints root node Variable, indents, then prints variable's name
	public Object visit(Var n){
		show("Variable");
		indent();
		show(n.name_);
		unindent();
		return null;
	}

	// Root node Block, indents, after visits left child, which should be
	// list of variables, and right child, which should be list of statements
	public Object visit(Block n){
		show("Block");
		indent();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		unindent();
		return null;
	}

	// The root node Variables is followed by all the variables defined
	// in the block visited in a loop
	public Object visit(VarBlock n){
		show("Variables");
		indent();
		for(Exp e : n.getVar()){
			e.accept(this);
		}
		unindent();
		return null;
	}

	// The root node Statements is followed by all the statements defined
	// in the block visited in a loop
	public Object visit(StmtBlock n){
		show("Statements");
		indent();
		for(Stmt s : n.getStmt()){
			s.accept(this);
		}
		unindent();
		return null;
	}

	// Root While, indents, then visits first the condition, then the statement
	// and finally the increment expression in the while loop
	public Object visit(WhileStmt n){
		show("While");
		indent();
		n.condition_.accept(this);
		n.statement_.accept(this);
		n.increment_.accept(this);
		unindent();
		return null;
	}

	// Root Do While, indents, then visits first the statement, then the increment
	// expression and finally the condition in the do while loop
	public Object visit(DoWhileStmt n){
		show("Do While");
		indent();
		n.statement_.accept(this);
		n.increment_.accept(this);
		n.condition_.accept(this);
		unindent();
		return null;
	}

	// Root For indents, then visits first the condition then the increment expression
	// and finally the statement
	public Object visit(ForStmt n){
		show("For");
		indent();
		n.condition_.accept(this);
		n.increment_.accept(this);
		n.statement_.accept(this);
		unindent();
		return null;
	}

	// Prints the string value
	public Object visit(MyString n){
		show(n.val_);
		return null;
	}
}
