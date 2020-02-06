This file is used to record my random brain storm
===============================================================

TODO List
---------
  -- Regex
  -- Think a way to perfectly support JSON in Java
  -- PrintWriter, Scanner
  -- URL & URI
  -- HTTP/2
  -- GraphQL
  -- Java Memory Model


06 Feb, 2020
------------
Several Locale related classes
    Locale -- country & language
    Currency - getInstance
    NumberFormat -  getCurrencyInstance, getNumberInstance, getIntegerInstance, getPercentInstance.
    Collator - Collator.getInstance - localized comparator
	ResourceBundle - getBundle, 


05 Feb, 2020
------------
Java Network
	-- Socket
			- Socket is quite fundamental level
			- Socket, ServerSocket together with InputStream OutputStream
	-- Connection
			- Connection Level is build on top of Socket.
			- URL, URLConnection, HttpURLConnection
	-- HttpClient
			- From Java 9, complete in Java 11
			- Support Http/2
			- Specially designed for HTTP
			- HttpClient, HttpRequest, HttpResponse, HttpRequest.BodyPublisher, HttpRequest.BodyPublishers

Common facility 
    -- InetAddress - Convert IP address and domain name


04 Feb, 2020
------------
Git Advanced:
	git symbolic-ref refs/heads/trunk refs/heads/master -- Alias a branch name
	git symbolic-ref -d refs/heads/trunk                -- Do not use git branch -d to delete a symbolic -- Delete a symbolic created above: 
	git merge-base branch_one branch_two                -- Find a common base of 2 branches(or commits)
	git diff commit1 commit2 --name-only               -- Find difference between commits, not include the first commit, to include use commit1^()
	

03 Feb, 2020
------------
Data sharing technology in Java
There are 3 different level of Data exchange technologies:
Information Exchange
    -- CPU Level - CPU Cache - Can be regarded the same as Memory Level data share.
    -- Memory Level - Variable (Object) 
    	- Memory Level is more efficient than efficient than Disc and Internet Level due to speed.
    	- All disc level & Internet Level information will be exchanged via its own memory.
    -- Disc Level - File, Database
    -- Internet Level - WebService, or 

Different processes(programmes) can share data through Disc Level and Internet Level.
Different threads within the same process can share data through Memory Level, Disc level and Internet level.

As Memory Level is far more efficient than Disc Level and Internet Level, thus provide the


30 Jan, 2020
------------
Why are we still using abstract class?? - TODO
 (java.time.clock)


29 Jan, 2020
------------
Summary the bad design of old java Date
    -- Date is mutable, which makes it not safe.
	-- Date constructor Date(year, month, day), the year is actually (year - 1900), month is (month - 1)
	-- Calendar
	-- 
	

New time API: java.time
LocalDate, LocalTime, Period, Duration, ZonedDateTime, DateTime, Instant, DateTimeFormatter, ZoneId, Clock
TemporalAdjusters. TemporalAmount, TemporalAdjuster -- Needs more in deep check -- TODO


28 Jan, 2020
------------
Java DB project is directly from Apache Derby, it was originally contained within JDK 6 and removed from JDK 8.
Apache Derby is actively developed and is a embedded DB that is pure Java, support JDBC and support standard SQL.

HSQLDB is another embedded DB system, which seems gain more popularity(Used by LibreOffice and OpenOffice).
	-- HSQLDB support JDBC 4 and closely to SQL2011 standard.
	-- HSQLDB support table level locking.
	-- jdbc:hsqldb:hsql://<HOST>/testdb   org.hsqldb.jdbc.JDBCDriver


27 Jan, 2020
------------
There are 4 types of JDBC Driver
	-- Type 1 
		- JDBC-ODBC Bridge Driver
		- It converts JDBC to ODBC call 
		- Not recommended to use
	-- Type 2 
		- JDBC-Native API Driver
		- It is partly java, and converts to native code. 
		- jdbc:oracle:oci is this type
		- Not recommended
	-- Type 3 
		- JDBC-Network Protocol Driver
		- Pure Java implementation
		- Client code just needs to use standard Java, all the implementation is within server which connects to DB
		- Partly Recommended - If there is no type 4 available, then use type 3
	-- Type 4
		- JDBC-Database Protocol Driver
		- Also called thin driver
		- Pure Java implementation
		- MySQL support it: jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>     com.mysql.jdbc.Driver
		- Oracle support it: jdbc:oracle:thin@<HOST>:<PORT>:<DATABASE_NAME>     oracle.jdbc.driver.OracleDriver
		- Recommended - Most DB system supports this thin type, though some doesn't contain thin in the name


24 Jan, 2020
------------
Common XML libraries with Java
	-- JDK - DOMm SAX StAX - JDK provides Reading and writing XML files through DOM, SAX and StAX, default one is Apache xerces inside.
		   - XSLT - JDK provide TransformerFactory, the default one is Apache Xalan, but could be modified to Saxon.
	-- JAXB - Java Architecture for XML Binding
			- It deals with mapping between Java classes and XML files
			- It has been removed from JDK from Java11, and one popular implementation is com.sun.xml.bind-jaxb-impl (JAXBContext Unmarshaller, Marshaller)
	-- Xalan - Apache Xalan Project
		     - implements XLST 1.0 and XPath1.0
    -- Saxon - Implements XLST 2.0 3.x and XPath 2.0, 3.x, XQuery 3.x


23 Jan, 2020
------------
DOM is a tree structure parsing XML, there is another way: Streaming XML SAX and StAX

  -- SAX - Simple API for XML, also known as Streaming XML.
	     - An event-driven online algorithm for parsing XML documents.
	     - SAX parsers operate on each piece of the XML document sequentially.
	     - SAX is Part of JDK(SAXParseFactory)
  -- StAX - Streaming API for XML
          - It is originated from Java Community, not managed by W3C.
          - It is similar to SAX, but will pull information itself, not like SAX which push data through handle.
          - StAX is easier for programming purpose.
          - StAX is Part of JDK(XMLInputFactory)


22 Jan, 2020
------------

XML has quite a few technologies around it  - XQuery, Xpath, Dom, SAX, XSL, XSLT, JAXB, dtd, xsd,
The following technologies are around XML and managed by W3C

  -- XML - eXtensible Markup Language - 
  		 - Simple, flexible text format derived from SGML
  		 - Originally designed to meet the challenges of large-scale electronic publishing
  		 - Also playing an increasingly important role in the exchange of a wide variety of data on the Web and elsewhere
  		 - Managed by W3C
  		 
  -- DOM - Document Object Model 
  		 - A programming API for HTML and XML documents
		 - Managed by W3C
		 
  -- XSL - eXtensible Stylesheet Language for XML
         - A family of recommendations for defining XML document transformation and presentation including XSLT, XPath, XSL-FO
         
  -- XSLT - eXtensible Stylesheet Language Transformations
		  - A language for transforming XML documents into other XML documents or other formats such as HTML for web pages, plain text or XSL Formatting Objects
		  - XSLT uses XPath to identify subsets of the source document tree and perform calculations
		  - XSLT 1.0 support basic operations, XSLT 2.0 support date, time and regex for String, XSLT 3.0 support Streaming XML
		  - Managed by W3C
		  
  -- XSD - XML Schema Definition 
         - Validate the structure of another XML
         - Managed by W3C
		  
  -- XPath - XML Path Language.
  		   - A query language for selecting nodes from an XML document.
  		   - May also be used to compute values From XML document.
  		   - XPath 2.0 is built around the XQuery and XDM, XPath 3.0 support functions as first class value., XPath 3.1 add map and array type so to support JSON
  		   - Managed by W3C		   
  
  -- XQuery - XML Query
            - XQuery is the language to query XML.
            - XQuery is a standardized language for combining documents, databases and Web pages
            - XQuery is a query and functional programming language that queries and transforms collections of structured and unstructured data such as XML, JSON and even database.
            - Provide flexible query facilities to extract data from real and virtual documents on the World Wide Web.
  		    - XQuery is to XML what SQL to databases.
  		    - Managed by W3C
  		    
  -- XDM - XQuery and XPath Data Model -- TODO
         - Managed by W3C


21 Jan, 2020
------------
Tree Parser such as DOM parse(include Document, DocumentBuilder, DocumentBuilderFactory)treat XML as a tree structure and is suitable for most XML analysis.
Streaming Parser such as SAX Parser and StAX parser generate event when reading the file, so it is suitable for big and simple XML analysis.


19 Jan, 2020
------------
FileChannel is used for memory-mapping, which can be used


18 Jan, 2020
------------
Regex is very powerful and I used in some scenarios during daily work.
However, seems I never get a point to fully understand it.
It is time now - Master Regex in one Week.


17 Jan, 2020
------------
CyclicBarrier is similar to CountdownLatch, but can be used repeatedly once released.


15 Jan, 2020
------------
Serializable interface
There is no method in this interface, and if to modify the default serializable method, we need to provide readObject and writeObject method, and are called secretly!!!
There is a benefit: If an Object implements Serializable, then it is easy to implements the Clone method using ByteArrayInputStream and ByteArrayOutputStream.

Clonable interface 
Clonable interface is ridiculous design, there is no interface, but once you implements it, we need to override the clone() method of Object - Bad Design !!!
What we should do is to remove the clone method in Object class and add it to Clonable.

Java needs to maintain backward compatibility so there is no easy way to change fundamental class Object.

14 Jan, 2020
------------
ThreadLocal seems a bad design, it is best not use it.
Stream - groupBy is a very useful method together with Collector
		reduce can be used when there is no direct function of stream, but it should be a second choice as the function is not self-explaining.

Parallel Stream is suitable for most cases if there is no sequence required - it is not used that much only because it is fast enough - Stream is used for simple calculation only.

13 Jan, 2020
------------
Today I started "Core Java Volume II - Advanced Features", and expected to fully finish it within 6 weeks from day.
This week, I plan to finish the first chapter - About Stream.


12 Jan, 2020
------------
Restrictions has been used in Hibernate - not too bad though.


11 Jan, 2020
------------
I have created class which is very useful for Parameter Check and used to enhance Objects, functions should be:
-- requireNonnull, requireNull, requireEquals, requireNotEquals, requireSameDay.

It should be used for Parameter and result check of a call.
I considered several names for it, Parameters and Requirements
-- Parameters would indicate it is used for Parameters check only; 
-- Requirements seem better, but would be duplicate to the function name;
-- Restrictions could be best 


10 Feb, 2019
------------

Gson and Jackson can both be used to parse Json String & Objects  - What is the difference?

These days, I use GraphQL as it is required by workflow innovation project. GraphQL seems promising, however, there is no utility 
to convert between JSON string and GraphQL query - Is it possible to write a utility?

When I read Gson, I noticed it uses Type, and I noticed interface have implicitly public method from Object, although it does not inherit from Object.
Why is it designed this way?