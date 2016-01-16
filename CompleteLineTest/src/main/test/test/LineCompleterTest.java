package test;

import static org.junit.Assert.*;

import org.junit.Test;

import henri5.LineCompleter;

public class LineCompleterTest {

  @Test
  public void testSemiColonMatch() {
    assertTrue(s("new foo()"));
    assertTrue(s("new foo(bar, baz)"));
    assertTrue(s("int i"));
    assertTrue(s("int i = asd"));
    assertTrue(s("String s = \"endOfLine\""));
    assertTrue(s("String s = \"endOfLine;\""));
    assertTrue(s("String s = \"endOfLine;\".toString()"));
    assertTrue(s("throw foo()"));
    assertTrue(s("throw foo"));
    assertTrue(s("import foo.bar"));
    assertTrue(s("import static foo.bar.Baz"));
    assertTrue(s("if (true) return false"));
    assertTrue(s(" if (foo()) return bar"));
    //assertTrue(s(" if (foo()) return bar()"));
    assertTrue(s("Foo.bar(bat())"));
    assertTrue(s("Foo.bar(baz, bat())"));
    assertTrue(s("String at = \"@\""));
    assertTrue(s("foo()"));

    assertFalse(s(""));
    assertFalse(s(" "));
    assertFalse(s("new foo();"));
    assertFalse(s("void foo()"));
    assertFalse(s("String s = \"endOfLine;\".toString();"));
    assertFalse(s("@Test"));
    assertFalse(s("@Ignore(\"becauseWhyNot\")"));
  }

  private boolean s(String string) {
    return LineCompleter.canInsertSemicolon(string);
  }

  @Test
  public void testCurlyBracketMatch() {
    assertTrue(c("public static void foo()"));
    assertTrue(c("Foo.bar foo()"));
    assertTrue(c("@NotNull Foo foo()"));
    assertTrue(c("void foo()"));
    assertTrue(c("Bar foo(Baz baz, Bat.Man batman)"));
    assertTrue(c(" if (true)"));
    assertTrue(c("if (true) "));
    assertTrue(c("\twhile (something) "));
    assertTrue(c(" else if (true)"));
    assertTrue(c("} else if (true)"));
    assertTrue(c("public static class Foo"));
    assertTrue(c("public static class Foo implements Bar.Baz"));
    assertTrue(c("protected abstract class Foo extends Bar"));
    assertTrue(c("protected abstract class Foo extends Bar implements Baz"));
    assertTrue(c("interface Foo"));
    assertTrue(c("private interface Foo implements Bar"));
    assertTrue(c("enum Foo"));
    assertTrue(c("\tfor (int i = 0; i < 100; i++)"));

    assertFalse(c(""));
    assertFalse(c("\t"));
    assertFalse(c("public static int = foo()"));
    assertFalse(c("new foo()"));
    assertFalse(c("throw new foo()"));
    assertFalse(c("fakeenum Foo"));
    assertFalse(c("enumfake Foo"));
    assertFalse(c("enum"));
    assertFalse(c("throw foo()"));
    assertFalse(c(" functionif (true)"));
    assertFalse(c(" if (true) {"));
    assertFalse(c("enum Foo {"));
    assertFalse(c("public static class Foo {"));
    assertFalse(c("void foo() {"));
    assertFalse(c("public classic Foo"));
    assertFalse(c("Foo.bar(Baz.class)"));
    assertFalse(c("Foo.bar(bat())"));
    assertFalse(c("foo()"));
  }

  private boolean c(String string) {
    return LineCompleter.canInsertCurlyBrackets(string);
  }
}
