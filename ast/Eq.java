package ast;

import visitor.*;

public class Eq extends Bin
{	
	public Eq(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
