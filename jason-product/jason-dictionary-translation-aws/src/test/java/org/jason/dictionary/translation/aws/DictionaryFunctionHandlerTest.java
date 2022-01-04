package org.jason.dictionary.translation.aws;

import java.io.IOException;
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
        // TODO: set up your sample input object here.
        input = new APIGatewayV2HTTPEvent();
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testDictionaryFunctionHandler() {
        ReadTranslationHandler handler = new ReadTranslationHandler();
        Context ctx = createContext();

        // String output = handler.handleRequest(input, ctx);

        // Assertions.assertEquals("Hello from Lambda!", output);
    }
}
