/**
 * Created by Nate on
 */
public class Driver {

    public static void main(String args[]){

        Thread threads[] = new Thread[8];

        for(int i = 0; i < 8; i++){
            threads[i] = new Thread(new Sensor(i));
        }

        for(int i = 0; i < 8; i++){
            threads[i].start();
        }

        try {
            threads[7].join();
        }catch(Exception e){

        }

        while(threads[7].isAlive()) {
            for (int i = 0; i < 8; i++) {
                try {
                    threads[i].sleep(5000);
                } catch (InterruptedException e) {
                    threads[i].toString();
                }
            }
        }
    }
}
