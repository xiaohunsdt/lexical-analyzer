package entity;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 13:38
 * Description:
 */

/**
 * Defined all of Class of tokens in here
 */
public enum TokenType {
    //int
    INT,
    //char
    CHAR,
    //function and variables
    ID,
    //if
    IF,
    //else
    ELSE,
    //while
    WHILE,
    //return
    RETURN,
    //+
    ADD,
    //-
    MIN,
    //*
    MUL,
    // /
    DIV,
    // =
    ASSIGN,
    // <
    LT,
    // >
    GT,
    // ==
    EQUAL,
    //!=
    NEQUAL,
    // <=
    LTOREQ,
    // >=
    GTOREQ,
    // ;
    TERMINATE,
    // (
    LPAREN,
    // )
    RPAREN,
    // {
    LBRACE,
    // }
    RBRACE,
    // ,
    COMMA,
    // \t
    TABC,
    // \n
    BREAKC,
    // ' '
    WHITESPACE;
}
