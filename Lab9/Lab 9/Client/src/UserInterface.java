import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class UserInterface extends JFrame{

    private JTextField firstName;
    private JTextField lastName;
    private JTextField phone;
    private JTextField department;
    private JComboBox titleList;
    private JRadioButton other;
    private ButtonGroup bg;
    private static final int TEXT_LENGTH = 15;
    private Client c;
    private int currentEmployeeIndex = 0;


    public UserInterface(Client c) {
        this.c = c;
        setTitle("Employees");
        createContents();
        setSize(500, 375);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createContents() {
        JPanel p = new JPanel();
        firstName = new JTextField(TEXT_LENGTH);
        lastName = new JTextField(TEXT_LENGTH);
        phone = new JTextField(TEXT_LENGTH);
        department = new JTextField(TEXT_LENGTH);

        String[] titleLabel = new String[]{"Mr.", "Ms.", "Mrs.", "Dr.", "Col.", "Prof", "other"};
        titleList = new JComboBox(titleLabel);

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        other = new JRadioButton("Unspecified");

        // Information Pane
        p.setBorder(BorderFactory.createTitledBorder("Employee Information"));
        JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        temp.add(new JLabel("Title:"));
        titleList.setSelectedIndex(6);
        temp.add(titleList);
        p.add(temp);

        temp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        temp.add(new JLabel("First Name:"));
        temp.add(firstName);
        p.add(temp);


        temp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        temp.add(new JLabel("Last Name:"));
        temp.add(lastName);
        p.add(temp);

        temp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        temp.add(new JLabel("Gender"));
        male.setActionCommand("Male");
        female.setActionCommand("Female");
        other.setActionCommand("Unspecified");
        other.setSelected(true);
        bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(other);
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(male);
        radioPanel.add(female);
        radioPanel.add(other);
        temp.add(radioPanel, BorderLayout.LINE_START);
        p.add(temp);

        temp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        temp.add(new JLabel("Phone NUmber:"));
        temp.add(phone);
        p.add(temp);


        temp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        temp.add(new JLabel("Department:"));
        temp.add(department);
        p.add(temp);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        this.add(p, BorderLayout.CENTER);


        // Previous Button
        p = new JPanel();
        p.setLayout(new GridBagLayout());
        JButton prev = new JButton("Previous");
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentEmployeeIndex == 0) return;
                Employee em = getEmployeeFromFields();
                c.addToList(em, currentEmployeeIndex);
                em = c.getEmployee(--currentEmployeeIndex);
                setFields(em);
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        p.add(prev, gbc);
        add(p, BorderLayout.WEST);


        // Next Button
        p = new JPanel();
        p.setLayout(new GridBagLayout());
        JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee em = getEmployeeFromFields();
                currentEmployeeIndex = c.addToList(em, currentEmployeeIndex);
                try {
                    em = c.getEmployee(currentEmployeeIndex);
                    setFields(em);
                } catch (IndexOutOfBoundsException e1) {
                    setFields();
                }
            }
        });
        p.add(next, gbc);
        add(p, BorderLayout.EAST);


        // Submit Button
        p = new JPanel(new FlowLayout());
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.addToList(getEmployeeFromFields(), currentEmployeeIndex);
                System.out.println(c.getJSON());
                String response = c.sendData("sendresults", c.getJSON());
                System.out.println(response);
            }
        });
        p.add(submit);
        add(p, BorderLayout.SOUTH);
    }

    private Employee getEmployeeFromFields() {
        return new Employee(
                titleList.getSelectedItem().toString(),
                firstName.getText(),
                lastName.getText(),
                department.getText(),
                phone.getText(),
                bg.getSelection().getActionCommand()
        );
    }

    private void setFields() {
        this.titleList.setSelectedIndex(6);
        this.firstName.setText("");
        this.lastName.setText("");
        this.department.setText("");
        this.phone.setText("");
        this.other.setSelected(true);

    }

    private void setFields(Employee e) {
        this.titleList.setSelectedItem(e.getTitle());
        this.firstName.setText(e.getFirstName());
        this.lastName.setText(e.getLastName());
        this.department.setText(e.getDepartment());
        this.phone.setText(e.getPhoneNumber());
        for (Enumeration<AbstractButton> ee = bg.getElements(); ee.hasMoreElements(); ) {
            AbstractButton a = ee.nextElement();
            if (e.getGender().compareToIgnoreCase(a.getActionCommand()) == 0)
                a.setSelected(true);
            else
                a.setSelected(false);
        }
    }
}
