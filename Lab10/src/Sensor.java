/**
 * Created by Nate on 4/12/2016.
 */
public class Sensor implements Runnable{

    String sensorName;

    public Sensor(int sensorNum){
        sensorName = "Sensor"+sensorNum;
        SensorView ui = new SensorView(sensorName, this);
    }

    public void run(){
        try{

        }catch(Exception e){

        }
    }

    public void interrupt(){
        Thread.interrupted();
    }
}
