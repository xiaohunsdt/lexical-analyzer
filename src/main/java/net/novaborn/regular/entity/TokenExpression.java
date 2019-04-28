package net.novaborn.regular.entity;

import net.novaborn.entity.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 10:20
 * Description:
 */
@Data
@AllArgsConstructor
public class TokenExpression {
    TokenType type;
    String expression;
}
