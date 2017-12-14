package ast;

import visitor.*;

public class MyString extends Exp
{
	public String val_;
	
	public MyString(String val)
	{
		val_ = val;
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
