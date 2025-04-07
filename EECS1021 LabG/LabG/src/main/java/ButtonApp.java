import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

public class ButtonApp {

    private final String myPort = "/dev/cv.usbserial-0001"; // modify for your own computer & setup.
    private IODevice myGroveBoard;
    private ButtonListener theButtonListener;

    /**
     * Constructor for a ButtonApp
     */
    public ButtonApp() throws IOException {

        this.myGroveBoard = new FirmataDevice(this.myPort);

        try {
            this.myGroveBoard.start(); // start communication with board;
            this.myGroveBoard.ensureInitializationIsDone();
            System.out.println("Board started."); //hopefully we make it here.
        } catch (Exception ex) { // if not, detail the error.
            System.out.println("couldn't connect to board.");
            return; //no point continuing at this point.
        }
        Pin ledPin = myGroveBoard.getPin(4);
        Pin buttonPin = myGroveBoard.getPin(6);
        ledPin.setMode(Pin.Mode.OUTPUT);
        buttonPin.setMode(Pin.Mode.INPUT);
        this.theButtonListener = new ButtonListener(ledPin, buttonPin);
        myGroveBoard.addEventListener(theButtonListener);
    }

        //In the space below:
        //1. Initialize two Pin objects
        //One should point to myGroveBoard.getPin(4) and the second to myGroveBoard.getPin(6);
        //2. Set the mode of the first pin to Pin.Mode.OUTPUT and the mode of the second to Pin.Mode.INPUT.
        //3. Initialize a this.theButtonListener with a new ButtonListener.  Use the Pins you initialized as arguments.
        //4. Add this.theButtonListener as an addEventListener to myGroveBoard

    

    /**
     * Stop the Board
     * @throws IOException
     */
    private void stopApp() throws IOException {
        this.myGroveBoard.stop();
    }


    public static void main(String[] args) {
try{
        ButtonApp p = new ButtonApp();
        System.out.println("Button App running, Press Button to toggle LED");
        while(true){ Thread.sleep(1000);}
    }catch(IOException | InterruptedException e){
    System.out.println("An error occurred: " + e.getMessage());
}
    }



}
