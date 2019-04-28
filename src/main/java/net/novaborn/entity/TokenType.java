package net.novaborn.entity;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 10:38
 * Description: Defined all of Class of tokens in here
 */
public enum TokenType {
    //Variable type
    VARTYPE,
    //integer
    INTEGER,
    //string
    STRING,
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
    SEMILCOLON,
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
    // \t \n ' '
    WHITESPACE,
    // //
    NOTE;
}
