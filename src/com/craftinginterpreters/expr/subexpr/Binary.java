package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.expr.Visitor;
import com.craftinginterpreters.lox.Token;

public class Binary extends Expr {
    final public Expr left;
    final public Token operator;
    final public Expr right;

    public Binary(Expr left, Token operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitBinaryExpr(this);
    }
}
