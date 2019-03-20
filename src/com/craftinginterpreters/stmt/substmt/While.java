package com.craftinginterpreters.stmt.substmt;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.stmt.Stmt;

public class While extends Stmt {
  public final Expr condition;
  public final Stmt body;

  public While(Expr condition, Stmt body) {
    this.condition = condition;
    this.body = body;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitWhileStmt(this);
  }
}
