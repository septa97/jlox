package com.craftinginterpreters.stmt.substmt;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.lox.Token;
import com.craftinginterpreters.stmt.Stmt;

public class Return extends Stmt {
  public final Token keyword;
  public final Expr value;

  public Return(Token keyword, Expr value) {
    this.keyword = keyword;
    this.value = value;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitReturnStmt(this);
  }
}
