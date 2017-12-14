package ast;

import visitor.*;

public class Block extends Stmt
{
	public Stmt lhs_;
	public Stmt rhs_;
	
	public Block(Stmt lhs, Stmt rhs)
	{
		lhs_=lhs;
		rhs_=rhs;
	}
	
	public Object accept(TreeVisitor t){
		return t.visit(this);
	}
}
