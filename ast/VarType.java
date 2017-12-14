package ast;

import visitor.*;

public class VarType
{
	public String type;
	public Object content;
	
	public VarType(String t, Object o)
	{
		type = t;
		content = o;
	}
}
