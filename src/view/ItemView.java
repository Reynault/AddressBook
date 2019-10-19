package view;

import global.ressources.StringManager;

import javax.swing.*;
import java.awt.*;

public class ItemView extends JPanel {

    private JLabel name = new JLabel();
    private JTextField nameField = new JTextField();

    private JLabel surname = new JLabel();
    private JTextField surnameField = new JTextField();

    private JLabel email = new JLabel();
    private JTextField emailField = new JTextField();

    private JLabel error = new JLabel();

    private JButton modify = new JButton(StringManager.MODIFY_BUTTON);

    public ItemView() {
        this.setLayout(new BorderLayout());

        // Configure center pane
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(name);
        center.add(nameField);
        center.add(surname);
        center.add(surnameField);
        center.add(email);
        center.add(emailField);
        center.add(error);

        this.add(center, BorderLayout.CENTER);
    }

    public void setValues(String name, String surname, String email) {
        this.name.setText(name);
        this.surname.setText(surname);
        this.email.setText(email);
    }

    public void setError(String error) {
        this.error.setText(error);
    }
}
