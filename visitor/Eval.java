package visitor;

import java.util.HashMap;
import ast.*;
import myUtils.*;

public class Eval implements TreeVisitor{

	// Tests if an object is an Integer
	public static boolean isInt(Object o){
		if(o instanceof Integer)
			return true;
		return false;
	}
	
	// General evaluation for strings and integer variables
	public static Object eval(Object o1, Object o2, String op, Eval e){
		try{
			Object eval = evaluation(o1, o2, op, e);
			if(eval!=null){
				if(eval instanceof String) return eval;
				return (int) eval;
			}
		}catch(Exception e_){ }
		return null; 
	}

	// Types of the expresions are retrieved and if one of them is a string, the string evaluation
	// is called, otherwise the integer evaluation is called
	private static Object evaluation(Object o1, Object o2, String op, Eval e) throws Exception{
		try{
			// Symbol table is needed for the potential variables
			SymTable sm = SymTable.getHM();

			if(o1 instanceof Var){
				o1 = sm.getValueType(((Var)o1).name_, false, true);
				if (o1==null) return null;
				o1 = ((Exp)o1).accept(e);
			}else if(o1 instanceof Exp){
				o1 = ((Exp)o1).accept(e);
			}else if(o1 instanceof Num){
				o1 = ((Num)o1).accept(e);
			}else if(o1 instanceof MyString){
				o1 = ((MyString)o1).accept(e);
			}

			if(o2 instanceof Var){
				o2 = sm.getValueType(((Var)o2).name_, false, true);
				if (o2==null) return null;
				o2 = ((Exp)o2).accept(e);
			}else if(o2 instanceof Exp){
				o2 = ((Exp)o2).accept(e);
			}else if(o2 instanceof Num){
				o2 = ((Num)o2).accept(e);
			}else if(o2 instanceof MyString){
				o2 = ((MyString)o2).accept(e);
			}
			
			if(o1==null || o2==null) return null;

			if((o1 instanceof String) || (o2 instanceof String))
				return myStringOp(o1, o2, op, e);
			else return intOp(o1, o2, op, e);
		}catch(Exception e_){}
		return null;
	}

	// For integer operations, there's a switch for the operation type and treated accordingly
	// since the result value is an integer, the comparaisons results are converted to 1 if true
	// and 0 if false
	public static Object intOp(Object o1, Object o2, String op, Eval e) throws Exception{
		try{
			SymTable sm = SymTable.getHM();

			int res=0;
			if(isInt(o1) && isInt(o2))
				switch(op){
					case "+":
						res = (int)o1 + (int)o2;
						break;
					case "-":
						res = (int)o1 - (int)o2;
						break;
					case "*":
						res = (int)o1 * (int)o2;
						break;
					case "/":
						res = (int)o1 / (int)o2;
						break;
					case "=":
						res = ((int)o1 == (int)o2)?1:0;
						break;
					case "<>":
						res = ((int)o1 != (int)o2)?1:0;
						break;

					case ">":
						res = ((int)o1 > (int)o2)?1:0;
						break;
					case ">=":
						res = ((int)o1 >= (int)o2)?1:0;
						break;
					case "<":
						res = ((int)o1 < (int)o2)?1:0;
						break;
					case "<=":
						res = ((int)o1 <= (int)o2)?1:0;
						break;
					default:
						throw new Exception("Unimplemented operator");
				}
			return res;
		}catch(Exception e_){}
		return null;
	}

	// For String operations, there's a switch for the operation type and treated accordingly
	// the result is an object which will be a string in the concatenation case or an integer
	// in the comparaisons cases.
	public static Object myStringOp(Object o1, Object o2, String op, Eval e) throws Exception{
		try{
			SymTable sm = SymTable.getHM();

			Object res="";
			switch(op){
					// The concatenation builds a new string upon two strings or one string and an integer
					case "+":
						StringBuilder sb = new StringBuilder(o1 instanceof Integer?Integer.toString((int)o1):(String)o1);
						sb.append(o2 instanceof Integer?Integer.toString((int)o2):(String)o2);
						res = sb.toString();
						break;
					case "=":
						res = ((String)o1).equals((String)o2)?1:0;						
						break;
					case "<>":
						res = !((String)o1).equals((String)o2)?1:0;
						break;
					case ">":
						res = (((String)o1).compareTo((String)o2))<0?1:0;
						break;
					case ">=":
						res = (((String)o1).compareTo((String)o2))<=0?1:0;
						break;
					case "<":
						res = (((String)o1).compareTo((String)o2))>0?1:0;
						break;
					case "<=":
						res = (((String)o1).compareTo((String)o2))>=0?1:0;
						break;
					default:
						throw new Exception("Unimplemented operator");
				}
				return res;
		}catch(Exception e_){}
		return null;
	}

	// returns integer value
	public Object visit(Num n){
		return (int) n.val_;
	}

	// returns all strings without the quotes("") at the begining and end 
	public Object visit(MyString n){

		return (String) n.val_.replaceAll("^\"", "").replaceAll("\"$", "");
	}
	
	// All binary operations call the eval function with the corresponding
	// operator
	public Object visit(Add n){
		try{
			return eval(n.lhs_, n.rhs_, "+", this);
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(Sub n){
		try{
			return eval(n.lhs_, n.rhs_, "-", this);
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(Mul n){
		try{
			return eval(n.lhs_, n.rhs_, "*", this);
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(Div n){
		try{
			return eval(n.lhs_, n.rhs_, "/", this);
		} catch(Exception e) { }
		return null;
	}
	
	// All comparaisons convert the integer results from
	// the evaluation to booleans
	public Object visit(Eq n){
		try{
			Object ie=eval(n.lhs_, n.rhs_, "=", this);
			if(ie==null) return null;
			return ((int)ie)==1?true:false;
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(Neq n){
		try{
			Object ie=eval(n.lhs_, n.rhs_, "<>", this);
			if(ie==null) return null;
			return ((int)ie)==1?true:false;
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(GreaterThan n){
		try{
			Object ie=eval(n.lhs_, n.rhs_, ">", this);
			if(ie==null) return null;
			return ((int)ie)==1?true:false;
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(GreaterEq n){
		try{
			Object ie=eval(n.lhs_, n.rhs_, ">=", this);
			if(ie==null) return null;
			return ((int)ie)==1?true:false;
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(LessThan n){
		try{
			Object ie=eval(n.lhs_, n.rhs_, "<", this);
			if(ie==null) return null;
			return ((int)ie)==1?true:false;
		} catch(Exception e) { }
		return null;
	}
	
	public Object visit(LessEq n){
		try{
			Object ie=eval(n.lhs_, n.rhs_, "<=", this);
			if(ie==null) return null;
			return ((int)ie)==1?true:false;
		} catch(Exception e) { }
		return null;
	}
	
	// printing will test the type of the expression and print
	// the according evaluated expression for integers, strings
	// and variables
	public Object visit(Print n){
		try{
			SymTable sm = SymTable.getHM();			
			if(n.e_ instanceof Var){
				Object value = sm.getValueType(((Var)n.e_).name_, false, true);
				if (value==null) return null;
				System.out.println(((Exp)value).accept(this));
			}else if(n.e_ instanceof MyString) System.out.println(((MyString)n.e_).accept(this));
			else{
				Object o = n.e_.accept(this);
				if(o!=null) System.out.println(o instanceof Integer?Integer.toString((int)o):o.toString());

			}
		} catch(Exception e) { }
		return null;
	}
	
	// First retreives the evaluation for the initial expression and then convert it
	// into boolean so it can be used as the condition for a java if statement
	// afterwards teh if and else statement are evaluated
	public Object visit(IfStmt n){
		try{
			Object o = n.ifE_.accept(this);
			boolean cond = (boolean)(o instanceof Integer?(((int)o!=0?true:false)):o);
			if(cond)
				n.ifS_.accept(this);
			else
				n.elseS_.accept(this);
		} catch(Exception e) { }
		return null;
	}

	// After retrieving the syymbol table, the variable is declared in it using the id and
	// expresions from the object Aff
	public Object visit(Aff n){
		try{
			SymTable sm = SymTable.getHM();
			if(n.rhs_ instanceof Var)
				return sm.declareVariable(n.id_, n.rhs_.accept(new Eval()), n.declaration_);
			else
				return sm.declareVariable(n.id_, n.rhs_.accept(new Eval()), n.declaration_);
		} catch(Exception e) { }
		return null;
	}

	// Tries to retrieve a variable's value with it's name
	public Object visit(Var n){
		try{
			SymTable sm = SymTable.getHM();
			sm.getValueType(n.name_, false, true);
		}catch (Exception e){}
		return (String) n.name_;
	}

	// After retrieveing the block, a scope is opened for the blocks
	// local variables, then the left child and right child, variables 
	// and expressions accordingly, will be visited, the scope has to be closed
	// or deleted at the end without afecting the parent scope's variables and
	// erasing form existance it's own variables
	public Object visit(Block n){
		SymTable sm = SymTable.getHM();
		sm.openScope();
		n.lhs_.accept(this);
		n.rhs_.accept(this);
		sm.closeScope();
		return null;
	}

	// All variables from a block are evalulated in a loop
	public Object visit(VarBlock n){
		for(Exp e : n.getVar()){
			e.accept(this);
		}
		return null;
	}

	// All statements from a block are evaluated in a loop
	public Object visit(StmtBlock n){
		for(Stmt s : n.getStmt()){
			s.accept(this);
		}
		return null;
	}

	// Like for a block, the while statement opens a scope at the begining and
	// closes it at the end. Then, like done in the if statement, the first expression
	// is converted to boolean to be passed as the condition of a java while statement
	// wihtin the statement and invcrement expressions are evaluated while updating the
	// condition
	public Object visit(WhileStmt n){
		try{
			SymTable sm = SymTable.getHM();
			sm.openScope();
			Object o = n.condition_.accept(this);
			boolean cond = (boolean)(o instanceof Integer?(((int)o!=0?true:false)):o);
			while(cond){
				n.statement_.accept(this);
				n.increment_.accept(this);
				o = n.condition_.accept(this);
				cond = (boolean)(o instanceof Integer?(((int)o!=0?true:false)):o);
			}
			sm.closeScope();
		}catch(Exception e){}
		return null;
	}

	// Very much like the while statement but replacing the java while for a java do while
	public Object visit(DoWhileStmt n){
		try{
			SymTable sm = SymTable.getHM();
			sm.openScope();
			Object o = 0;
			boolean cond = false;
			do{
				n.statement_.accept(this);
				n.increment_.accept(this);
				o = n.condition_.accept(this);
				cond = (boolean)(o instanceof Integer?(((int)o!=0?true:false)):o);
			}while(cond);
			sm.closeScope();
		}catch(Exception e){}
		return null;
	}

	// Symbol table is retrieved, scope is created, then the name of the variable used
	// for the statement is stored in a string while the condition and increment are 
	// stored in ints to be used in a java for statement, the value of the condition is
	// updated at each iteration in the symbol table on it's corresponding scope
	public Object visit(ForStmt n){
		try{
			SymTable sm = SymTable.getHM();
			sm.openScope();
			String v = ((Aff)n.condition_).id_;
			int c = (int) n.condition_.accept(this);
			int i = (int) n.increment_.accept(this);
			for(int j=c; j<=i; j++){
				n.statement_.accept(this);
				Object o = sm.getValueType(v);
				if(o==null) return null;
				sm.declareVariable(v, 1+ (int)((Exp)o).accept(this), false);
			}
			sm.closeScope();
		}catch(Exception e){}
		return null;
	}
}
