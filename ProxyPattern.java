import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
"The Proxy Pattern provides a surrogate or placeholder for another object to control
access to it."

Proxy pattern is way to go if you need to reach to an object that is
on a remote JVM. Calls from the client object actually are to the 
delegate object "Client Helper" which is retrieved as a clone from the 
actual remote object. 

*/

/*
First you need to define an interface which will indicate what methods 
can we call from the remote object. It's basically a template that 
informs us about the abilities of the remote object.  */
interface Service extends Remote {
    /*
     * Since these calls will be transmitted over network, it's prone to be affected
     * over network interruptions. So we declare their exceptions.
     */

    // Method names and return types are arbitrary. But take note that
    // return types must be primitive or Serializable.
    public int getNumber() throws RemoteException;

    public String getName() throws RemoteException;

    public double getValue() throws RemoteException;
}

/*
 * An actual client object. It must extend Unicast object and implement our
 * Service interface.
 */
class MyService extends UnicastRemoteObject implements Service {
    private static final long serialVersionUID = 1L;

    public MyService() throws RemoteException {

    }

    public String getName() throws RemoteException {
        return null;
    }

    public int getNumber() throws RemoteException {
        return 0;
    }

    public double getValue() throws RemoteException {
        return 0;
    }
}

/*
 * This is all for defining the client side but we're not finished. To register
 * our MyService object, we need to make the followings:
 */
public class ProxyPattern {
    public static void main(String[] args) {
        try {
            // Initiating a MyService object and registering it.
            Service service = new MyService();
            Naming.bind("RemoteService", service);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * In order to make this work, you need to run rmiregistry command in your
         * operating systems console. Then running this class will provide us a remote
         * service to starth with.
         */
    }
}

/*
 * Okay, let's say we're on a different computer on the network and we want make
 * use of this remote object.
 */
class ClientClass {
    public static void main(String[] args) {
        try {
            
            /*This will assign the "Stub" object to our "service" variable.
            Calls that you'll make to this service object will be packaged and 
            send over network network by this "Stub" object to "Skeleton" object
            on the other side of the line, which will which will open the package 
            and make the actual call to the MyService object and return the results
            same way. Easy huh?   */
            Service service = (Service)Naming.lookup("rmi://###IP_Address###/RemoteService");

            //this is an example call. 
            service.getName();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 









