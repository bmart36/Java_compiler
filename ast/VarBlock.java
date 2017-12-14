package ast;

import java.util.ArrayList;
import visitor.*;

public class VarBlock extends Stmt{
	private ArrayList<Exp> variables;

	public VarBlock(){
		variables = new java.util.ArrayList<Exp>();
	}

	public void addVar(Exp e){
		variables.add(e);
	}

	public ArrayList<Exp> getVar(){
		return variables;
	}

	public Object accept(TreeVisitor t){
		return t.visit(this);
	}
}