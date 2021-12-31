package org.jason.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

  @Override
  public String handleRequest(Object input, Context context) {
    return "This is: " + input;
  }
}
