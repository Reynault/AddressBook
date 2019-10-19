package view;

import controller.MainViewController;
import global.GlobalError;
import global.GlobalUpdate;
import global.GraphicConstant;
import global.ressources.StringManager;
import model.AddressBook;
import model.contact.Contact;
import view.showFrame.ShowPanelFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainView implements Observer {
    private JFrame jFrame = new JFrame(StringManager.MAIN_FRAME_TITLE);

    private JButton create = new JButton(StringManager.CREATE_BUTTON);
    private JButton delete = new JButton(StringManager.DELETE_BUTTON);
    private JButton show = new JButton(StringManager.SHOW_BUTTON);

    private ShowFrame showFrame;

    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JList<String> jlist = new JList<>(defaultListModel);

    private MainViewController controller;

    public MainView(MainViewController controller) {
        this.controller = controller;
        buildFrame();
    }

    public void buildFrame() {
        JPanel contentPane = (JPanel) jFrame.getContentPane();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        contentPane.setPreferredSize(new Dimension(
                GraphicConstant.MAIN_FRAME_WIDTH,
                GraphicConstant.MAIN_FRAME_HEIGHT
        ));

        contentPane.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();

        jlist.setFixedCellHeight(GraphicConstant.FIXED_CELL_HEIGHT);
        jlist.setCellRenderer(new CellRenderer());

        scrollPane.add(jlist);
        scrollPane.setViewportView(jlist);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        create.setPreferredSize(new Dimension(
                GraphicConstant.CREATE_BUTTON_WIDTH,
                GraphicConstant.CREATE_BUTTON_HEIGHT
        ));

        delete.setPreferredSize(new Dimension(
                GraphicConstant.DELETE_BUTTON_WIDTH,
                GraphicConstant.DELETE_BUTTON_HEIGHT
        ));

        show.setPreferredSize(new Dimension(
                GraphicConstant.SHOW_BUTTON_WIDTH,
                GraphicConstant.SHOW_BUTTON_HEIGHT
        ));

        JPanel buttons = new JPanel();
        buttons.add(create);
        buttons.add(delete);
        buttons.add(show);

        contentPane.add(buttons, BorderLayout.SOUTH);

        create.setEnabled(true);
        delete.setEnabled(false);
        show.setEnabled(false);

        showFrame = new ShowFrame();

        // Setting listeners

        // When an item in the jlist is selected
        jlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {

                    GlobalError code = controller.askForContact(
                        jlist.getSelectedValue()
                    );

                    switch (code){
                        case SUCCESS:
                            delete.setEnabled(true);
                            show.setEnabled(true);
                            break;
                    }

                }
            }
        });

        // When the delete button is selected
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jlist.getSelectedIndex() != -1 && !defaultListModel.isEmpty()){
                    GlobalError code = controller.removeContact(
                            jlist.getSelectedValue()
                    );

                    switch (code){
                        case SUCCESS:
                            delete.setEnabled(false);
                            break;
                    }

                }
            }
        });

        // When the create button is triggered
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*GlobalError code = controller.createContact(

                );*/
                create.setEnabled(false);
            }
        });

        // When show button is triggered
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFrame.setVisible(!showFrame.isVisible());
            }
        });

        jFrame.pack();
        jFrame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        GlobalUpdate code = (GlobalUpdate) arg;
        AddressBook addressBook = (AddressBook) o;
        switch (code){
            case GET:
                Contact c = addressBook.getContact(jlist.getSelectedValue());
                showFrame.changePanel(
                        ShowPanelFactory.getItemView(
                                c.getName(), c.getSurname(), c.getEmail()
                        )
                );
                showFrame.setVisible(true);
                break;
            case CHANGE:
                // TODO
                break;
            case CREATE:
                // TODO
                break;
            case DELETE:
                defaultListModel.remove(jlist.getSelectedIndex());

                showFrame.changePanel(
                        ShowPanelFactory.getEmptyPanel()
                );

                showFrame.setVisible(false);
                break;
            case GET_ALL:
                Collection<Contact> contacts = addressBook.getContacts();
                Iterator<Contact> contactIterator = contacts.iterator();
                while (contactIterator.hasNext()){
                    Contact contact = contactIterator.next();
                    defaultListModel.addElement(contact.getEmail());
                }
                break;
        }
    }

    private class CellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            component.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0,
                            0,
                            GraphicConstant.CELL_LINE_HEIGHT,
                            0,
                            GraphicConstant.CELL_BORDER_COLOR),

                    BorderFactory.createEmptyBorder(GraphicConstant.CELL_BORDER_UP,
                            GraphicConstant.CELL_BORDER_LEFT,
                            GraphicConstant.CELL_BORDER_BOTTOM,
                            GraphicConstant.CELL_BORDER_RIGHT)
            ));
            return component;
        }

    }
}
