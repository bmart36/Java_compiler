package ast;

import java.util.ArrayList;
import visitor.*;

public class DoWhileStmt extends Stmt
{
	public Exp condition_, increment_;
	public Stmt statement_;
	

	public DoWhileStmt(Exp e1, Stmt s, Exp e2)
	{
		condition_ = e1;
		statement_ = s;
		increment_ = e2;
	}

	public Object accept(TreeVisitor t)
	{
		return t.visit(this);
	}
}
