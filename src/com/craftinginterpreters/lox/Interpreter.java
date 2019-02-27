package com.craftinginterpreters.lox;

import com.craftinginterpreters.expr.Expr;
import com.craftinginterpreters.expr.Visitor;
import com.craftinginterpreters.expr.subexpr.Binary;
import com.craftinginterpreters.expr.subexpr.Grouping;
import com.craftinginterpreters.expr.subexpr.Literal;
import com.craftinginterpreters.expr.subexpr.Unary;

class Interpreter implements Visitor<Object> {
  @Override
  public Object visitBinaryExpr(Binary expr) {
    Object left = evaluate(expr.left);
    Object right = evaluate(expr.right);

    switch (expr.operator.type) {
      case BANG_EQUAL:
        return !isEqual(left, right);
      case EQUAL_EQUAL:
        return isEqual(left, right);
      case GREATER:
        checkNumberOperands(expr.operator, left, right);
        return (double) left > (double) right;
      case GREATER_EQUAL:
        checkNumberOperands(expr.operator, left, right);
        return (double) left >= (double) right;
      case LESS:
        checkNumberOperands(expr.operator, left, right);
        return (double) left < (double) right;
      case LESS_EQUAL:
        checkNumberOperands(expr.operator, left, right);
        return (double) left <= (double) right;
      case MINUS:
        checkNumberOperands(expr.operator, left, right);
        return (double) left - (double) right;
      case PLUS:
        if (left instanceof Double && right instanceof Double) {
          return (double) left + (double) right;
        } else if (left instanceof String || right instanceof String) {
          // Strip ".0" if left is a Number
          if (left instanceof Double) {
            String text = left.toString();

            if (text.endsWith(".0")) {
              return text.substring(0, text.length() - 2) + right;
            }
          }

          // Strip ".0" if right is a Number
          if (right instanceof Double) {
            String text = right.toString();

            if (text.endsWith(".0")) {
              return left + text.substring(0, text.length() - 2);
            }
          }

          return left.toString() + right.toString();
        } else {
          throw new RuntimeError(expr.operator, "Operands must be two numbers or two strings.");
        }
      case SLASH:
        checkNumberOperands(expr.operator, left, right);
        checkDivisionByZero(expr.operator, (Double) right);
        return (double) left / (double) right;
      case STAR:
        checkNumberOperands(expr.operator, left, right);
        return (double) left * (double) right;
    }

    // Unreachable
    return null;
  }

  @Override
  public Object visitGroupingExpr(Grouping expr) {
    return evaluate(expr.expression);
  }

  @Override
  public Object visitLiteralExpr(Literal expr) {
    return expr.value;
  }

  @Override
  public Object visitUnaryExpr(Unary expr) {
    Object right = evaluate(expr.right);

    switch (expr.operator.type) {
      case BANG:
        return !isTruthy(right);
      case MINUS:
        checkNumberOperand(expr.operator, right);
        return -(double) right;
    }

    return null;
  }

  private void checkDivisionByZero(Token operator, Double divisor) {
    if (divisor == 0.0) {
      throw new RuntimeError(operator, "Division by zero.");
    }
  }

  private void checkNumberOperand(Token operator, Object operand) {
    if (operand instanceof Double) return;
    throw new RuntimeError(operator, "Operand must be a number.");
  }

  private void checkNumberOperands(Token operator, Object left, Object right) {
    if (left instanceof Double && right instanceof Double) return;
    throw new RuntimeError(operator, "Operands must be numbers.");
  }

  private boolean isEqual(Object left, Object right) {
    if (left == null && right == null) return true;
    if (left == null) return false; // to avoid NPE on left.equals(right)

    return left.equals(right);
  }

  private boolean isTruthy(Object object) {
    if (object == null) return false;
    if (object instanceof Boolean) return (boolean) object;
    return true;
  }

  private Object evaluate(Expr expr) {
    return expr.accept(this);
  }

  void interpret(Expr expression) {
    try {
      Object value = evaluate(expression);
      System.out.println(stringify(value));
    } catch (RuntimeError error) {
      Lox.runtimeError(error);
    }
  }

  private String stringify(Object object) {
    if (object == null) return "nil";

    if (object instanceof Double) {
      String text = object.toString();
      // Remove ".0" for it to become an integer
      if (text.endsWith(".0")) {
        text = text.substring(0, text.length() - 2);
      }

      return text;
    }

    return object.toString();
  }
}
