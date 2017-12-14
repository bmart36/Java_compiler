package ast;

import java.util.ArrayList;
import visitor.*;

public class StmtBlock extends Stmt{
	private ArrayList<Stmt> statements;

	public StmtBlock(){
		statements = new java.util.ArrayList<Stmt>();
	}

	public void addStmt(Stmt e){
		statements.add(e);
	}

	public ArrayList<Stmt> getStmt(){
		return statements;
	}

	public Object accept(TreeVisitor t){
		return t.visit(this);
	}
}