package com.craftinginterpreters.stmt.substmt;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.lox.Token;
import com.craftinginterpreters.stmt.Stmt;

public class Var extends Stmt {
    public final Token name;
    public final Expr initializer;

    public Var(Token name, Expr initializer) {
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visitVarStmt(this);
    }
}
