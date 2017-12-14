# JAVA compiler
Members: Bibiana MARTINEZ

Work progression:
- Basic grammar: expression -> factor -> term with operations of sum, substraction, division and multiplication
- Building AST
- Creation of tree visitors
	- Evalution visitor
	- Prettyprint visitor
- Quit expression for exiting
- Print expression to print evaluation of an expression
- Print and quit are now considered as statements, in the mainloop we expect either a statement or an expression
- Unary operator "-" for negative numbers
- Operation symbols (+, -, *, /) and parenthesis are now tokens
- Comparaisons added to the grammar
---------------------------------------------------------------------------------
|	Expression -> Comparaison ( =Comparaison | <> Comparaison )					|
|	Comparaison -> Additive ( <Additive | <=Additive | >Additive | >=Additive )	|
|	Additive -> Term ( +Term | -Term )											|
|	Term -> Factor ( *Factor | /Factor )										|
|	Factor -> [-]Number | (Expression)											|
---------------------------------------------------------------------------------
- After adding the to the grammar, their corresponding evaluation and position in the AST are implemented
- Implementation of if-then-else test without the support for else ____ then, instead else if ___ then should be used
- Support for identifiers as expressions
- Declaration of variables possible
- Class for symbol table ot save variables in HashMap implemented as a singleton
- var key word for variables to be replaced in future by type
- Implemented Blocks, VarBlock and StmtBlock. block represents a block of code, while VarBlock and StmtBlock represent lists of the blocks variables and statements
- Switch in intEval for teh type evalustion and making correct cast and retreiving value from symbol table if variable exists
- Modification of declaration so affectation is only possible if variable already exists in symbol table
- Variable evalueted befor print (fixed)
- Added error detection for undeclared variables or double declaration of variables with the same name
- While loop implemented
- For loop implemented
- Do While loop implemented
- Scope implementation, created new class that has HashMap to store variables and reference to parent scope. Symbol table no longer has a HashMap but a scope
- String support with concatenation and operations by adding an extra evaluation function with a switch similar to what was done with the integer binary operations

Problems found:
- Problem for the else part of the if statement, had to use a lookahead
- In the if statement after then only a staement can be used
- When an identifier was found by the parser instead of considering it a variable name if would go directly to expression and consider it a factor. To solve this problem for the affectation and declarations instead of using expression() for the identifier the token IDENTIFIER was used directly
- Like in the if statement, only statements can be used afer IN in the block, not expressions
- Scopes not working correctly when created in the jj file so moved to the evaluation visitor