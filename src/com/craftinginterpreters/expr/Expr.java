package com.craftinginterpreters.expr;

import com.craftinginterpreters.expr.subexpr.*;

public abstract class Expr {
  public abstract <R> R accept(Visitor<R> visitor);

  public interface Visitor<R> {
    R visitBinaryExpr(Binary expr);

    R visitGroupingExpr(Grouping expr);

    R visitLiteralExpr(Literal expr);

    R visitUnaryExpr(Unary expr);

    R visitVariableExpr(Variable expr);

    R visitAssignExpr(Assign expr);

    R visitLogicalExpr(Logical expr);

    R visitCallExpr(Call expr);
  }
}
