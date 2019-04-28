package net.novaborn.regular;

import net.novaborn.entity.TokenType;
import lombok.Getter;
import net.novaborn.regular.entity.TokenExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-11
 * Time: 14:20
 * Description:
 */
public class TokenExpressionList {
    @Getter
    static private final List<TokenExpression> expressions = new ArrayList<>();

    static {
        expressions.add(new TokenExpression(TokenType.VARTYPE,"(int)|(INT)|(char)|(CHAR)"));
        expressions.add(new TokenExpression(TokenType.INTEGER,"-?[1-9][0-9]*"));
        expressions.add(new TokenExpression(TokenType.IF,"(if)|(IF)"));
        expressions.add(new TokenExpression(TokenType.ELSE,"(else)|(ELSE)"));
        expressions.add(new TokenExpression(TokenType.WHILE,"(while)|(WHILE)"));
        expressions.add(new TokenExpression(TokenType.RETURN,"(return)|(RETURN)"));
        expressions.add(new TokenExpression(TokenType.STRING,"\"[A-Za-z0-9\t\n ]*\""));
        expressions.add(new TokenExpression(TokenType.ID,"[A-Za-z0-9][A-Za-z0-9]*"));
        expressions.add(new TokenExpression(TokenType.ADD,"+"));
        expressions.add(new TokenExpression(TokenType.MIN,"-"));
        expressions.add(new TokenExpression(TokenType.MUL,"*"));
        expressions.add(new TokenExpression(TokenType.DIV,"/"));
        expressions.add(new TokenExpression(TokenType.ASSIGN,"="));
        expressions.add(new TokenExpression(TokenType.LT,"<"));
        expressions.add(new TokenExpression(TokenType.GT,">"));
        expressions.add(new TokenExpression(TokenType.LTOREQ,"<="));
        expressions.add(new TokenExpression(TokenType.GTOREQ,">="));
        expressions.add(new TokenExpression(TokenType.EQUAL,"=="));
        expressions.add(new TokenExpression(TokenType.NEQUAL,"!="));
        expressions.add(new TokenExpression(TokenType.SEMILCOLON,";"));
        expressions.add(new TokenExpression(TokenType.LPAREN,"("));
        expressions.add(new TokenExpression(TokenType.RPAREN,")"));
        expressions.add(new TokenExpression(TokenType.LBRACE,"{"));
        expressions.add(new TokenExpression(TokenType.RBRACE,"}"));
        expressions.add(new TokenExpression(TokenType.COMMA,","));
        expressions.add(new TokenExpression(TokenType.WHITESPACE,"[\t\n ]"));
        expressions.add(new TokenExpression(TokenType.NOTE,"//"));
    }
}
