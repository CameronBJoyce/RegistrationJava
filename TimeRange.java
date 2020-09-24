/**
 * This class represents a duration or range of time marked by a starting and 
 * ending time. This class does not allow for such a range of time to extend to 
 * the following day. In other words, the start time must always come before 
 * the end time without considering the days. So defining a range like 
 * 1900 to 0200 (7pm to 2am) would instead be interpreted as 0200 to 1900 
 * (2am to 7pm).
 */
 
public class TimeRange {
    protected Time startTime;  // startTime must always come before endTime
    protected Time endTime;
    
    /**
     * This is a constructor that takes a start and end time and initializes 
     * the object. If the start time does not come before the end time, the 
     * constructor switches them around to make the range meaningful.
     */
     
    // public void rangeSwap(Time x, Time y) {
    //     tempHour = x.getHour();
    //     tempMinute = x.minute;
    //     x.hour = y.hour;
    //     x.minute = y.minute;
    //     y.hour = tempHour;
    //     y.minute = tempMinute;
    // }
    
    public TimeRange(Time startTime, Time endTime) {
        if(startTime.getHour() > endTime.getHour()) {
            this.startTime = endTime;
            this.endTime = startTime;
        } else if(startTime.getHour() == endTime.getHour() && startTime.getMinute() > endTime.getMinute()) {
            this.startTime = endTime;
            this.endTime = startTime;
        } else { 
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * This method returns the start time. Note that there is no equivalent 
     * setter method here. This is because a setter method might violate the 
     * "start comes before end" constraint, forcing the time range to rearrange 
     * itself to remain meaningful. Instead, any changes must be done by 
     * instantiating a new TimeRange object.
     * 
     * @return the start time of this time range
     */
    public Time getStartTime() {
        return this.startTime;
    }
    
    
    /**
     * This method returns the end time. Note that there is no equivalent 
     * setter method here. This is because a setter method might violate the 
     * "start comes before end" constraint, forcing the time range to rearrange 
     * itself to remain meaningful. Instead, any changes must be done by 
     * instantiating a new TimeRange object.
     * 
     * @return the end time of this time range
     */
    public Time getEndTime() {
        return this.endTime;
    }

    /**
     * This method returns the duration, in minutes, of the range. If the range
     * were (1130, 1545), then the duration would be 255 (minutes).
     * 
     * @return the duration of the range in minutes
     */
    public int getDuration() {
        return (endTime.getHour() - startTime.getHour())*60 + (endTime.getMinute() - startTime.getMinute());
    }

    /**
     * This method returns true if the current TimeRange object contains the 
     * time passed in. For instance, the time range (1000, 1400) contains 1030 
     * (and 1000 and 1400 and all times in between) but does not contain 0800 
     * or 1500 (or 1401, for that matter).
     * 
     * @param time the time under consideration
     * @return whether this range contains the given time
     */
    public boolean contains(Time time) {
       return ((endTime.after(time) && startTime.before(time)) || endTime.equals(time) || startTime.equals(time));
    }

    /**
     * This method returns true if the current TimeRange object overlaps the 
     * TimeRange object passed in. For instance, the time range (1000, 1400) 
     * overlaps the ranges (1100, 1500), (1030, 1130), and (0900, 1430), but 
     * it does not overlap the range (0800, 0900).
     * 
     * @param otherRange the range under consideration
     * @return whether this range overlaps the given range
     */
    public boolean overlaps(TimeRange otherRange) {
        return (otherRange.contains(startTime) || otherRange.contains(endTime) || this.contains(otherRange.startTime) || this.contains(otherRange.endTime));
    }

    /**
     * This method returns the string representation of the time range of the 
     * form "HHMM-HHMM" where the first time is the start and the second is 
     * the end.
     * 
     * @return the string representation of this range
     */
    public String toString() {
        return this.startTime.toString() + "-" + this.endTime.toString();
        
        // Integer.toString(this.startTime.getHour()) + "" + Integer.toString(this.startTime.getMinute()) + "-" + Integer.toString(this.endTime.getHour()) + "" + Integer.toString(this.endTime.getMinute());
    }
    
    public static void main (String[] args) {
        Time t1 = new Time(7,30);
        Time t2 = new Time(14,40);
        Time t3 = new Time(14,50);
        Time t4 = new Time(15,40);
       
        
        TimeRange tr1 = new TimeRange(t1, t2);
        TimeRange  tr2 = new TimeRange(t3,t4);
        System.out.println("T1 START =" + tr1.startTime);
        System.out.println("T1 END =" + tr1.endTime);
        System.out.println("T2 Start =" + tr2.startTime);
        System.out.println("OVERLAP? = " + tr1.overlaps(tr2));
      
        
        System.out.println(tr1.toString());
        System.out.println(tr2.getDuration());
    }
}
