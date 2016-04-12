import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    trigger();
                } catch (IndexOutOfBoundsException e1) {

                }
            }
        });
        p.add(button);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void trigger(){
        sensor.interrupt();
    }
}
