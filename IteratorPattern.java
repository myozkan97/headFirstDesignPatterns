import java.util.*;

/**
 * Iterator pattern helps us iterato through objects without knowing or dealing
 * with their inner implementations.
 * 
 * This helps us traversing aggregate objects polymorficly. This way, you don't
 * have to deal with traversing elements trouble. ** Every class should have
 * just one objective. **
 */

/**
 * Let's assume we have a class that takes advantage of aggregation.
 */


/**We have a student class. Nothing of significance here. */
class Student{
    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
   
    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}

/**This class composed of Student array. */
class Classroom{
    //Our Student array.
    private Student[] students;
    private int position;
    
    
    public Classroom(int number){
        students= new Student[number];
        position=0;
    }

    //Method to add student. 
    public void addStudent(String name, String number){
        if(students.length!=position){
            if(students[position]==null)
                students[position]=new Student();
            students[position].setName(name);
            students[position].setNumber(number);
            position++;

        } else {
            System.err.println("Max number reached");
        }
    }

    //This will return an iterator. 
    public Iterator<Student> createIterator(){
        return new ClassroomIterator(students);
    }


}

/**As you might've noticed; we have a createIterator method in our
 * aggregate object Classroom. Which returns the ClassroomIterator 
 * object defined below.
 * 
 * What happens is; because we don't want to leave the responsibility 
 * of traversing our aggregate object to itself(which would be complicated),
 * we have a Iterator class which implements Iterator interface. This 
 * iterator with constructor parameter of Student array, handles the 
 * traversing stuff with the hasNext and next method. This way, we are 
 * free to use this Iterator class polymorfically anywhere we want. 
 * 
 * Also, the inner aggregation type of the Classrom object is not exposed.
 * That would cost us a lot of code whenever we wanted to traverse the 
 * Classroom object. 
 */


class ClassroomIterator implements Iterator<Student>{
    private Student[] students;
    private int count;

    public ClassroomIterator(Student[] s){
        students=s;
        count=0;
    }
    
    @Override
    public boolean hasNext() {
        if(count<=students.length && students[count]!=null){
            return true;
        } else return false;
    }

    @Override
    public Student next() {
        Student student = students[count];
        count++;
        return student;
    }
}


//Let's try out our implementation. 
public class IteratorPattern{
    public static void main(String[] args) {
        Classroom classroom = new Classroom(4);
        classroom.addStudent("Theodore", "0234");
        classroom.addStudent("Janet", "3423");
        classroom.addStudent("Michael", "2743");

        Iterator<Student> iterator = classroom.createIterator();
        while(iterator.hasNext()){
            Student st = iterator.next();
            System.out.println(st.getName() + " " + st.getNumber());
        }


        
    }
}

    


