
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
public class ClockDisplay12Hr
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private int Middleman;
    private String meridian;
    private int convertCheck;
    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at 00:00.
     */
    public ClockDisplay12Hr()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }
    /**
     * Takes middlemans value and divides it by two, if it comes out with a remainder ( != 0) then meridian is set to AM, if it divides cleanly, meridian is set to PM.
     */
    private void setMeridian()
    {
        if(Middleman % 2 == 0)
        {
            meridian = " PM";
        }
        else{
            meridian = " AM";
        }
        
    }
    
    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the
     * parameters.
     */
    public ClockDisplay12Hr(int hour, int minute)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }
    
    public int getMiddleman(){
     return   Middleman;
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
        
        convertCheck = hours.getValue();
        setMeridian();
        convertCheck();
        updateDisplay();
    }
    /**
     * gets meridians current value, not useful outside of debugging 
     */
    public String getMeridian()
    {
        return meridian;
    }
    
    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        convertCheck = hours.getValue();
        Middleman = 1; // sets middleman to one as a starting value
        setMeridian();
        convertCheck();
        updateDisplay();
    }
    /**
     * gets convertChecks current value, not useful outside of debugging
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
     * 
     */
    public void convertCheck()
    {
        
        // the clock hits 12pm and rolls over to 00:00 add 12 to the hours field and continue counting
        if(convertCheck == 0)
        {
            convertCheck = convertCheck + 12;
        } 
        // when the clock ticks from 12:59 to 13, subtract twelve so that clock properly displays 1:00AM/1:00PM
        if(convertCheck > 12)
        {
            convertCheck = convertCheck - 12;
        }
        // if convert check == 12 (it's noon or midnight) add one to middleman, which changes the meridian from AM to PM/PM to AM
        if(convertCheck == 12) {
            Middleman = Middleman + 1;
        }
        setMeridian(); //calls setMeridian again so that meridians display value continues to update relative to the time.
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
