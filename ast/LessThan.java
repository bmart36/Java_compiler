package ast;

import visitor.*;

public class LessThan extends Bin
{	
	public LessThan(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
