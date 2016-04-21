import java.util.concurrent.TimeUnit;

/**
 *@author Group1 Nate, Jess, Sam, Shaun
 */
public class GarageDoor {

    private boolean doorState;
    private boolean lightState;
    private Motor motor = new Motor();

    public GarageDoor(){
        doorState = false;
        lightState = false;
    }

    /**
     *Turns motor and light on(20sec), prints states to console
     */
    private void open(){
        motor.on();
        lightState = true;
        System.out.println("Opening... and light on");
        try{
            TimeUnit.SECONDS.sleep(20);
            lightState = false;
            System.out.println("Light off");
        }
        catch(Exception e){

        }
    }

    /**
     * Turns motor off, prints state to console
     */
    private void close(){
        motor.on();

        System.out.println("Closing...");
    }

    /**
     * Turns motor off and changes door state
     */
    private void stop(){
        motor.off();
        System.out.println("Door Stopped");
        if(doorState){
            doorState = false;
        }else{
            doorState = true;
        }
    }

    /**
     * Inverts light state, prints new state to console
     */
    protected void lightClick(){
        if(lightState){
            lightState = false;
            System.out.println("Light off");
        }else{
            lightState = true;
            System.out.println("Light on");
        }
    }

    /**
     *
     */
    protected void onClick(){
        if(motor.getMotorState()){
            stop();
        }else if(!motor.getMotorState() && doorState){
            close();
        } else if (!motor.getMotorState() && !doorState) {
            open();
        }
    }

    /**
     *
     */
    protected void onSafety(){
        System.out.println("Safety hit");
        open();
    }

    /**
     *
     */
    protected void onLimit(){
        motor.off();
        if(doorState){
            doorState = false;
            System.out.println("Garage Close.");
        }else {
            doorState = true;
            System.out.println("Garage Open.");
        }
    }
}
