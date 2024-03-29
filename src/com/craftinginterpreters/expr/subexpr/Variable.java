package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.lox.Token;

public class Variable extends Expr {
  public final Token name;

  public Variable(Token name) {
    this.name = name;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitVariableExpr(this);
  }
}
