/**
 * This class represents most of the relevant course information for scheduling 
 * purposes. (Prerequisites, in particular, are not included for simplicity.) 
 * This class should allow programmers to determine whether a Course object is 
 * full (meaning that its enrollment is at capacity), if it meets on a 
 * particular day, and if it conflicts with another course (meaning that its 
 * day and time conflict with the other course's day and time).
 */

public class Course {
    /**
     * This enumeration represents the days of the week that can be 
     * used to determine whether the course meets on a particular day.
     * Legitimate values are SUN, MON, TUE, WED, THU, FRI, SAT.
     */
    public enum Day {SUN, MON, TUE, WED, THU, FRI, SAT};
    
    /**
     * A two- or three-letter designation for a department. For example, 
     * "CS" represents "computer science."
     */
    protected String department;
    
    /**
     * An alphanumeric string representing the course number. For example, 
     * "232" or "110M."
     */
    protected String number;

    /**
     * A positive integer representing the section number.
     */
    protected int section;

    /**
     * A string representing the days of the week on which the course meets, 
     * where each day is represented by a single character. The days of the 
     * week from Sunday through Saturday would be represented as "NMTWRFS". 
     * These designations should be capital letters.
     */
    protected String days;

    /**
     * The range of time for the class (e.g., 10am to 11am).
     */
    protected TimeRange time;

    /**
     * The abbreviation for the building (e.g., "AH" for "Ayers Hall").
     */
    protected String building;

    /**
     * The alphanumeric room number (e.g., "214" or "105C").
     */
    protected String room;

    /**
     * The last name of the faculty member teaching the course (e.g., "Garrett").
     */
    protected String faculty;

    /**
     * The maximum capacity for the course.
     */
    protected int capacity;

    /**
     * The current number of students enrolled in the course.
     */
    protected int enrolled;

    /**
     * This constructor requires the department, course number, and section.
     */
    public Course(String department, String number, int section) {
        this.department = department;
        this.number = number;
        this.section = section;
        days = "";//NMTWRFS
        building = "";
        room = "";
        faculty = "";
        capacity = 0;
        enrolled = 0;
        Time T1 = new Time("0000");
        Time T2 = new Time("0000");
        time = new TimeRange(T1,T2);
    }

    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public int getSection() {
        return section;
    }
    
    /**
     * The section must be greater than 0.
     */
    public void setSection(int section) {
        if(section > 0){
            this.section = section;
        } else {
            this.section = 0;
        }
    }
    
    public String getDays() {
        return days;
    }

    /**
     * The days must be converted to uppercase.
     */
    public void setDays(String days) {
        this.days = days.toUpperCase();
    }

    public TimeRange getTime() {
        return time;
    }
    
    public void setTime(TimeRange time) {
        this.time = time;
    }
    
    public String getBuilding() {
        return building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    public String getRoom() {
        return room;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }
    
    public String getFaculty() {
        return faculty;
    }
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * The capacity must be greater than or equal to 0.
     */
    public void setCapacity(int capacity) {
        if(capacity >= 0) {
            this.capacity = capacity;
        } else {
            this.capacity = 0;
        }
    }
    
    public int getEnrolled() {
        return this.enrolled;
    }
    
    /**
     * The enrolled must be greater than or equal to 0.
     */
    public void setEnrolled(int enrolled) {
        if(enrolled >= 0) {
            this.enrolled = enrolled;
        } else {
            this.enrolled = 0;
        }
    }

    /**
     * This method returns true if the enrollment is greater than or equal 
     * to the capacity.
     * 
     * @return whether the course is full
     */
    public boolean isFull() {
        return (enrolled>=capacity);
    }

    /**
     * This method returns true if the course meets on the given day.
     * 
     * @param day the day of the week
     * @return whether the course meets on the given day
     */
    public boolean meetsOn(Day day) {
        String allDays = this.days;
        char charDay = 'E';

        if(day.equals(Day.SUN)){
            charDay = 'N';
        } else if(day.equals(Day.MON)) {
            charDay = 'M';
        } else if(day.equals(Day.TUE)) {
            charDay = 'T';
        } else if(day.equals(Day.WED)) {
            charDay = 'W';
        } else if(day.equals(Day.THU)) {
            charDay = 'R';
        } else if(day.equals(Day.FRI)) {
            charDay = 'F';
        } else if(day.equals(Day.SAT)) {
            charDay = 'S';
        } else {
            charDay ='E';
        }
        
        for (int i = 0; i < allDays.length(); i++) {
            char currentChar = allDays.charAt(i);
            if (currentChar == charDay){
                return true;
            }
        }
        return false;
}
// Outputs "Thursday" (day 4)
    /**
     * This method returns true if the current course conflicts with the 
     * course that is passed in. A course conflicts with another if both 
     * meet on the same day and the time ranges overlap.
     * 
     * @param course the course in question
     * @return whether the current course conflicts with the course parameter
     */
    public boolean conflictsWith(Course course) {
        char[] currentDay = this.days.toCharArray();
        TimeRange currentTime = this.time;
        char[] givenDay = course.days.toCharArray();
        TimeRange givenTime = course.time;
        
        boolean conflict = false;
        
        for(int j = 0; j < currentDay.length; j++) {
            for(int i = 0; i < givenDay.length; i++) {
                if(givenDay[i] == currentDay[j]){
                    if(currentTime.overlaps(givenTime)) {
                        conflict = true;
                    }
                }
            }
        }
        return conflict;
    }
    
    /**
     * This method returns the string representation of the course. For example, 
     * a string representation of a course would look like the following:
     * 
     * CS 232 1 MWF 1230 1400 AH 363 Garrett 15 14
     * 
     * @return the string representation of the course
     */
    public String toString() {
        
        String DeNSDa = this.department + " " + this.number + " " + Integer.toString(this.section) + " " + this.days + " ";
        
        // String rangeTime =  Integer.toString(this.time.startTime.getHour()) + "" + Integer.toString(this.time.startTime.getMinute()) + " " + Integer.toString(this.time.endTime.getHour()) + "" + Integer.toString(this.time.endTime.getMinute()) + " ";
        
        String rangeTime = this.time.startTime + " " + this.time.endTime + " ";
        
        
        String BFCE = this.building + " " + this.room + " " +this.faculty + " " + Integer.toString(this.capacity) + " " + Integer.toString(this.enrolled);
        
        
        return (DeNSDa + rangeTime + BFCE);
    }
        
    public static void main (String[] args) {
        // course 1
        Course c = new Course("CS", "232", 1);
        c.setDays("NWF");
        Time t1 = new Time(12,30);
        Time t2 = new Time(14,00);
        TimeRange t = new TimeRange(t1, t2);
        c.setTime(t);
        c.setBuilding("Ah");
        c.setRoom("363");
        c.setFaculty("Garrett");
        c.setCapacity(39);
        c.setEnrolled(30);
        System.out.println(c.toString());
        
        Course c2 = new Course("CS", "232", 1);
        c2.setDays("WTF");
        Time t3 = new Time(12,30);
        Time t4 = new Time(14,00);
        TimeRange tr2 = new TimeRange(t3, t4);
        c2.setTime(tr2);
        c2.setBuilding("IN");
        c2.setRoom("203");
        c2.setFaculty("Ordeal");
        c2.setCapacity(20);
        c2.setEnrolled(20);
        System.out.println(c2.toString());
        
        Course c3 = new Course("ANTHRO", "232", 1);
        System.out.println(c3.toString());
        
        c3.setDays("MF");
        Time t5 = new Time(9,30);
        Time t6 = new Time(11,00);
        TimeRange tr3 = new TimeRange(t5, t6);
        c3.setTime(tr3);
        c3.setBuilding("OL");
        c3.setRoom("200");
        c3.setFaculty("Coleman");
        c3.setCapacity(20);
        c3.setEnrolled(20);
        System.out.println(c3.toString());
        
        
        System.out.println(c2.meetsOn(Day.SUN));
        System.out.println(c2.meetsOn(Day.WED));
        System.out.println(c2.conflictsWith(c));
        System.out.println(c2.conflictsWith(c3));
        
        
        
        
       // System.out.println(c.conflictsWith(c2));
        
    }
}