import java.util.concurrent.TimeUnit;

/**
 * Created by Nate on 4/19/2016.
 */
public class GarageDoor {

    private boolean doorState;
    private boolean lightState;
    private Motor motor = new Motor();

    public void open(){
        motor.on();
        lightState = true;
        System.out.println("Opening... and light on");
        try{
            TimeUnit.SECONDS.sleep(20);
        }
        catch(Exception e){

        }
        lightState = false;
        System.out.println("Light off");
    }

    public void close(){
        motor.on();
        System.out.println("Closing...");
    }

    public void stop(){
        motor.off();
        System.out.println("Door Stopped");
        if(doorState){
            doorState = false;
        }else{
            doorState = true;
        }
    }

    public void lightClick(){
        if(lightState){
            lightState = false;
            System.out.println("Light off");
        }else{
            lightState = true;
            System.out.println("Light on");
        }
    }

    public void onClick(){
        if(motor.getMotorState()){
            stop();
        }else if(!motor.getMotorState() && doorState){
            open();
        } else if (!motor.getMotorState() && !doorState) {
            close();
        }
    }

    public void onSafety(){
        motor.reverse();
        System.out.println("Safety hit");
    }

    public void onLimit(){
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
