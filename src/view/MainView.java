package view;

import controller.MainViewController;
import global.GraphicConstant;
import global.ressources.StringManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    private JFrame jFrame = new JFrame(StringManager.MAIN_FRAME_TITLE);
    private JButton create = new JButton(StringManager.CREATE_BUTTON);
    private JButton delete = new JButton(StringManager.DELETE_BUTTON);

    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private JList<String> jlist = new JList<>(defaultListModel);

    private ItemView itemView = new ItemView();
    private EmptyView emptyView = new EmptyView();

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

        defaultListModel.addElement("qsqsd");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("qqqq");
        defaultListModel.addElement("ssss");

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

        JPanel south = new JPanel();
        south.setLayout(new BorderLayout());

        contentPane.add(south, BorderLayout.SOUTH);

        JPanel north = new JPanel();
        north.add(create);
        north.add(delete);

        south.add(north, BorderLayout.NORTH);
        south.add(emptyView, BorderLayout.CENTER);

        create.setEnabled(true);
        delete.setEnabled(false);

        // Setting listeners

        // When an item in the jlist is selected
        jlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    delete.setEnabled(true);
                }
            }
        });

        // When the delete button is selected
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jlist.getSelectedIndex() != -1 && !defaultListModel.isEmpty()){
                    defaultListModel.remove(jlist.getSelectedIndex());
                }
                delete.setEnabled(false);
            }
        });

        // When the create button is triggered
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jFrame.pack();
        jFrame.setVisible(true);
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
