# Volatile
Volatile is used on variables, there is no locking
Volatile guaranteed visibility of variables across different threads, but no atomicity is guaranteed
Volatile is most used for flag variables as the operations on these variables are atomitic.


# ConcurrentHashMap
ConcurentHashMap locks by segement instead of locking the whole map thus provides much better performance.
It is much better than HashTable or synchronisedMap(Collections.synchronisedMap)


# CopyOnWriteArrayList
This list, as indicate by its name, will create a new one if modifies thus ensures multiple thread safety.
However, as the java doc said, it is usually costly unless the traversal operations are much more than mutable operations.
So only use this collection when there are much more operation for traversal.


# Atomic 
Atomic Variables are usually considered to be better than Volatile.
It used Volatile inside.(AtomicInteger)


# ReentrantLock & sychronised
ReentrantLock is one type of lock which allows one lock could be aquaired multiple times within one thread so it could effectively avoid deadlock for acquiring the same lock within one programe.
synchronised keyword used ReentrantLock inside it.
Within one thread, a synchronised method could call another synchronised method because it is an ReentrantLock.


# ReentrantReadWriteLock
This lock contains 2 locks, a ReadLock and a WriteLock. ReadLock can be re-acquired by different threads as long there is no WriteLock.


# StampedLock
This is a new lock introduced in Java 8, which returns a long value (which is called stamp) when lock, this stamp stands for the lock and can be used to unlock. 


# BlockingQueue
This is a Queue, with a set of feature that can block the operation nothing in the queue - There are 4 sets of operations which will allow blocking, throw exception, timeout or return some value(true or false)
It is an interface, the implementations are: ArrayBlockingQueue, LinkedBlockingQueue, DelayQueue etc.


# CountDownLatch
This facility is used to wait until other threads finish, two common scenarios:
	In main thread to wait until all child threads finished.
	Create a pair latch as startGate and endGate.


# Executor Framework
Executor framework is better in 2 ways:
	It supports callable interface thus could get return value and throw exception.
	It separates task definition and task executing, thus code are easier to be modularized.