/*
-----CHAPTER 6 COMMAND PATTERNS-----
*Command patterns aim to create a template for passing around commands. 
*If we're told to program a smart home solution, it wouldn't take much time of ours to
*realize we must standardize the communication between our solution and any smart home
*device.
*/

//This is one of our smart home devices. 
class SmartLamp {
    // It has a name and a boolean value. isOn bool indicates is lamp is whether on or not.
    boolean isOn;
    String name;

    // Constructor. For default, the lamp is on.
    SmartLamp(String name) {
        this.name = name;
        isOn = false;
    }

    // For turning it on.
    public void on() {
        isOn = true;
    }

    // For turning it off.
    public void off() {
        isOn = false;
    }

}

// This is another smart home device object
class SmartLock {
    boolean isLocked;
    String name;

    SmartLock(String name) {
        this.name = name;
    }

    // For locking
    public void lock() {
        isLocked = true;
    }

    // For unlocking
    public void unlock() {
        isLocked = false;
    }
}

/*
 * As you might have noticed, we have different smart home devices with
 * different function names for each. So if we wanted to create a remote
 * controller with programmeble buttons on it and we wanted to assign
 * different functions to that buttons; good luck. As there are many smart home
 * devices with great functional diversity, there'll be many more in the future.
 * So every time you wanted to assign a function to any button, you need to call
 * the functions directly from the subject class. So as always we
 * "encapsulate what varies" with command objects that implement our example
 * Command interface.
 
 
 * Let's define our command interface. This interface contains only one method;
 * execute. Every time we press a button on the remote, we will call the
 * concrete impelementation of the execute().
 */
interface Command {
    void execute();
}

/* This being done, time to implement our cocrete command classes. */

// A command object to lock the door
class LockCommand implements Command {
    SmartLock lock;

    // Constructor to create a LockCommand with any SmartLock object.
    public LockCommand(SmartLock lock) {
        this.lock = lock;
    }

    // This execute method locks the door.
    @Override
    public void execute() {
        lock.lock();

    }

}

// A command object to unlock the door
class UnlockCommand implements Command {
    SmartLock lock;

    // Constructor to create a UnlockCommand with any SmartLock object.
    public UnlockCommand(SmartLock lock) {
        this.lock = lock;
    }

    // This execute method unlocks the door.
    @Override
    public void execute() {
        lock.unlock();
    }

}

// A command object to turn on the light
class LightOnCommand implements Command {
    SmartLamp smartLamp;

    // Constructor to create a LightOnCommand with any SmartLamp object
    public LightOnCommand(SmartLamp smartLamp) {
        this.smartLamp = smartLamp;
    }

    // Again, implementation of execute method of Command interface
    @Override
    public void execute() {
        smartLamp.on();
    }
}

/*
 * Since we have different command objects that implement the same interface,
 * all we need to know is the name of the Command object that we want to use.
 * That saves us whole lot of time since we're saved from drudgery of dealing
 * every smart home objects inner functions and behaviours. SO, let's create a
 * simple remote control to make use of our Command objects...
 */

// A simple remote class with three button
class RemoteControl {
    Command button1;
    Command button2;

    // Constructor to create command with two command
    public RemoteControl(Command button1, Command button2) {
        this.button1 = button1;
        this.button2 = button2;
    }

    /*
     * As we have discussed, all we have to do is decide which Command objects that
     * we want to use. Since all Command objects implement the same interface with
     * execute() method, we don't have to deal with the details of objects that
     * we're operating on.
     */
    public void button1Pressed() {
        button1.execute();
    }

    public void button2Pressed() {
        button2.execute();
    }

}

/* Finally time to create a RemoteControl and demonstrate how everything works*/
public class CommandPatterns{
    public static void main(String[] args) {
        
        //Smart home objects
        SmartLamp smartLamp = new SmartLamp("Living Room Light");
        SmartLock smartLock = new SmartLock("Outer Door Lock");

        //Command objects with smart home objects
        Command lightOnCommand = new LightOnCommand(smartLamp);
        Command lockCommand = new LockCommand(smartLock);

        //One remote to rule them all!
        RemoteControl remoteControl = new RemoteControl(lightOnCommand, lockCommand);

        //Also we could use lambda expressions like this:
        RemoteControl remoteControl2 = new RemoteControl(
            () -> smartLamp.on( ), () -> smartLock.lock());

        //Method references are another option
        RemoteControl remoteControl3 = new RemoteControl(
            smartLock::lock, smartLamp::on);


    }
}
