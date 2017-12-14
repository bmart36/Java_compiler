package ast;

import visitor.*;

public class Mul extends Bin
{
	public Mul(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
