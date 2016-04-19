import java.util.Scanner;

/**
 * Created by Nate on 4/19/2016.
 */
public class Driver {

    private static GarageDoorSystem gds = new GarageDoorSystem();

    public static void main(String args[]){
        gds.on();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while(running) {
            switch(scanner.next()) {
                case "LCLICK":
                    gds.lightClick();
                    break;
                case "DCLICK":
                    gds.doorClick();
                    break;
                case "LIMIT":
                    gds.limitHit();
                    break;
                case "SAFETY":
                    gds.safetyHit();
                    break;
            }
        }
    }
}

