package com.craftinginterpreters.stmt;

import com.craftinginterpreters.stmt.substmt.Block;
import com.craftinginterpreters.stmt.substmt.Expression;
import com.craftinginterpreters.stmt.substmt.Print;
import com.craftinginterpreters.stmt.substmt.Var;

public abstract class Stmt {
  public abstract <R> R accept(Visitor<R> visitor);

  public interface Visitor<R> {
    R visitExpressionStmt(Expression stmt);

    R visitPrintStmt(Print stmt);

    R visitVarStmt(Var stmt);

    R visitBlockStmt(Block stmt);
  }
}
