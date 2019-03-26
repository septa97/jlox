package com.craftinginterpreters.expr.subexpr;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.lox.Token;

public class Super extends Expr {
  public final Token keyword;
  public final Token method;

  public Super(Token keyword, Token method) {
    this.keyword = keyword;
    this.method = method;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitSuperExpr(this);
  }
}
