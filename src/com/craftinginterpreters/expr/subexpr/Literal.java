package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.expr.Visitor;

public class Literal extends Expr {
    final public Object value;

    public Literal(Object value) {
        this.value = value;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitLiteralExpr(this);
    }
}
