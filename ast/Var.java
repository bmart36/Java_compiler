package ast;

import visitor.*;

public class Var extends Exp
{
	public String name_;
	
	public Var(String name)
	{
		name_ = name;
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
