

/*
"The Strategy Pattern defines a family of algorithms,
encapsulates each one, and makes them interchangeable. Strategy
lets the algorithm vary independently from clients that use it."

Instead of hardcoding every detail to the inheritance chain, 
Strategy Pattern defines varying behaviors as delegates. 
This provides us some lucrative advantages like changing behavior 
of some object at the runtime and many more.

*/


/*Let's say we have an abstract class called Duck, we want to
extend this class and create our concrete implementations. 

Because our ducks' fly and quack behavior varies class to class,
it'll be a real pain to rewrite different quack and fly behavior
code in every class. 

Here comes the Strategy Pattern to play. It delegates those behaviors
to Behaviour classes. So as we can delegate any behavior withoout 
hassle of defining new behaviours, we can also change the objects 
behavior at runtime. What an absolute unit this Strategy Pattern...

Here is the abstract Duck class.
*/
abstract class Duck{
    //This how we delegate those behaviors. 
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    //Getting the fly behavior from the delegated object.
    public void flyBehavior(){
        flyBehavior.flyBehavior();
    }

    //Getting the fly behavior from delegated class.
    public void quackBehavior(){
        quackBehavior.quackBehavior();
    }

    //With these two setters below, we can delegate 
    //any behavior class at we want at runtime. 
    public void setFlyBehavior(FlyBehavior fb){
        flyBehavior = fb;
    }
    public void setQuackBehavior(QuackBehavior qb){
        quackBehavior = qb;
    }

}

//Okay, here comes the one behavior interface. 
interface FlyBehavior{
    public void flyBehavior();
}

//And a concrete behavior implementation.
class FlyWithWings implements FlyBehavior{
    public void flyBehavior(){
        System.out.println("Flying Real Good");
    }
}

//Another one. 
class NoFly implements FlyBehavior{
    @Override
    public void flyBehavior() {
        System.out.println("You can't fly loser");
    }
}

//Quack behaviour interface. 
interface QuackBehavior{
    public void quackBehavior();
}

//Concrete behaviour implementation.
class AnglaisQuack implements QuackBehavior{
    @Override
    public void quackBehavior() {
        System.out.println("Quack quack mf");
    }
}

//Another one. 
class TurkoQuack implements QuackBehavior{
    @Override
    public void quackBehavior() {
        System.out.println("Vak vak");
    }
}


/*Careful here, we're implementing finally a concrete Duck. In
constructor, we're delegating our desired behaviors. Look how 
simple is the actual implementation of a duck. 

Think otherwise, we would have to implement a very varying 
behavior for this Duck implementation and every Duck we'll create.
This, much simpler. */
class TurkishDuck extends Duck{
    TurkishDuck(){
        setFlyBehavior(new FlyWithWings());
        setQuackBehavior(new TurkoQuack());
    }
}

//Let's check out if everything's allright. 
public class StrategyPattern{
    public static void main(String[] args) {
        Duck duck = new TurkishDuck();
        duck.quackBehavior();
        duck.flyBehavior();
    }
}


