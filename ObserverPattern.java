import java.util.LinkedList;

/*
Observer Pattern - as the name suggests - is a pattern to
inform subscribed observers of any change in the subject 
class. 

As a simple example, if you have a mail server, you'll 
probably want to inform the mail user in case of a new
mail. So the mail client on the other end, is a subscriber
to your mail server, and you're responsible to notify them.

*/


/*So this is the mail server*/
interface MailServer{
    public void subscribe(ObserverI observer);
    public void unsubscribe(ObserverI observer);
    public void notify(String s);
}

class MyMailService implements MailServer{
    List<ObserverI> observers;

    public MyMailService(){
        observers = new LinkedList<>();
    }
    
    @Override
    public void subscribe(ObserverI observer) {
        observers.add(observer);
    }
    @Override
    public void unsubscribe(ObserverI observer) {
        observers.add(observer);
    }
    @Override
    public void notifyObservers(String s) {
        for(ObserverI i: observers){
            i.notify();
        }
    }
}


interface Observer{
    public void notifyObservers();

}


class MailClient implements ObserverI{
    @Override
    public  void notifyObservers() {
        super.notify();
    }
}

