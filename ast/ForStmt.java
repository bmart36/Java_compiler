package ast;

import java.util.ArrayList;
import visitor.*;

public class ForStmt extends Stmt
{
	public Exp condition_, increment_;
	public Stmt statement_;
	

	public ForStmt(Exp e1, Exp e2, Stmt s)
	{
		condition_ = e1;
		increment_ = e2;
		statement_ = s;
	}

	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
