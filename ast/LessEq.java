package ast;

import visitor.*;

public class LessEq extends Bin
{	
	public LessEq(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
