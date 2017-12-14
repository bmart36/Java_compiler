default:
	javacc Tigger.jj
	javac -d classes visitor/*.java ast/*.java myUtils/*.java
	javac -d classes *.java
	
for=forLoop
string=strings
while=while
do=doWhile
scope=scopes
check:
	
	cd classes;	java Tigger < $(for); java Tigger < $(string); java Tigger < $(while); java Tigger < $(do); java Tigger < $(scope);
	cd ..

clean:
	$(RM) classes/*.class classes/visitor/*.class classes/ast/*.class classes/myUtils/*.class
	
#	< $(file) 
