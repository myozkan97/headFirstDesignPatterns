import java.util.*;

/*It's simply an interface intended to perform chores for you. 

Let's say you're a god and you wanted to create the universe. 
That's a whole lot of planets(objects) to create and a lot of
task to perform(functions to call). Since you're the mightiest
beign around, no doubt you won't bother yourself with all the
details, Facade Pattern will do everything for you.

*/


/* We have all neccessary classes to create a universe down below*/


class Universe{
    List<Galaxy> galaxies;


    public Universe(){
        galaxies = new ArrayList<>();
    }

    public void addGalaxy(Galaxy galaxy){
        galaxies.add(galaxy);
    }

    public void removeGalaxy(Galaxy galaxy){
        galaxies.add(galaxy);
    }

    public void startExpanding(){
        System.out.println("Universe is expanding...");
    }

     
}


class Galaxy{
    String name;
    List<Star> stars;
    List<Planet> planets;

    public Galaxy(String name){
        stars = new ArrayList<>();
        planets = new ArrayList<>();
    }

    public void addStar(Star star){
        stars.add(star);
    }

    public void removeGalaxy(Star star){
        stars.add(star);
    }
    
    public void addPlanet(Planet planet){
        planets.add(planet);
    }

    public void removePlanet(Planet planet){
        planets.add(planet);
    }

    public void rotate(){
        System.out.println(name + " Galaxy is rotating...");
    }


}

class Star{
    String name;

    Star(String name){
        this.name=name;
    }

    public void shine(){
        System.out.println(name + " Star is shining...");
    }

}

class Planet{
    String name;
    

    Planet(String name){
        this.name=name;
    }

    public void spin(){
        System.out.println("Planet " + name + "is Spinnin.g");
    }

}




/*Since we've all the neccessary classes at our disposal, let's
begin to create a universe. */

public class FacadePattern{
    public static void main(String[] args) {
        //Creating the whole universe probably looked like this;
        Universe universe = new Universe();
        Galaxy milkywayGalaxy = new Galaxy("Milkyway");
        Star sun = new Star("SUN");
        Planet earth = new Planet("Earth");

        earth.spin();
        sun.shine();
        milkywayGalaxy.rotate();
        universe.startExpanding();
        
        
        universe.addGalaxy(milkywayGalaxy);
        milkywayGalaxy.addStar(sun);
        milkywayGalaxy.addPlanet(earth);
        //etc etc...

        //So you would concur this is a one lenghty job.
        //But we can use our facade pattern defined below.
        CreationFacade cFacade = new CreationFacade(new Universe(), 
            new Galaxy("Milkyway"), new Star("Sun"), new Planet("Earth"));
        
        cFacade.createUniverse();
        //As you see, this far more easier. 
        
    }
}


/*Most of the time facade pattern looks just like this.*/
class CreationFacade{
    Universe universe;
    Galaxy galaxy;
    Star star;
    Planet planet;

    //Constructor with objects that we'll work on
    public CreationFacade(Universe universe,
            Galaxy galaxy, Star star, Planet planet){
        this.universe=universe;
        this.galaxy=galaxy;
        this.star=star;
        this.planet=planet;
    }

    //We could have multiple methods just like this.
    //For example there could've been a method named
    // startDoomsday(). 
    public void createUniverse(){
        planet.spin();
        star.shine();
        galaxy.rotate();
        universe.startExpanding();

        galaxy.addPlanet(planet);
        galaxy.addStar(star);
        universe.addGalaxy(galaxy);
    }

    


    //Facedes are just fancy scripts, change my mind. 

}





