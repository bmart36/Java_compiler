package ast;

import visitor.*;
import myUtils.*;

public class Print extends Stmt
{
	public Exp e_;
	
	public Print(Exp e, String... b)
	{
		e_ = e;
	}
	
	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
