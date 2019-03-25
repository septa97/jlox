package com.craftinginterpreters.stmt.substmt;

import com.craftinginterpreters.lox.Token;
import com.craftinginterpreters.stmt.Stmt;
import java.util.List;

public class Class extends Stmt {
  public final Token name;
  public final List<Function> methods;

  public Class(Token name, List<Function> methods) {
    this.name = name;
    this.methods = methods;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitClassStmt(this);
  }
}
