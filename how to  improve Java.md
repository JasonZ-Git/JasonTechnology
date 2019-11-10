This is my thinking of improving java

## Date & Calendar improvement
Date as has been replaced by LocalDate LocalTime new API will not comment more - Improved with timezone & Make new LocalDate unchangable.
For Calendar, there is problem as well: month starts from 0 instead of 1 which is wired.

## double & float issue
First, float should be removed, and only keep double.
Second, float & double cannot be precisely represented decimal number, while we usually use decimal to do calculation.
We should introduce a Number (currently in Java, Number is a abstract class for Long, Short, and others)

More thinking: when Java is designed, memory is expensive, so a lot of consideration has been designed to make java run fast with least memory.


## Java for Desktop application - Kick off Swing and AWT

AWT is a terrible, Swing also fails to satisfy people.
The fundamental reason is that using code to draw UI is low efficient.
Now we know that we should reply on template, to handle static part,  JS to control dynamic part, and CSS to control the style.
So in model design, we use HTML, JS, CSS these 3 standards to provide UI solution which is handled by Swiing & AWT -- that is too heavy a task.

So we should depends on template from the beginning, get rid of Swing and AWT, use web to do the UI.

## Using Generic from the beginning - make it a must not just recommendataion.

## Redesign Serialization and Clonable interface
These 2 interfaces are doesn't are dummy interfaces, and jvm secretly do operation on there subclasses. Best to use annotation to replace these kind of interfaces.

## 
