package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.expr.Visitor;
import com.craftinginterpreters.lox.Token;

public class Unary extends Expr {
  public final Token operator;
  public final Expr right;

  public Unary(Token operator, Expr right) {
    this.operator = operator;
    this.right = right;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitUnaryExpr(this);
  }
}
