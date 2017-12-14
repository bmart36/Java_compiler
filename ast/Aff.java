package ast;

import visitor.*;

public class Aff extends Exp
{
	public String id_, s_rhs=null;
	public Exp rhs_=null;
	public Boolean declaration_;
	
	public Aff(String id, Exp rhs, Boolean declaration)
	{
		id_ = id;
		rhs_ = rhs;
		declaration_ = declaration;
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
