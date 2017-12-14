package visitor;

import ast.*;

public interface TreeVisitor{
	public Object visit(Num n);
	public Object visit(Add n);
	public Object visit(Sub n);
	public Object visit(Mul n);
	public Object visit(Div n);
	public Object visit(Eq n);
	public Object visit(Neq n);
	public Object visit(GreaterThan n);
	public Object visit(GreaterEq n);
	public Object visit(LessThan n);
	public Object visit(LessEq n);
	public Object visit(Print n);
	public Object visit(IfStmt n);
	public Object visit(Aff n);
	public Object visit(Var n);
	public Object visit(Block n);
	public Object visit(VarBlock n);
	public Object visit(StmtBlock n);
	public Object visit(WhileStmt n);
	public Object visit(DoWhileStmt n);
	public Object visit(ForStmt n);
	public Object visit(MyString n);
}
