package ast;

import visitor.*;

public class IfStmt extends Stmt
{
	public Exp ifE_;
	public Stmt ifS_, elseS_;
	

	public IfStmt(Exp e, Stmt s1, Stmt... s2)
	{
		ifE_ = e;
		ifS_ = s1;
		if(s2.length > 0 ) elseS_ = s2[0];
	}

	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
