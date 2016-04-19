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
        try{
            TimeUnit.SECONDS.sleep(20);
        }
        catch(Exception e){

        }
        lightState = false;
    }

    public void close(){
        motor.on();
    }

    public void stop(){
        motor.off();
        if(doorState){
            doorState = false;
        }else{
            doorState = true;
        }
    }

    public void lightClick(){
        if(lightState){
            lightState = false;
        }else{
            lightState = true;
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
    }

    public void onLimit(){
        motor.off();
    }
}
