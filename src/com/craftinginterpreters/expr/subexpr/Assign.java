package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.lox.Token;

public class Assign extends Expr {
  public final Token name;
  public final Expr value;

  public Assign(Token name, Expr value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitAssignExpr(this);
  }
}
