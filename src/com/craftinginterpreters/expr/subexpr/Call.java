package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.lox.Token;
import java.util.List;

public class Call extends Expr {
  public final Expr callee;
  public final Token paren;
  public final List<Expr> arguments;

  public Call(Expr callee, Token paren, List<Expr> arguments) {
    this.callee = callee;
    this.paren = paren;
    this.arguments = arguments;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitCallExpr(this);
  }
}
