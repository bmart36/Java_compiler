package ast;

import visitor.*;

public abstract class Exp
{
	public abstract Object accept(TreeVisitor t);
}

