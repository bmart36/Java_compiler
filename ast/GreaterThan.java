package ast;

import visitor.*;

public class GreaterThan extends Bin
{	
	public GreaterThan(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
