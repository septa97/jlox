package com.craftinginterpreters.stmt;

import com.craftinginterpreters.stmt.substmt.*;
import com.craftinginterpreters.stmt.substmt.Class;

public abstract class Stmt {
  public abstract <R> R accept(Visitor<R> visitor);

  public interface Visitor<R> {
    R visitExpressionStmt(Expression stmt);

    R visitPrintStmt(Print stmt);

    R visitVarStmt(Var stmt);

    R visitBlockStmt(Block stmt);

    R visitIfStmt(If stmt);

    R visitWhileStmt(While stmt);

    R visitFunctionStmt(Function stmt);

    R visitReturnStmt(Return stmt);

    R visitClassStmt(Class stmt);
  }
}
