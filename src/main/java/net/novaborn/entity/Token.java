package net.novaborn.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 13:34
 * Description:
 */
@Data
public class Token {
    TokenType name;
    String value;
}