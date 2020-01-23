This file is used to record my random thinking come across with
==============================================================
23 Jan, 2019
------------
Tree Parser such as DOM parse(include Document, DocumentBuilder, DocumentBuilderFactory)treat XML as a tree structure and is suitable for most XML analysis.
Streaming Parser such as SAX Parser and StAX parser generate event when reading the file, so it is suitable for big and simple XML analysis.
 



19 Jan, 2020
------------
FileChannel is used for memory-mapping, which can be used



15 Jan, 2020
------------
Serializable interface
There is no method in this interface, and if to modify the default serializable method, we need to provide readObject and writeObject method, and are called secretly!!!
There is a benefit: If an Object implements Serializable, then it is easy to implements the Clone method using ByteArrayInputStream and ByteArrayOutputStream.


15 Jan, 2020
------------
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