package ast;

import visitor.*;

public class Add extends Bin
{	
	public Add(Exp lhs, Exp rhs)
	{
		super(lhs,rhs);
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
