package view;

import global.GraphicConstant;
import global.ressources.StringManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EmptyView extends JPanel {
    private JLabel empty = new JLabel(StringManager.NONE_LABEL);

    public EmptyView() {
        add(empty);
        setBorder(new EmptyBorder(
                GraphicConstant.EMPTY_VIEW_BORDER_Y,
                GraphicConstant.EMPTY_VIEW_BORDER_X,
                GraphicConstant.EMPTY_VIEW_BORDER_Y,
                GraphicConstant.EMPTY_VIEW_BORDER_X
        ));
        setAlignmentX(CENTER_ALIGNMENT);
    }
}
