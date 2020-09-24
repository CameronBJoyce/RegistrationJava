// There is nothing to complete in this class. I have implemented it for you fully.

public class Student {
    public enum Result { SUCCESS, OVERLOAD, DUPLICATE, CONFLICT };
    
    protected Course[] courses;
    protected int numCourses;
    
    public Student() {
        // Instantiate the array of courses and
        // set the number of courses to 0.
        courses = new Course[5];
        numCourses = 0;
    }
    
    public Result addCourse(Course c) {
        // There are three error scenarios in adding a course.
        // 1. The student's schedule is already at capacity (OVERLOAD).
        // 2. The course is already in the list (by department and number) (DUPLICATE).
        // 3. The course conflicts with another course in the schedule (CONFLICT).
        // If none of these errors exist, then add the course to the schedule
        // and return SUCCESS. Otherwise, return the apprpriate error result.
        if(numCourses == courses.length) {
            return Result.OVERLOAD;
        }
        else {
            boolean conflict = false;
            boolean duplicate = false;
            for(int i = 0; i < numCourses; i++) {
                if(courses[i].getDepartment().equals(c.getDepartment()) &&
                   courses[i].getNumber().equals(c.getNumber())) {
                    duplicate = true;
                }
                if(courses[i].conflictsWith(c)) {
                    conflict = true;
                }
            }
            if(duplicate) {
                return Result.DUPLICATE;
            }
            else if(conflict) {
                return Result.CONFLICT;
            }
            else {
                courses[numCourses] = c;
                numCourses++;
                return Result.SUCCESS;
            }
        }
    }
}