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
}
