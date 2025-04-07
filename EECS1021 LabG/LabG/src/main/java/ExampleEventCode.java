import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.firmata.*;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;

import java.io.IOException;

/**
 * ExampleEventCode class.  Class to demonstrate Arduino event handling.
 */
public class ExampleEventCode {

    /**
     * Main method, with some demonstration code related to the Arduino
     * @throws IOException in the case that board communications don't work.
     */
    public static void main(String[] args) throws IOException {

        String myPort = "/dev/cv.usbserial-001"; // modify for your own computer & setup.
        IODevice myGroveBoard = new FirmataDevice(myPort); // using the name of a port

        // try to communicate with the board
        try {
            myGroveBoard.start(); // start communication with board;
            myGroveBoard.ensureInitializationIsDone();
            System.out.println("Board started."); //hopefully we make it here.
        } catch (Exception ex) { // if not, detail the error.
            System.out.println("couldn't connect to board.");
            return; //no point continuing at this point.
        }

        // assuming we have initialized the board ...
        Pin myButton = myGroveBoard.getPin(6);
        myButton.setMode(Pin.Mode.INPUT); // set pin 4 to be OUTPUT

        ExampleListener eventListener = new ExampleListener(myButton);
        myGroveBoard.addEventListener(eventListener);

        // Pause for two seconds.
        try {
            Thread.sleep(20000);
        } catch (Exception ex) {
            System.out.println("sleep error.");
        }

        myGroveBoard.stop(); // stop the board.
        System.out.println("Board stopped.");

    }

    /**
     * A "nested" ExampleListener class.  Class to demonstrate IODeviceEventListener.
     */
    private static class ExampleListener implements IODeviceEventListener {

        Pin buttonPin;
        public ExampleListener(Pin buttonPin) {this.buttonPin = buttonPin;}
        @Override
        public void onPinChange(IOEvent ioEvent) {

            // Return right away if the event isn't from the Button.
            if (ioEvent.getPin().getIndex() != buttonPin.getIndex()) {
                return;
            }

            if (ioEvent.getPin().getValue() == 1) System.out.println("A button event occurred!");
        }

        @Override
        public void onStart(IOEvent ioEvent) {}
        @Override
        public void onStop(IOEvent ioEvent) {}
        @Override
        public void onMessageReceive(IOEvent ioEvent, String s) {}

    }


}
