package fjord;

import static org.junit.Assert.*;
import org.junit.Test;

public class InteractiveTest {
  private Main.Environment env = new Main.Environment();

  @Test public void testValueDefinitions() throws Exception {
    assertEval("let x = 1", "val x : int32 = 1\n");
    assertEval("let x = 1.0", "val x : float = 1.0\n");
    assertEval("let x = false", "val x : bool = false\n");
    assertEval("let x = true", "val x : bool = true\n");
  }

  @Test public void testApplicationExpressions() throws Exception {
    assertEval("let x = 1 + 2", "val x : int32 = 3\n");
    assertEval("let x = 1 - 2", "val x : int32 = -1\n");
  }

  @Test public void testFunctionDefinitions() throws Exception {
    assertEval("let f x = x + 1", "val f : int32 -> int32 = <fun>\n");
    assertEval("let add x y = x + y", "val add : int32 -> int32 -> int32 = <fun>\n");
  }

  @Test public void testPatternMatching() throws Exception {
    assertEval("let matchTest x = match x with | 1 -> \"one\" | _ -> \"other\"", "val matchTest : int32 -> string = <fun>\n");
    assertEval("let matchTest x = match x with | true -> \"true\" | false -> \"false\"", "val matchTest : bool -> string = <fun>\n");
  }

  @Test public void testTypeAnnotations() throws Exception {
    assertEval("let x : int32 = 1", "val x : int32 = 1\n");
    assertEval("let x : float = 1.0", "val x : float = 1.0\n");
  }

  private void assertEval(String input, String output) throws Exception {
    assertEquals(input, output, Main.eval(env, input));
  }

  @Test public void testQuitDirective() throws Exception {
    assertFalse(env.isHalted());
    String output = Main.eval(env, "#quit");
    assertTrue(env.isHalted());
    assertEquals("", output);
  }
}
