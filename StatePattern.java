/*
"The State Pattern allows an object to alter its behavior when its 
internal state changes. The object will appear to change its class."

In summary, State Pattern defines states of an object as representative 
classes and delegates these state classes to the object. Delegated classes
determines which action to take or which state to delegate. 

Instead of lengthy if statements, with State Pattern; we are more able 
to make changes to the system in the future more easily. 

*/

/*
Let's start off by implementing a State Pattern for a cold drink 
vending machine. 

As the example covered in book; this machine will have four states;
    *Sold State
    *No Money State
    *Has Money State
and there will be 4 actions that must be covered for every one of 
these states;
    *insertMoney()
    *ejectMoney()
    *selectBeverage()
    *vend()

Here we go...

*/

//That's how a State interface looks like.
interface State {
    public void insertMoney();
    public void ejectMoney();
    public void selectBeverage();
    public void vend();
}

// And this is how a concrete State class looks like
class SoldState implements State {
    VendingMachine vendingMachine;

    public SoldState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Please wait while vending.");
    }

    @Override
    public void ejectMoney() {
        System.out.println("Too late buddy.");
    }

    @Override
    public void selectBeverage() {
        System.out.println("You've already selected a beverage.");
    }

    @Override
    public void vend() {
        System.out.println("Vending...");
        vendingMachine.setState(vendingMachine.getNoMoneyState());
    }
}

//Another concrete implementation for NoMoneyState...
class NoMoneyState implements State {
    VendingMachine vendingMachine;

    public NoMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Money inserted.");
        vendingMachine.setState(vendingMachine.getHasMoneyState());
    }

    @Override
    public void ejectMoney() {
        System.out.println("No money to eject.");
    }

    @Override
    public void selectBeverage() {
        System.out.println("No money.");
    }

    @Override
    public void vend() {
        System.out.println("No money. Also no selection.");
    }
}

//The last implementation.
class HasMoneyState implements State {
    VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertMoney() {
        System.out.println("You already inserted money...");
    }

    @Override
    public void ejectMoney() {
        System.out.println("Ejecting money...");
        vendingMachine.setState(vendingMachine.getNoMoneyState());
    }
    
    @Override
    public void selectBeverage() {
        System.out.println("Beverage selected.");
        vendingMachine.setState(vendingMachine.getSoldState());
    }

    @Override
    public void vend() {
        System.out.println("You have not selected a beverage.");
    }
}

/*VengingMachine class is the one we're going to use as an interface. 
When we call a method in this object, it'll call an the appropriate 
method in the current state object. That call might change the state.  */
class VendingMachine {
    //These are our states at our disposal.
    private State hasMoneyState;
    private State noMoneyState;
    private State soldState;

    //These variable composed by States declared above.
    //It represents current state of the vending machine. 
    State state;

    //Constructor
    public VendingMachine() {
        //assigning appropriate State objects.
        hasMoneyState = new HasMoneyState(this);
        noMoneyState = new NoMoneyState(this);
        soldState = new SoldState(this);
        
        //At start, of course our vending machine will start with no money.
        state = noMoneyState;
    }

    //This and 3 methods below sends our calls to current State object.
    public void insertMoney() {
        state.insertMoney();
    }
    public void ejectMoney() {
        state.ejectMoney();
    }
    public void selectBeverage() {
        state.selectBeverage();
    }
    public void vend() {
        state.vend();
    }
    public void vendBeverage() {
        System.out.println("Vending beverage");
    }


    //Helper method.
    public void setState(State state) {
        this.state = state;
    }

    //and getters...
    public State getSoldState() {
        return soldState;
    }
    public State getNoMoneyState() {
        return noMoneyState;
    }
    public State getHasMoneyState() {
        return hasMoneyState;
    }

}

//time to test.
public class StatePattern {
    public static void main(String[] args) {
        VendingMachine vM = new VendingMachine();

        vM.ejectMoney();
        vM.vend();
        vM.insertMoney();
        vM.selectBeverage();
        vM.vend();

    }
}


