This file is used to record my random thinking come across with
==============================================================

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