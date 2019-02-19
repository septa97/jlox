package com.craftinginterpreters.expr;

public abstract class Expr {
    public abstract <R> R accept(Visitor<R> visitor);
}
