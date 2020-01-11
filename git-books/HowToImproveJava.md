# This file records my thinking of improving java language, including the design issue & JDK libraries

# Date & Calendar improvement
Date has long been an issue for Java, the main problem for Date API are:
-- Date is mutable - which causes potential safety issues.
-- Date API is badly designed.
-- Date doesn't contain timezone.
-- Date actually represent date and time.
-- The default Date.equals method is odd - it actually compares the inner long value - which is kind of unexpected.
-- Even the Calendar has an odd flaw - the month starts from 0 instead of 1

As Date is so important and so bad, so an alternative joda Date is used as a de-factor Date.
From Java 8, however, there is a new date API introduced which addresses all above issues.
This is a great - though not widely accepted so far for 2 reasons: 
-- There are too much legacy code using Date.
-- The new Date seems assume people with start fresh.
So my suggestion is: for green field applications, use LocalDate is convenient; for legacy code, it is really needs careful planning to switch to this new API.

# Enhance switch
Switch has been enhanced to handle Enum, int and String, but it should be enhanced for more.
- One way is use hashCode so that JVM don't need to handle extra rules.
- Another way is to introduce an interface - Switchable, that interface should contain a single method which returns an int value(it should be a default method, which returns the hashCode value), every class that implements the interface can be used for switch.

interface Switchable {

	default int switchValue() {
		return this.hashCode();
	}
}

# Enum
Every enum type extends Enum implicitly, but there is a secretly added method values() and valueOf by compiler.
That should be a way to use the standard Enum class, instead of secretly doing this doggy. - @TODO Futher action needed.
Enum should be an interface, instead of abstract class.

# double & float issue
First, float should be removed, and only keep double.
Second, float & double cannot be precisely represented decimal number, while we usually use decimal to do calculation.
We should introduce a Number (currently in Java, Number is a abstract class for Long, Short, and others)

More thinking: when Java is designed, memory is expensive, so a lot of consideration has been designed to make java run fast with least memory.

# Java for Desktop application - Kick off Swing and AWT
AWT is a terrible, Swing also fails to satisfy people.
The fundamental reason is that using code to draw UI is low efficient.
Now we know that we should reply on template, to handle static part,  JS to control dynamic part, and CSS to control the style.
So in model design, we use HTML, JS, CSS these 3 standards to provide UI solution which is handled by Swing & AWT -- that is too heavy a task.

So we should depends on template from the beginning, get rid of Swing and AWT, use web to do the UI.

# Using Generic from the beginning - make it a must not just recommendation.


# Redesign Serialization and Clonable interface
These 2 interfaces are doesn't are dummy interfaces, and jvm secretly do operation on there subclasses. Best to use annotation to replace these kind of interfaces.


# New Stream API - TODO - Think how to improve it from language level
The stream API is great to simplify simple loops and make java cool again
However, it is not perfect, There are 2 pain points
-- The code is hard to read, however, it ensures easy usage - so it is not really bad.
-- Stream is hard to be debugged, and what's more, and not quite friendly picked by eclipse - there are some work to do.
-- The main problem lies with exception - if an operation within stream throws exception, it cann't be caught outside, it must be handled within the stream - this is really bad - TODO - I need to invest more time into it and check whether it is possible to design a solution for it.

# Assert 
Assert is a wired design - maybe good at the start when there is no good TDD.
Now with TDD, we can use it more gracefully than the wired assert.
So I think this feature should be removed from Java.
One more suggestion is that there should be a new class added for Parameter check or result value check purpose.
There are several ways - Introducing a new utility class - Requirements, this class can server as parameter check or other check purpose"

public final class Requirements {
	requireNonNull();
	requireNull();
	requireTrue();
	requireEquals();
	requireFalse();
}