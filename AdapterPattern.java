

/*In essence adapter pattern simply makes an interface with
another interface. It's kind of translator, middle man that
saves the day in the time of need. 
*/


//Let's say we have a cat interface; 
interface Cat{
    void meow();
    void purr();
}

//and we have concrete classes that implement this interface;
class TurkishVanCat implements Cat{
    @Override
    public void meow() {
        System.out.println("Meow in Turkish");
    }

    @Override
    public void purr() {
        System.out.println("Purr in Turkish");
    }
}

//And may be there are interfaces that make use of cat classes
//like;
class CatTester {
    
    CatTester(Cat cat){
        cat.meow();
        cat.purr();
    }

}

/*
So let's say we need to use this CatTester class on a AsianTiger 
class. But surprise, AsianTiger class doesn't implement Cat
interface, it may have different method names and stuff. But
we are confident that cats and tigers are similar and therefore
there must be some way to use our CatTester class with AsianTiger
class. Let's take a look how can we implement such a mediator.
*/

//Tiger interface
interface Tiger{
    public void roar();
    public void purr();
}

//Our AsianTiger will look like this.
class AsianTiger implements Tiger{
    @Override
    public void roar(){
        System.out.println("Roar in Taiwanese");
    }
    @Override
    public void purr(){
        System.out.println("Loud purring");
    }
}

/*As you may have noticed, even though they aren't implementing
the same interface, they're quite similar. Let's create our
adapter... */

//Tiger adapter for CatTester class
class TigerAdapter implements Cat{
    Tiger tiger;

    //Starting adapter with a Tiger object
    public TigerAdapter(Tiger tiger){
        this.tiger=tiger;
    }

    //This is the whole trick, it calls roar method
    // of tiger when we call meow method of this so-called
    //"Cat"
    @Override
    public void meow() {
        tiger.roar();
    }

    @Override
    public void purr() {
        tiger.purr();
    }

}

/* The crucial part is, adapter must implement the target interface and 
must contain a instance of the subject interface. */

