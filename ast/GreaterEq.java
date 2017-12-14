package ast;

import visitor.*;

public class GreaterEq extends Bin
{	
	public GreaterEq(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
