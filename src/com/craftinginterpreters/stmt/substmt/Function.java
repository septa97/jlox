package com.craftinginterpreters.stmt.substmt;

import com.craftinginterpreters.lox.Token;
import com.craftinginterpreters.stmt.Stmt;
import java.util.List;

public class Function extends Stmt {
  public final Token name;
  public final List<Token> params;
  public final List<Stmt> body;

  public Function(Token name, List<Token> params, List<Stmt> body) {
    this.name = name;
    this.params = params;
    this.body = body;
  }

  @Override
  public <R> R accept(Visitor<R> visitor) {
    return visitor.visitFunctionStmt(this);
  }
}
