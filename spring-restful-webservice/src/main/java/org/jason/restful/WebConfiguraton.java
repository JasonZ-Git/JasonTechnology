package org.jason.restful;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguraton {
  
  /**
   * Configure view controller
   * 
   * @return WebMvcConfigurerAdapter that has registered view controllers
   */
  @Bean
  public WebMvcConfigurerAdapter viewControlleConfigure() {
    
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
      }
    };
  }
  
  
}
