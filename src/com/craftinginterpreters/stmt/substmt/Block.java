package com.craftinginterpreters.stmt.substmt;

import com.craftinginterpreters.stmt.Stmt;
import java.util.List;

public class Block extends Stmt {
  public final List<Stmt> statements;

  public Block(List<Stmt> statements) {
    this.statements = statements;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitBlockStmt(this);
  }
}
