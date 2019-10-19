package view;

import global.ressources.StringManager;
import view.showFrame.ShowPanelFactory;

import javax.swing.*;

public class ShowFrame {
    private JFrame frame = new JFrame(StringManager.SHOW_FRAME_TITLE);
    private JPanel contentPane;

    public ShowFrame() {
        buildFrame();
    }

    private void buildFrame() {
        contentPane = (JPanel) frame.getContentPane();

        contentPane.add(ShowPanelFactory.getEmptyPanel());

        frame.pack();
        frame.setVisible(false);
    }

    public void setVisible(boolean val) {
        frame.setVisible(val);
    }

    public boolean isVisible() {
        return frame.isVisible();
    }

    public void changePanel(JPanel panel) {
        contentPane.removeAll();
        contentPane.add(panel);

        contentPane.setPreferredSize(
                panel.getPreferredSize()
        );

        contentPane.revalidate();
        contentPane.repaint();
        frame.pack();
    }
}
