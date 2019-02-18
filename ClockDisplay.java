
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String meridian;   // the value that determines whether it's morning or night, adds AM or PM string to the end of displaystrings value depending on the internal hour
    private int Middleman;    //
    private int convertCheck; // the value of this integer has it's value set based on the the numerical value for the hours field, it's then used to convert 24 hour time to 12 hour
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
        
    }
    
    
    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
        /**
        
        if(hours.getValue() >= 13){
            hours.getValue() = (hours.getValue() - 12);
        }
        **/
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        setMiddleman();
        convertCheck();
        updateDisplay();
    }
    
    /**
     * Gets the current numerical value for the hours field
     */
    public int getHour(){
    return hours.getValue();
    }
    
    /**
     * assigns middleman a value based on the hour field of the current time. 
     * Then copies it's value to convertCheck for it to use, and finally ends by calling the set meridian method.
     */
    private void setMiddleman(){
        Middleman = hours.getValue();
        convertCheck = Middleman;
        setMeridian();
    }
    
    /**
     * Gets middlemans current value, not useful outside of debugging
     */
    public int getMiddleman(){
     return   Middleman;
    }
    
    /**
     * Takes middlemans value and decides whether the time is AM or PM based on it's value being above or below 12
     */
    private void setMeridian()
    {
        
        if(Middleman > 12)
        {
            meridian = " PM";
        }
        else{
            meridian = " AM";
        }
    }
    /** 
     * fetches the meridians current value, useful for debugging.
     */
    public String getMeridian()
    {
        return meridian;
    }
    
    public void convertCheck()
    {
        /**
         * corrects the 24 hour clock value, by subtracting 12 from any hour field above 12
         */
        if(convertCheck > 12)
        {
            convertCheck = convertCheck - 12;
        }
        /**
         * 
         */
        if(convertCheck == 0)
        {
            convertCheck = convertCheck + 1;
        }
    }
    
    /** 
     * Gets convert checks current value, not useful outside of debugging
     */
    public int getconvertCheck(){
    return convertCheck;
    }
    
    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        
        displayString = convertCheck + ":" + 
                        minutes.getDisplayValue() + meridian;
                
    }
}
