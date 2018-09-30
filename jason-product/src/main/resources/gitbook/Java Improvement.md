# This is used to record my idea of the design flaws of java and how to improve it.

## Object.equals(Object o)
### This method should be used as designed using an generic way, thus enhance type safety check at compile time instead of runtime when overriding it.

    For example:
    
    public interface Equalable<T> {
    	boolean equals(T t){}
    }
	
	public class Object implements Equalalbe{
	 ...
	 @Override
	 public boolean equals(Object o) {
	 	...
	 }
	}
    
    Thus String should be override like this:
    
    
## Get rid of all dummy interface, give them proper methods, for example: 

### Clonable has a clone method, 
### Serialiable has a serialize method.

