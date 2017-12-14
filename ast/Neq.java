package ast;

import visitor.*;

public class Neq extends Bin
{	
	public Neq(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
