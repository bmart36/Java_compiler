package ast;

import visitor.*;

public abstract class Bin extends Exp
{
	public Exp lhs_;
	public Exp rhs_;
	
	public Bin(Exp lhs, Exp rhs)
	{
		lhs_=lhs;
		rhs_=rhs;
	}
	
	public abstract Object accept(TreeVisitor t);
}
