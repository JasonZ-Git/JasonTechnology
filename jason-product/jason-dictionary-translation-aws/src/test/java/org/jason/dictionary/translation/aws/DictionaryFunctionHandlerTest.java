package org.jason.dictionary.translation.aws;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DictionaryFunctionHandlerTest {

    private static APIGatewayV2HTTPEvent input;

    @BeforeAll
    public static void createInput() throws IOException {
        input = new APIGatewayV2HTTPEvent();
        input.setRawQueryString("hello");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        ctx.setFunctionName("DictionaryFunction");

        return ctx;
    }

    @Test
    public void testDictionaryFunctionHandler() {
        ReadTranslationHandler handler = new ReadTranslationHandler();
        Context ctx = createContext();

        String output = handler.handleRequest(input, ctx);

        Assertions.assertEquals("hello - 你好!,喂!", output);
    }
}
