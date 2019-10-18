package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class MainView {
    private JFrame jFrame = new JFrame(StringManager.MAIN_FRAME_TITLE);
    private JButton create = new JButton(StringManager.CREATE_BUTTON);
    private JButton modify = new JButton(StringManager.MODIFY_BUTTON);
    private JButton delete = new JButton(StringManager.DELETE_BUTTON);

    private JList jlist = new JList();

    public MainView() {
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

        jlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                System.out.println("qsdqsd");
            }
        });

        jlist.setFixedCellHeight(GraphicConstant.FIXED_CELL_HEIGHT);

        contentPane.add(jlist, BorderLayout.CENTER);

        create.setPreferredSize(new Dimension(
                GraphicConstant.CREATE_BUTTON_WIDTH,
                GraphicConstant.CREATE_BUTTON_HEIGHT
        ));

        delete.setPreferredSize(new Dimension(
                GraphicConstant.DELETE_BUTTON_WIDTH,
                GraphicConstant.DELETE_BUTTON_HEIGHT
        ));

        modify.setPreferredSize(new Dimension(
                GraphicConstant.MODIFY_BUTTON_WIDTH,
                GraphicConstant.MODIFY_BUTTON_HEIGHT
        ));

        JPanel south = new JPanel();
        contentPane.add(south, BorderLayout.SOUTH);
        south.add(create);
        south.add(modify);
        south.add(delete);

        jFrame.pack();
        jFrame.setVisible(true);
    }
}
