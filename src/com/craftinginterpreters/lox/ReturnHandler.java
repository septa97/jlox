package com.craftinginterpreters.lox;

public class ReturnHandler extends RuntimeException {
  public final Object value;

  public ReturnHandler(Object value) {
    super(null, null, false, false);
    this.value = value;
  }
}
