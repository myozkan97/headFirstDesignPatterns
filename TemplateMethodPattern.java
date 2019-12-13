/*
 *Temple method is all about a abstract class standardizing
 what to do and which order, but leaving how to do to 
 sub-concrete classes for their own case. 

 *I will use exact example from the book because i couldn't
 came up with a better example.

 */

abstract class PrepareHotBeverage {

   /**
    * this final method right here is the "Template method". Which sets the order
    * of the operations and makes sure it won't be altered by the "final keyword".
    */
   final void prepareBeverage() {
      boilWater();
      brew();
      pour();
      if (userWantCondiments())
         addCondiments();

   }

   /**
    * Because what to brew and what condiments we want to add can vary drink to
    drink, we better leave this implementations to sub-concrete classes.
    */
   abstract void brew();
   abstract void addCondiments();

   /**We implemented boilwater() and pour() because these steps won't vary 
    among different drinks. We always boil the water and pour it no matter 
    what we drink. So we better benefit from code reuse. 
    */
   final void boilWater() {
      System.out.println("Boiling water...");
   }

   final void pour() {
      System.out.println("Pouring water...");
   }


   /**The is called hook, it has default implementation but you're free
    * to change them. 
      */
   boolean userWantCondiments() {
      return false;
   }

}

/**Since defining our abstract interface done, let's check out how a concrete
 * subclass of a PrepareHotBeverage looks like.
 */

 //I can't afford to think that nobody will understand this Star Trek: TNG reference.
 //So i'm letting you know.
 class TeaEarlyGreyHot extends PrepareHotBeverage{
   
   /**We have to implement the abstract classes brew() and addCondiments(). */
   @Override
   void brew() {
      System.out.println("Putting tea bag in there.");
   }

   @Override
   void addCondiments() {
      System.out.println("Adding some sugars and lemon.");
   }

   /**We DON'T have to implement the hook method userWantCondiments() if we
   wan't to make do with the default implementation. But we'll do it here
   and make sure nothing spoils Picard's tea. 
    */
   @Override
   boolean userWantCondiments() {
      return false;
   }


 }

 /**Let's try try it out */
public class TemplateMethodPattern{
   public static void main(String[] args) {
      PrepareHotBeverage earlyGrey = new TeaEarlyGreyHot();
      earlyGrey.prepareBeverage();

      /**The output is:
       Boiling water...
      Putting tea bag in there.
      Pouring water...
       */
   }
}