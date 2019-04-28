# lexical-analyzer
20165172 WANGZHANYONG 왕천용

>This project develop by java. And I uesed Java develop design patterns and some frameworks for better development.I finished the whole project on my own, so I have no time to complete the final code optimization. Such as use hopcroft algorithm to minimize DFA and make output result file. And some code collation. I've made code comments on important points of source code. So I don't think it's too hard to read code. 

###### Development Environment
> - Idea 2019
> - JDK 1.8
> - Gradle 4
> - lombok 1.18.6

###### Source code File
> App.java (main class)

> entity (base entity)
> > Token,java
> > TokenType.java

> fa (FA Algorithmic Implementation Core Package)
> > entity
> > > ClosureState.java（for NFA to DFA）
> > > DFA.java
> > > NFA.java
> > > State.java.java (fa core entity)
> > > StateGroup.java (fa core entity)
> > > Transition.java (fa core entity)
> > > TransitionTable.java 
> > hander (Algorithmic Implementation)
> > > BaseHandler.java (base handler interface, all of handler have to implement that)
> > > DfaToTransitionTableHandler.java
> > > ExpToNfaHandler.java
> > > LexicalAnalysisHandler.java
> > > NfaToDfaHandler.java

> regular
> > TokenExpressionList.java
> >  entity
> > > TokenExpression.java

###### How to use
> First, We should defind regular expression in need in the TokenExpressionList.java file. (Ps: The order of items determines the priority of parsing)
> Like this
> ```
> expressions.add(new TokenExpression(TokenType.VARTYPE,"(int)|(INT)|(char)|(CHAR)"));
> expressions.add(new TokenExpression(TokenType.INTEGER,"-?[1-9][0-9]*"));
> expressions.add(new TokenExpression(TokenType.IF,"(if)|(IF)"));
> expressions.add(new TokenExpression(TokenType.ELSE,"(else)|(ELSE)"));
> expressions.add(new TokenExpression(TokenType.WHILE,"(while)|(WHILE)"));
> expressions.add(new TokenExpression(TokenType.RETURN,"(return)|(RETURN)"));
> expressions.add(new TokenExpression(TokenType.STRING,"\"[A-Za-z0-9\t\n ]*\""));
> expressions.add(new TokenExpression(TokenType.ID,"[A-Za-z0-9][A-Za-z0-9]*"));
> expressions.add(new TokenExpression(TokenType.ADD,"+"));
> expressions.add(new TokenExpression(TokenType.MIN,"-"));
> expressions.add(new TokenExpression(TokenType.MUL,"*"));
> expressions.add(new TokenExpression(TokenType.DIV,"/"));
> ```
> 	Then, we just running the application **in the main class**(App.java).
> 	


