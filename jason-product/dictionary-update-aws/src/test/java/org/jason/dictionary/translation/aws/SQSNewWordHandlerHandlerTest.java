package org.jason.dictionary.translation.aws;

import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

/** A simple test harness for locally invoking your Lambda function handler. */
public class SQSNewWordHandlerHandlerTest {

  private static SQSEvent input;

  @BeforeAll
  public static void createInput() throws IOException {
    input = new SQSEvent();
  }

  private Context createContext() {
    TestContext ctx = new TestContext();

    ctx.setFunctionName("DictionaryFunction");

    return ctx;
  }

  @Test
  public void testDictionaryFunctionHandler() {
    PersistTranslationHandler handler = new PersistTranslationHandler();
    Context ctx = createContext();

    // handler.handleRequest(input, ctx);
  }
}
