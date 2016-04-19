/**
 * Created by Nate on 4/19/2016.
 */
public class Motor {

    private boolean motorState = false;

    public void on(){
        motorState = true;
    }

    public void off(){
        motorState = false;
    }

    public boolean getMotorState(){
        return motorState;
    }

    public void reverse(){
        if(motorState){
            motorState = false;
        }else{
            motorState = true;
        }
    }
}
