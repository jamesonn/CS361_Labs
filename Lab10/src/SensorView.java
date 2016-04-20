import javax.swing.*;

/**
 * Created by Nate on 4/12/2016.
 */
public class SensorView extends JFrame {

    String sensorName;
    Sensor sensor;

    public SensorView(String sensorName, Sensor sensor){
        this.sensor = sensor;
        this.sensorName = sensorName;
        JPanel p = new JPanel();
        p.add(new JLabel(sensorName));
        JButton button = new JButton(sensorName);
        button.addActionListener(e -> {
            try{
                trigger();
            } catch (IndexOutOfBoundsException e1) {

            }
        });
        p.add(button);
        add(p);
        setSize(250,100);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void trigger(){
        sensor.interrupt();
    }
}
