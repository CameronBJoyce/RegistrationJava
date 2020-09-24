import java.util.Scanner;


public class Main {
    public enum Result { SUCCESS, INVALID, FULL, OVERLOAD, DUPLICATE, CONFLICT};
    
    public static Result selectCourse(Student student, 
                                      CourseOfferings offerings,
                                      int courseIndex) {
        // TODO
        // Try to add the specified course (by index) to the student's
        // schedule. If successful, increase its enrollment by 1. Return
        // the appropriate result in each case.
        // SUCCESS - no errors
        // INVALID - course index is invalid
        // FULL - course is full
        // OVERLOAD - student schedule is full
        // DUPLICATE - student schedule already contains the course
        // CONFLICT - course conflicts with another on student schedule
        Course c;
        try {
            c = offerings.getCourse(courseIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Result.INVALID;
        }

        if (c.isFull()) {
            return Result.FULL;
        }
        
        String result = student.addCourse(c).name();
        
        if (result.equals("SUCCESS")) {
            c.enrolled++;
        }
        
        return Result.valueOf(result);
        
      
    }

    public static void main(String[] args) {
        // TODO
        // For each index in the set [1, 3, 0, 6, 5, 7, 8, 9, 10, 11, 12]
        //    Display the offerings
        //    Choose the index
        //    Display the result
        //
        // The results should be 
        // FULL, SUCCESS, DUPLICATE, FULL, SUCCESS, CONFLICT, 
        // SUCCESS, SUCCESS, SUCCESS, OVERLOAD, OVERLOAD
        // 
        // The input file with the course offerings is located
        // at the path "input.txt".
        
        int[] set = {1, 3, 0, 6, 5, 7, 8, 9, 10, 11, 12};
        CourseOfferings cO = new CourseOfferings("input.txt");
        Student student = new Student();
        
        for (int i : set) {
            try {
                Course course = cO.getCourse(i);
                System.out.println(course.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Index " + i + " is invalid.");
            }

            System.out.println(selectCourse(student, cO, i));
        }
        
    }
}
