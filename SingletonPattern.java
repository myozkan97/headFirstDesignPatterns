
/*
-----CHAPTER 5 - SINGLETON PATTERN-----
If we were asked to explain Singleton Pattern with a simple sentence, 
we could say that; Singleton pattern used to make sure a single class is 
created just once. 

This might have many use cases. If we had a PrinterObject that we created
to print some object, we wouldn't want  more than one PrinterObject 
instances floating around. So, can we design such a class pattern that does
not let us to create more than one instance of itself? 

Yes we can, with Singleton Pattern. 
*/

/*1- A simple Singleton Pattern class */
class Singleton{
    /*This guy right here will be our only single instance of this class.*/
    private static Singleton singletonObject;

    /*Since our constructor is private, who are we to instatiate this class
    outside. */
    private Singleton() {}

    /*This is the static method we call to actually get an instance. Since the only instance 
    object and constructor is private, you have no choice but calling this method. */
    public static Singleton getInstance(){
        if(singletonObject==null){
            singletonObject = new Singleton();
        } 
        return singletonObject;
    }
}
/*End Of 1- A simple Singleton Pattern class */

/*
Okay, this was cool and all. But, what if two different thread called the getInstance()
at the same time? Wouldn't they get two different instance of the Singletonobject?
Yes, they definitely would and we have a way around that; syncronization. Check this out:
*/

class SynchronizedSingleton{
    private static SynchronizedSingleton singletonObject;

    private SynchronizedSingleton() {}

    //The getInstance method is now synchronized. 
    public static synchronized SynchronizedSingleton getInstance(){
        if(singletonObject==null){
            singletonObject = new SynchronizedSingleton();
        } 
        return singletonObject;
    }
}

 
/*Doesn't synchronized code blocks cause severe performance losses? Yeah, they do. But
if the performance isn't the importance here, you are good to with this pattern. Otherwise,
you have 2 option;
A) Eagerly created instance,
B) Double-checked locking.
*/


//A)With Eagerly created Instance
class SingletonWithEagerInst{
    
    /*Now we are creating an instance whether it's called or not, you might
    use this approach if instance won't take its toll on resources.*/
    private static SingletonWithEagerInst singletonObject = new SingletonWithEagerInst();

    private SingletonWithEagerInst() {}

    
    public static synchronized SingletonWithEagerInst getInstance(){
        return singletonObject;
    }
}

//B)With Double-Check Lock 
class SingletonWithDoubleCheck{
    
    /*Be careful; our singleton object is volatile here. Because we dont want it to be 
    independently modified by different threads. */
    private volatile static SingletonWithDoubleCheck singletonObject;

    private SingletonWithDoubleCheck() {}

    /*With this version of getInstance(), overall performance is greatly improved if 
    we tend to call getInstance() a lot. */
    public static SingletonWithDoubleCheck getInstance(){
        if(singletonObject==null){
            synchronized(SingletonWithDoubleCheck.class){
                if(singletonObject==null)
                    singletonObject = new SingletonWithDoubleCheck();
            }
        }
        return singletonObject;
    }
}










