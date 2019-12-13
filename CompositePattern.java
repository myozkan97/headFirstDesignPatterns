import java.util.*;

/*
"The Composite Pattern allows you to compose objects into tree structures 
to represent part-whole hierarchies. Composite lets clients treat 
individual objects and compositions of objects uniformly."

All of this done by defining a Component interface which implemented by
two types of concrete classes: Leaf and Composite. Leaf holds maximum
one object while Composite can be comprised of more than one object. 

Leaf and and Composite classes implements Component interface because
that model proved to be useful in case of traversing the Composite model
or operating on it. 
*/

/*Let's create a menu with Composite Pattern like authors of the book. */

//First of, Create the Component interface
abstract class MenuComponent{
    /*In abstract class, every method throws UnsupportedOperationException.
    In subclasses, you're free to re-implement any methods you intend to use. */
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        throw new UnsupportedOperationException();
    }

    public double getPrice(){
        throw new UnsupportedOperationException();
    }

    public void print(){
        throw new UnsupportedOperationException();
    }

    /*This method helps us define an implementation of Iterator to 
    traverse composite menu. */
    public Iterator<MenuComponent> createIterator(){
        throw new UnsupportedOperationException();
    }

}

//Now implement the Leaf Class
class MenuItem extends MenuComponent{
    String name;
    double price;

    public MenuItem(String name, double price){
        this.name=name;
        this.price=price;
    }

    

    @Override
    public String getName() {
        return name;
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print() {
        System.out.println("Menu Item: " + name + " $" + price);
    }


    /*Because we don't have anythint to traverse in a leaf class, we're
    defining an anonymus Iterator class that always false when the hasNext()
    method called. */
    @Override
    public Iterator<MenuComponent> createIterator() {
        return new Iterator<MenuComponent>(){
        
            @Override
            public MenuComponent next() {
                return null;
            }
        
            @Override
            public boolean hasNext() {
                return false;
            }
        };
    }
}



//Now we implement Composite class
class Menu extends MenuComponent{
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    Iterator<MenuComponent> iterator = null;
    String name;

    public Menu(String name){
        this.name=name;
    }

    
    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println("Menu: " + name + "\n-----------");
    }

    //Remember, iteration is your friend.
    @Override
    public Iterator<MenuComponent> createIterator() {
        if (iterator == null){
            iterator = new MenuComponentIterator(menuComponents.iterator());
        } 
    
        return iterator;
    }



}


/*Our Iterator implementation for our composite pattern. Feel free to 
explore this class yourself.  */
class MenuComponentIterator implements Iterator<MenuComponent>{
    Stack<Iterator<MenuComponent>> stack = new Stack<>();
    
    public MenuComponentIterator(Iterator<MenuComponent> iterator){
        stack.push(iterator);
    }
    
    @Override
    public boolean hasNext() {
        if(stack.isEmpty()) {
            return false;
        } else {
            Iterator<MenuComponent> iterator = stack.peek();
            if(!iterator.hasNext()){
                stack.pop();
                return hasNext();
            } else 
                return true;
        }
    }

    @Override
    public MenuComponent next() {
        if(hasNext()){
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent menuComponent = iterator.next();

            stack.push(menuComponent.createIterator());
            
            return menuComponent;
        } else {
            return null;
        }
    }
}



/*Let's try if all of this mess works.  */
public class CompositePattern{
    public static void main(String[] args) {
        Menu mainMenu = new Menu("Main Menu");
        MenuItem pizza = new MenuItem("Pizza", 13.99);
        MenuItem pasta = new MenuItem("Pasta", 7.99);
        MenuItem beefRoast = new MenuItem("Beef Roast", 19.99);
        mainMenu.add(pizza);
        mainMenu.add(pasta);
        mainMenu.add(beefRoast);

        Menu desertMenu = new Menu("Desert Menu");
        MenuItem supangle = new MenuItem("Supangle", 4.99);
        MenuItem puding = new MenuItem("Puding", 5.99);
        desertMenu.add(supangle);
        desertMenu.add(puding);

        mainMenu.add(desertMenu);

        MenuComponentIterator iterator = new MenuComponentIterator(mainMenu.createIterator());
        while(iterator.hasNext()){
            MenuComponent component = iterator.next();
            component.print();
        }


    }
}