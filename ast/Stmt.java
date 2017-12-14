package ast;

import visitor.*;

public abstract class Stmt
{
	public abstract Object accept(TreeVisitor t);
}

