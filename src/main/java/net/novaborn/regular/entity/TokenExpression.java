package net.novaborn.regular.entity;

import net.novaborn.entity.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenExpression {
    TokenType type;
    String expression;
}
