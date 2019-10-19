package view.showFrame;

import global.GraphicConstant;
import global.ressources.StringManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static global.GraphicConstant.NOTHING_FRAME_HEIGHT;
import static global.GraphicConstant.NOTHING_FRAME_WIDTH;

public class EmptyView extends JPanel {
    private JLabel empty = new JLabel(StringManager.NONE_LABEL);
    private static EmptyView instance = new EmptyView();

     static EmptyView getInstance() {
        return instance;
    }

    private EmptyView() {
        add(empty);
        setBorder(new EmptyBorder(
                GraphicConstant.EMPTY_VIEW_BORDER_Y,
                GraphicConstant.EMPTY_VIEW_BORDER_X,
                GraphicConstant.EMPTY_VIEW_BORDER_Y,
                GraphicConstant.EMPTY_VIEW_BORDER_X
        ));
        setPreferredSize(
                new Dimension(
                        NOTHING_FRAME_WIDTH,
                        NOTHING_FRAME_HEIGHT
                )
        );
        setAlignmentX(CENTER_ALIGNMENT);
    }
}
