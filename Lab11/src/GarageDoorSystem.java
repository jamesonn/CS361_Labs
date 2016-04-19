/**
 * Created by Nate on 4/19/2016.
 */
public class GarageDoorSystem {

    private GarageDoor theDoor = new GarageDoor();

    public void on(){
        theDoor = new GarageDoor();
    }

    public void off(){
        theDoor = null;
    }

    public void lightClick(){
        theDoor.lightClick();
    }

    public void doorClick(){
        theDoor.onClick();
    }

    public void limitHit(){
        theDoor.onLimit();
    }

    public void safetyHit(){
        theDoor.onSafety();
    }
}
