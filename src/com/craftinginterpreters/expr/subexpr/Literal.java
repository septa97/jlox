package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;

public class Literal extends Expr {
  public final Object value;

  public Literal(Object value) {
    this.value = value;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitLiteralExpr(this);
  }
}
