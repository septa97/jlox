package com.craftinginterpreters.expr;

import com.craftinginterpreters.expr.subexpr.Binary;
import com.craftinginterpreters.expr.subexpr.Grouping;
import com.craftinginterpreters.expr.subexpr.Literal;
import com.craftinginterpreters.expr.subexpr.Unary;

public interface Visitor<R> {
    R visitBinaryExpr(Binary expr);
    R visitGroupingExpr(Grouping expr);
    R visitLiteralExpr(Literal expr);
    R visitUnaryExpr(Unary expr);
}
