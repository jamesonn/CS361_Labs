/**
 * @author Group1
 */
public class GarageDoorSystem {

    private GarageDoor theDoor = new GarageDoor();

    protected void on(){
        theDoor = new GarageDoor();
    }

    public void off(){
        theDoor = null;
    }

    protected void lightClick(){
        theDoor.lightClick();
    }

    protected void doorClick(){
        theDoor.onClick();
    }

    protected void limitHit(){
        theDoor.onLimit();
    }

    protected void safetyHit(){
        theDoor.onSafety();
    }
}
