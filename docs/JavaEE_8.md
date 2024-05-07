
# JavaEE 8 technologies

Java Platform is evolving fast, with more and more technologies standarlized.
Some of the technology is actually inactive for long, some are very exciting.
It is meaningful to have a overall check of all the Java EE technologies.

The following technologies are Java EE 8 standard, so the version of related technologies in the following table are as follows by default unless it is specified:

JavaEE 8 contains a total of 32 JSR, plus 8 related JSR in JavaSE, and a summary (JSR 366)

I originally summerrize on confluence, now I bring it here to continue maintain (Not finished yet)


| Java EE 8 Technology  | Version   |     JSR  | Releaes Date  | JavaEE 8 API  | Implementation | Spring5/SpringBoot 2.2  |
| --------------------- | --------- | -------- | ------------- | ---------- | -------------- | ------------- |
| Servlet               | 4.0                | JSR-369       | Sep 2017     | javax.servlet:javax.servlet-api:4.0.0  | Servlet Glassfish 5 Tomcat Jetty Wildfly |Spring can be configured with embedded Servlet Servers such as Tomcat or Jetty. | 
|CDI|2.0|JSR-365|May 2017|javax.enterprise:cdi-api:2.0 | Full Implement CDI 2: Weld 3(Seam)  - Used by JBOSS <br> Apache OpenWebBeans  - Used by TomEE | Spring/SpringBoot implements CDI in its own way. <br> Spring doesn't implement Java EE CDI 2.0 and is confirmed that they will support CDI 2 in the near future.|
|DI (Included in CDI 2)|1.0|JSR-330|Oct 2009 | javax.inject:javax.inject:1|Implementation of DI: Spring Guice Dagger|Spring implements DI 1.0 (JSR-330) which is a subset of CDI 2.|
|JSON-P|1.1|JSR-374|May 2017|javax.json:javax.json-api:1.1|Implementation of JSON-P: Glassfish javax.json - Used by JBoss| |
|JAX-RS|2.1|JSR-370|Aug 2017|javax.ws.rs:javax.ws.rs-api:2.1| Full Implementation: Jersey(2.27) - Eclipse-RESTeasy - Used by JBOSS; Apache-CXF(3.2.0) - used by TomEE+| |
|WebSocket|1.1|JSR-356|Aug 2014|javax.websocket:javax.websocket-api:1.1|Tyrus - Used by Glassfish; Tomcat, Jetty;Undertow - Used by Wildfly| |
|Java Security API(JASPIC, JAAS, JACC)|1.0|JSR-375|Sep 2017|javax.security.enterprise:javax.security.enterprise-api:1.0|Soteria - Used by Glassfish(org.glassfish.soteria:javax.security.enterprise:1.0)| |
|Batch Applications|1.0|JSR-352|Maintenance Release - Aug 2014|javax.batch:javax.batch-api:1.0.1|JBatch; Spring Batch 3| Spring Batch 3 is a full implementation of JSR 352 |
|JTA|1.2|JSR-907|May 2013|javax.transaction:javax.transaction-api:1.2|Glassfish 5;Apache Geronimo Transaction - Used by TomEE;Narayana(Seam) - Used by Wildfly; Atomikos - A commercial JPA;Bitronix - Not active, but a popular standalone one.|Spring provides an abstraction on top of  Transaction Management, including JTA.|
|JPA|2.2|JSR-338||javax.persistence:javax.persistence-api:2.2|Full Implementation of JPA 2.2; EclipseLink 2.7 onwards; Hibernate 5.3 onwards;DataNucleus 5.1 onwards|Spring DATA JPA is an abstraction which builds on top of other JPA provider such as Hibernate JPA|
|Bean Validation|2.0|JSR-380|Aug 2017|javax.validation:validation-api:2.0.0.final|Hibernate Validator - Used by JBoss;Apache BVal - Used by TomEE |Spring integrates with Hibernate Validation.The thing to flag is that spring doesn't turn on validation by default, there is a need to use @Valid to turn it on|
|JMS|2.0|JSR-343|Maintenance Release: Mar 2015|javax.jms:javax.jms-api:2.0|Eclipse OpenMQ - Used in GlassFish<br> Apache ActiveMQ  - Used by TomEE+;Active MQ 5 - Classic - doesn't support JMS 2 <br> Active MQ Artemis support JMS 2 <br> RabbitMQ - Not a compliance to JMS <br> HornetQ - Full compliance of JMS2, has merged to Artemis <br> Amazon SQS - Support JMS 1.1|
|EJB|3.2|JSR-345|May 2013|javax.ejb:javax.ejb-api:3.2|Apache OpenEJB - Used by TomEE Glassfish 5.0|
|Connector Architecture|1.7|JSR-322|June 2013|javax.resource:javax.resource-api:1.7|GlassFish 5.0 Apache Geronimo Connector - Used by TomEE+|
|JSP|2.3|JSR-245|June 2013|||
|Debugging Support|1.0|JSR-45|Nov 2003|||
|JSTL|1.2|JSR-52|Maintenance Release 2 May 2006|||
|JSF|2.3|JSR-372|April 2017|Apache MyFaces - Used by TomEE+(Not sure TomEE)||
|Common Annotation|1.3|JSR-250|Sep 2016|javax.annotation:javax.annotation-api:1.3||
|EL - Unified Expression Language|3.0|JSR-341|May 2013|||
|Interceptors|1.2|JSR 318|Maintenance Release - Aug 2017|||
|Concurrency Utilities|1.0|JSR-236|May 2013|javax.enterprise.concurrent:javax.enterprise.concurrent-api:1.0|

# JavaEE-8 related Specs in Java SE-8

| Java EE 8 Technology   | Version |     JSR       | Releaes Date  | JavaEE 8 API  | Spring5/SpringBoot 2.2 | Implementation -  Reference Implementation |
| --------------------- | ----------------- | ------------- | ------------- | ------------- | --------------------- | ------------- |
|JMX - Java Management Extensions|2.0|JSR 3||||
|SOAP for SAAJ|1.3|JSR 67||||
|StAX - Streaming API for XML|1.0|JSR-173||||
|JAXB - Java Architecture for XML Binding(Removed from Java 11)|2.2|JSR 224||||
|JAXP - Java API for XML Processing|1.6|JSR-206||||
|JAX-WS - Java API for XML-Based Web Services (Removed from Java 11)|2.2|JSR-224||||
|JAF - JavaBeans Activation Framework(Removed from Java 11)|1.1|JSR-925||||
|JDBC|4.2|||||
|RMI & CORBA(Removed from Java 11)||||||
|Java Beans||||||
