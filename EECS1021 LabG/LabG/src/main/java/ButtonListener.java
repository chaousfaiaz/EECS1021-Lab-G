import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;

import java.io.IOException;

import java.text.DecimalFormat;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ButtonListener implements IODeviceEventListener {
    private final Pin ledPin; //note that a FINAL attribute can't be changed once initialized
    private final Pin buttonPin; //FINAL attributes are constants!
    private boolean[] tosses;

    /**
     * Constructor.  In the constructor:
     * 1. Assign ledPin to the ledPin attribute of the class.
     * 2. Assign buttonPin to the buttonPin attribute of the class.
     * 3. Initialize tosses so that it can hold 1000 boolean values.
     *
     * @param ledPin a Pin object referencing the LED pin on the Grove
     * @param buttonPin a Pin object referencing the BUTTON pin on the Grove
     */
    ButtonListener(Pin ledPin, Pin buttonPin) {
        //write your code here
        this.ledPin = ledPin;
        this.buttonPin = buttonPin;
        this.tosses = new boolean[1000];
         //replace this line!
    }

    /**
     * Handle a "Pin Change" event.
     * In this method:
     * 1. GET the value of this.ledPin.
     * 2. If it is 1 ... run 'simulateCoinTosses' and set the value of this.ledPin to 0.
     * 3. If it is 0 ... set the value of this.ledPin to 1 and print the following to the console:
     * "The current percentage of heads is XX.XX%"
     * Where XX.XX is the percentage of heads (or true values) in this.tosses.
     * @param event, the IO event that triggered the handler
     */
    @Override
    public void onPinChange(IOEvent event) {

        // Return right away if the event isn't from the Button.
        if (event.getPin().getIndex() != buttonPin.getIndex()) {
            return;
        }
try{
    int ledState = (int) ledPin.getValue();
    if(ledState == 1){
        simulateCoinTosses();
        ledPin.setValue(0);
    }else{
        ledPin.setValue(1);
        System.out.printf("The current percentage heads is %.2f%%%n",getPercentHeads());
    }
}catch(IOException e){e.printStackTrace();}
    }
    
        
        //WRITE YOUR CODE HERE

    

    /**
     * Simulate 1000 coin tosses.
     * This method should contain a loop that iterates 1000 times.
     * At each iteration i, simulate the flip of a coin.
     * If it is heads, assign this.tosses[i] the value TRUE.
     * If it is tails, assign this.tosses[i] the value FALSE.
     * Make sure there is an equal probability the coin is HEADS or TAILS.
     */
    public void simulateCoinTosses() {
        //WRITE YOUR CODE HERE
        Random random = new Random();
        for(int i = 0; i<tosses.length; i++){
            tosses[i] = random.nextBoolean();
        }//replace this line!
    }

    /**
     * Return the percent of the coin tosses that are "heads", rounded
     * to TWO OR LESS DECIMAL PLACES.  Look at methods in the Math package for
     * methods to facilitate ROUNDING. You may import any package in the Java
     * base distribution that you think might help with rounding.
     *
     * @return a ROUNDED double between 0 and 100, representing the percentage of "heads" in this.tosses.
     */
    public double getPercentHeads( ) {
        //WRITE YOUR CODE HERE
        int headcount = 0;
        for(boolean toss : tosses){
            if(toss){
                headscount++;
            }
        }
        double percentheads = (double) headscount/tosses.length)*100;//replace this line!
    }

    /* BELOW ARE ADDITIONAL METHODS from the IODeviceEventListener interface.
    We need to declare these, but we can leave the implementation
    blank.*/
    @Override
    public void onMessageReceive(IOEvent ioEvent, String s) {

    }


    @Override
    public void onStart(IOEvent ioEvent) {

    }

    @Override
    public void onStop(IOEvent ioEvent) {

    }

}
