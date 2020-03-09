package org.jason.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewerDemo extends Application {

  private static final String Google_Translate_URL = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=zh-CN&text=world";

  
  public static void main(String[] args) {
    launch(args);
  }

/**  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("JavaFX WebView Example");

    WebView webView = new WebView();

    webView.getEngine().load(Google_Translate_URL);

    VBox vBox = new VBox(webView);
    Scene scene = new Scene(vBox, 960, 600);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
  */
  
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("JavaFX WebView Example");

    WebView webView = new WebView();

    webView.getEngine().load(Google_Translate_URL);
    
    webView.getEngine().getDocument();
    
    System.out.println(webView.getEngine().getDocument());
    
    
    
    /*
     * VBox vBox = new VBox(webView); Scene scene = new Scene(vBox, 960, 600);
     * 
     * primaryStage.setScene(scene); primaryStage.show();
     */  }
}
