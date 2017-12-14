package ast;

import visitor.*;

public class Num extends Exp
{
	public int val_;
	
	public Num(int val)
	{
		val_ = val;
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
