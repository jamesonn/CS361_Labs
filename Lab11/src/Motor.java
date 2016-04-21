/**
 * Lab11
 * @author Group1
 */
public class Motor {

    private boolean motorState = false;

    protected void on(){
        motorState = true;
    }

    protected void off(){
        motorState = false;
    }

    protected boolean getMotorState(){
        return motorState;
    }

    protected void reverse(){
        if(motorState){
            motorState = false;
        }else{
            motorState = true;
        }
    }
}
