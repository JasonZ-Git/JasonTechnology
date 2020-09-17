
Java Platform is evolving fast, with more and more technologies standarlized.
Some of the technology is actually inactive for long, some are very exciting.
It is meaningful to have a overall check of all the Java EE technologies.

The following technologies are Java EE 8 standard, so the version of related technologies in the following table are as follows by default unless it is specified:

JavaEE 8 contains a total of 32 JSR, plus 8 related JSR in JavaSE, and a summary (JSR 366)

I originally summerrize on confluence, now I bring it here to continue maintain (Not finished yet)


# This is the the full picture of all JavaEE 8 technologies

| Java EE 8 Technology   | Version            |     JSR       | Releaes Date  | JavaEE 8 API  | Spring5/SpringBoot 2.2 | Implementation -  Reference Implementation |
| --------------------- | ----------------- | ------------- | ------------- | ------------- | --------------------- | ------------- |
| Servlet                | 4.0                | JSR-369       | Sep 2017      | javax.servlet:javax.servlet-api:4.0.0  | Spring can be configured with embedded Servlet Servers such as Tomcat or Jetty. | Servlet Glassfish 5 Tomcat Jetty Wildfly |
|CDI|2.0|JSR-365|May 2017|javax.enterprise:cdi-api:2.0|Spring/SpringBoot implements CDI in its own way.Spring doesn't implement Java EE CDI 2.0 and is confirmed that they will support CDI 2 in the near future.|Full Implement CDI 2: Weld 3(Seam)  - Used by JBOSS;Apache OpenWebBeans  - Used by TomEE|
|DI (Included in CDI 2)|1.0|JSR-330|Oct 2009|javax.inject:javax.inject:1|Spring implements DI 1.0 (JSR-330) which is a subset of CDI 2.|Implementation of DI: Spring Guice Dagger|
|JSON-P|1.1|JSR-374|May 2017|javax.json:javax.json-api:1.1| |Implementation of JSON-P: Glassfish javax.json - Used by JBoss|
|JAX-RS|2.1|JSR-370|Aug 2017|javax.ws.rs:javax.ws.rs-api:2.1| |Full Implementation: Jersey(2.27) - Eclipse-RESTeasy - Used by JBOSS; Apache-CXF(3.2.0) - used by TomEE+|
|WebSocket||||||
|Java Security API(JASPIC, JAAS, JACC)||||||
|Batch Applications||||||
|JTA||||||
|JPA||||||
|Bean Validation||||||
|JMS||||||
|Java Mail||||||
|EJB||||||
|Connector Architecture||||||
|JSP||||||
|Debugging Support||||||
|JSTL||||||
|JSF||||||
|Common Annotation||||||
|EL - Unified Expression Language||||||
|Interceptors||||||
|Concurrency Utilities||||||


# JavaEE-8 related Specs in Java SE-8

| Java EE 8 Technology   | Version            |     JSR       | Releaes Date  | JavaEE 8 API  | Spring5/SpringBoot 2.2 | Implementation -  Reference Implementation |
| --------------------- | ----------------- | ------------- | ------------- | ------------- | --------------------- | ------------- |
|JMX - Java Management Extensions||||||
|SOAP for SAAJ||||||
|StAX - Streaming API for XML||||||
|JAXB - Java Architecture for XML Binding(Removed from Java 11)||||||
|JAXP - Java API for XML Processing||||||
|JAX-WS - Java API for XML-Based Web Services (Removed from Java 11)||||||
|JAF - JavaBeans Activation Framework(Removed from Java 11)||||||
|JDBC||||||
|RMI & CORBA(Removed from Java 11)||||||
|Java Beans||||||
