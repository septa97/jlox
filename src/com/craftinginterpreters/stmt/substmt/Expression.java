package com.craftinginterpreters.stmt.substmt;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.stmt.Stmt;

public class Expression extends Stmt {
  public final Expr expression;

  public Expression(Expr expression) {
    this.expression = expression;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitExpressionStmt(this);
  }
}
