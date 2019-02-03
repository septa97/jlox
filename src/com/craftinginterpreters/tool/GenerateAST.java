package com.craftinginterpreters.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAST {
  private static String tab = "    ";

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.err.println("Usage: generate_ast <output directory>");
      System.exit(1);
    }

    String outputDir = args[0];
    System.out.println(outputDir);
    defineAST(
        outputDir,
        "Expr",
        Arrays.asList(
            "Binary     : Expr left, Token operator, Expr right",
            "Grouping   : Expr expression",
            "Literal    : Object value",
            "Unary      : Token operator, Expr right"));
  }

  private static void defineAST(String outputDir, String baseName, List<String> types)
      throws IOException {
    String path = outputDir + "/" + baseName + ".java";
    PrintWriter writer = new PrintWriter(path, "UTF-8");

    writer.println("package com.craftinginterpreters.lox;");
    writer.println();
    writer.println("import java.util.List;");
    writer.println();
    writer.println("abstract class " + baseName + " {");

    defineVisitor(writer, baseName, types);

    // The AST classes
    for (String type : types) {
      String[] splits = type.split(":");
      String className = splits[0].trim();
      String fields = splits[1].trim();
      defineType(writer, baseName, className, fields);
    }

    // The base accept() method
    writer.println(tab + "abstract <R> R accept(Visitor<R> visitor);");

    writer.println("}");
    writer.close();
  }

  private static void defineType(
      PrintWriter writer, String baseName, String className, String fieldList) {
    String[] fields = fieldList.split(", ");
    writer.println(tab + "static class " + className + " extends " + baseName + " {");

    // Fields
    for (String field : fields) {
      writer.println(tab + tab + "final " + field + ";");
    }
    writer.println();

    // Constructor
    writer.println(tab + tab + className + "(" + fieldList + ") {");

    // Store parameters in fields
    for (String field : fields) {
      String name = field.split(" ")[1];
      writer.println(tab + tab + tab + "this." + name + " = " + name + ";");
    }

    writer.println(tab + tab + "}");

    // Visitor pattern
    writer.println();
    writer.println(tab + tab + "<R> R accept(Visitor<R> visitor) {");
    writer.println(tab + tab + tab + "return visitor.visit" + className + baseName + "(this);");
    writer.println(tab + tab + "}");

    writer.println(tab + "}");
    writer.println();
  }

  private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
    writer.println(tab + "interface Visitor<R> {");

    for (String type : types) {
      String typeName = type.split(":")[0].trim();
      writer.println(
          tab
              + tab
              + "R visit"
              + typeName
              + baseName
              + "("
              + typeName
              + " "
              + baseName.toLowerCase()
              + ");");
    }

    writer.println(tab + "}");
    writer.println();
  }
}
