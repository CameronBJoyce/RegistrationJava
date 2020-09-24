/**
 * This class represents a set of course offerings. It should allow the 
 * programmer to construct an empty object with a capacity of 100 courses 
 * or construct an object from an input file. It should also allow courses 
 * to be accessed, changed, and added.
 */
import java.io.File;
import java.io.*;
import java.util.Scanner;

 
public class CourseOfferings {
    protected Course[] courses;  // an array of courses with at most 100 elements
    protected int numCourses;    // the actual number of courses in the courses array

    /**
     * This is a constructor that creates an array of 100 courses and 
     * initializes the number of courses to 0.
     */
    public CourseOfferings() {
        courses = new Course[100];
        numCourses = 0;
    }

    /**
     * This is a constructor that creates an array of 100 courses and fills it 
     * with the courses listed in the input file whose file name is passed in. 
     * The format (and sample) of the input file is as follows:
     * 
     * CS 230 1 MWF 1000 1130 AH 257 DaParma 20 18
     * CS 230 2 MWF 0815 0945 AH 257 Thornton 20 18
     * CS 230 3 TWR 1245 1415 AH 363 Ford 20 12
     * CS 230 4 MWF 1300 1430 AH 361 DaParma 20 18
     * CS 230 5 TWR 1500 1629 AH 363 Garrett 20 14
     * CS 231 1 TWR 0915 1045 AH 363 Ford 20 11
     * CS 231 2 TWR 1245 1415 AH 257 Reaves 20 20
     * CS 232 1 MWF 1230 1400 AH 363 Garrett 15 14
     * CS 232 2 MWF 1630 1800 AH 363 Garrett 15 5
     */
    public CourseOfferings(String filename) {
          courses = new Course[100];
          Course c;
          int line = 0;
          try {
              File file = new File(filename);
              Scanner reader = new Scanner(file);
              
              while (reader.hasNextLine()) {
                
                
                //Department, Number, Section
                String department = reader.next();
                String number = reader.next();
                int section = reader.nextInt();
                c = new Course(department, number, section);
             
                
                //Days 
                String days = reader.next();
                c.setDays(days);
                
              
                
                // Time Range
                String sT = reader.next();
                String eT = reader.next();
                Time tST = new Time(sT);
                Time tET = new Time(eT);
                TimeRange tr = new TimeRange(tST,tET);
                c.setTime(tr);
                
               // System.out.println("FOUR");
               
                //Building 
                String b = reader.next();
                c.setBuilding(b);
                
                //Room 
                String r = reader.next();
                c.setRoom(r);
                
                //Faculty
                String f = reader.next();
                c.setFaculty(f);
                
                //capacity
                int cap = reader.nextInt();
                c.setCapacity(cap);
                
                //Enrolled
                int e = reader.nextInt();
                c.setEnrolled(e);
                
                courses[line] = c;
                line++;
              }
             
             this.numCourses = line;
              reader.close();
            } catch (Exception e) {
                
            }
        
    }

    /**
     * This method returns the number of courses currently held in the array.
     * 
     * @return the number of courses
     */
    public int getNumCourses() {
        return this.numCourses;
    }

    /**
     * This method returns the course at index i. If i is not within the 
     * legal bounds of the array (less than 0 or greater than or equal to 
     * the current number of courses), an ArrayIndexOutOfBoundsException 
     * should be thrown.
     * 
     * @param i the index of the course to retrieve
     * @return the course at index i
     */
    public Course getCourse(int i) {
        if(i >= numCourses || i < 0) {
            throw new ArrayIndexOutOfBoundsException("error1");
        } else {
            return courses[i];
        }
    }

    /**
     * This method sets the course at index i to c. If i is not within the 
     * legal bounds of the array (less than 0 or greater than or equal to 
     * the current number of courses), an ArrayIndexOutOfBoundsException 
     * should be thrown.
     * 
     * @param i the index of the course
     * @param c the course that should replace the one at index i
     */
    public void setCourse(int i, Course c) {
        if(i >= numCourses || i < 0) {
            throw new ArrayIndexOutOfBoundsException("error2");
        } else {
            this.courses[i] =  c;
           //x numCourses++;
        }
        
    }

    /**
     * This method adds the course c to the end of the array if there is room. 
     * It returns true if c could be added and false otherwise.
     * 
     * @param c the course to add
     * @return whether the course could be added
     */
    public boolean addCourse(Course c) {
        if(numCourses > 99) {
            return false;
        } else {
            this.courses[numCourses] = c;
            this.numCourses++;
            return true;
        }
    }

    /**
     * This method returns the string representation of the course offerings, 
     * which should look identical in format to the input file.
     * 
     * @return the string representation of the course offerings
     */
    public String toString() {
        String result = " ";
        for(int i = 0; i < this.numCourses; i++) {
            result += this.courses[i].toString() + "\n";
        }
        return result.trim();
    }
    public static void main(String[] args){
        CourseOfferings cO = new CourseOfferings("input.txt");
        System.out.println(cO.getNumCourses());
        System.out.println(cO.toString());
        System.out.println("Test1");
        Course course = new Course("c","b",12);
        Course course2 = new Course("A", "C", 3);
        Course course3 = new Course("V", "A", 4);
        //cO.getCourse();
        //
        cO.setCourse(12,course);
        cO.setCourse(2,course2);
       // cO.addCourse(course3);
        // System.out.println("Test2");
        // System.out.println(cO.getNumCourses());
        // System.out.println("Test2.5");
        // System.out.println(cO.toString());
        // System.out.println("Test3");
        // System.out.println(cO.getCourse(12));

        
    

}
}






