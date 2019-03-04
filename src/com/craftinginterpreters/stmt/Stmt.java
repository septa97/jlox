package com.craftinginterpreters.stmt;

import com.craftinginterpreters.stmt.substmt.Expression;
import com.craftinginterpreters.stmt.substmt.Print;

public abstract class Stmt {
  public abstract <R> R accept(Visitor<R> visitor);

  public interface Visitor<R> {
    R visitExpressionStmt(Expression stmt);

    R visitPrintStmt(Print stmt);
  }
}
