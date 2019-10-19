package view.showFrame;

import global.ressources.StringManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static global.GraphicConstant.*;

public class ItemView extends JPanel {

    private JLabel name = new JLabel();
    private JTextField nameField = new JTextField();

    private JLabel surname = new JLabel();
    private JTextField surnameField = new JTextField();

    private JLabel email = new JLabel();
    private JTextField emailField = new JTextField();

    private JLabel error = new JLabel();

    private JButton modify = new JButton(StringManager.MODIFY_BUTTON);

    public ItemView(String name, String surname, String email) {
        this.setLayout(new BorderLayout());

        // Configure center pane
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));


        setPreferredSize(
                new Dimension(
                        ITEM_FRAME_WIDTH,
                        ITEM_FRAME_HEIGHT
                )
        );

        this.name.setText(StringManager.NAME_LABEL);
        this.email.setText(StringManager.EMAIL_LABEL);
        this.surname.setText(StringManager.SURNAME_LABEL);

        this.nameField.setText(name);
        this.surnameField.setText(surname);
        this.emailField.setText(email);

        this.name.getHorizontalAlignment();

        this.name.setBorder(
                new EmptyBorder(
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X,
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X)
        );

        this.surname.setBorder(
                new EmptyBorder(
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X,
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X)
        );

        this.email.setBorder(
                new EmptyBorder(
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X,
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X)
        );

        this.nameField.setBorder(
                new EmptyBorder(
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X,
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X)
        );

        this.emailField.setBorder(
                new EmptyBorder(
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X,
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X)
        );

        this.surnameField.setBorder(
                new EmptyBorder(
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X,
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X)
        );

        center.add(this.name);
        center.add(nameField);
        center.add(this.surname);
        center.add(surnameField);
        center.add(this.email);
        center.add(emailField);
        center.add(error);

        this.add(center, BorderLayout.CENTER);

        this.setBorder(
                new EmptyBorder(
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X,
                        ITEM_VIEW_BORDER_Y,
                        ITEM_VIEW_BORDER_X)
        );
    }

}
