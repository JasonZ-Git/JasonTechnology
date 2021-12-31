package org.jason.aws.lambda;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

  private static Object input;

  @BeforeAll
  public static void createInput() throws IOException {
    input = "Jason";
  }

  private Context createContext() {
    TestContext ctx = new TestContext();

    ctx.setFunctionName("Your Function Name");

    return ctx;
  }

  @Test
  public void testLambdaFunctionHandler() {
    LambdaFunctionHandler handler = new LambdaFunctionHandler();
    Context ctx = createContext();

    String output = handler.handleRequest(null, ctx);

    Assertions.assertEquals("Hello Jason!", output);
  }
}
