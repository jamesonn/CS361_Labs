import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame{

    private JTextField firstName;
    private JTextField lastName;
    private JTextField phone;
    private JTextField department;
    private JList list;
    private ButtonGroup genders;
    private JRadioButton male;
    private JRadioButton female;
    private JRadioButton other;
    private static final int TEXT_LENGTH = 15;
    private Client c;
    private int currentEmployeeIndex = 0;
    private String selectedGender = "";


    public UserInterface(Client c) {
        this.c = c;
        createContents();
        //setSize(850, 300);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createContents() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        firstName = new JTextField(TEXT_LENGTH);
        lastName = new JTextField(TEXT_LENGTH);
        phone = new JTextField(TEXT_LENGTH);
        department = new JTextField(TEXT_LENGTH);

        // Information Pane
        p.setBorder(BorderFactory.createTitledBorder("Employee Information"));
        constraints.gridx = 0;
        constraints.gridy = 0;
        p.add(new JLabel("First Name:"),constraints);
        JPanel temp = new JPanel();
        temp.add(firstName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        p.add(temp, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        p.add(new JLabel("Last Name:"),constraints);
        temp = new JPanel();
        temp.add(lastName,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        p.add(temp,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        p.add(new JLabel("Phone NUmber:"),constraints);
        temp = new JPanel();
        temp.add(phone,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        p.add(temp,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        p.add(new JLabel("Department:"),constraints);
        temp = new JPanel();
        temp.add(department,constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        p.add(temp,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        p.add(new JLabel("Gender:"),constraints);

        genders = new ButtonGroup();
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        other = new JRadioButton("Other");
        genders.add(male);
        genders.add(female);
        genders.add(other);

        constraints.gridx = 1;
        constraints.gridy = 4;
        p.add(male,constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        p.add(female,constraints);

        constraints.gridx = 3;
        constraints.gridy = 4;
        p.add(other,constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        p.add(new JLabel("Title:"),constraints);


        constraints.gridx = 1;
        constraints.gridy = 5;
        String titles[] = {"Mr.","Ms.","Mrs.","Dr.","Col.","Prof."};
        list = new JList(titles);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        p.add(list, constraints);

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
                setFields(em.getFirstName(), em.getLastName(), em.getDepartment(), em.getPhoneNumber(), em.getGender(), em.getTitle());
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
                    setFields(em.getFirstName(), em.getLastName(), em.getDepartment(), em.getPhoneNumber(), em.getGender(), em.getTitle());
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

    private void setFields() {
        setFields("", "", "", "", "", "");
    }

    private Employee getEmployeeFromFields(){
        if(male.isSelected()) {
            selectedGender = male.getText();
        }else if(female.isSelected()) {
            selectedGender = female.getText();
        }else if(other.isSelected()) {
            selectedGender = other.getText();
        }
        return new Employee(firstName.getText(), lastName.getText(), department.getText(), phone.getText(), selectedGender, list.getSelectedValue().toString());
    }
    private void setFields(String firstName, String lastName, String department, String phoneNumber, String gender, String title) {
        this.firstName.setText(firstName);
        this.lastName.setText(lastName);
        this.department.setText(department);
        this.phone.setText(phoneNumber);
        //TODO maybe pass these something to set to other than clear
        this.genders.clearSelection();
        this.list.clearSelection();
    }
}
