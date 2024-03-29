package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.lox.Token;

public class Logical extends Expr {
  public final Expr left;
  public final Token operator;
  public final Expr right;

  public Logical(Expr left, Token operator, Expr right) {
    this.left = left;
    this.operator = operator;
    this.right = right;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitLogicalExpr(this);
  }
}
