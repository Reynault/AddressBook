package view.showFrame;

import javax.swing.*;

public class ShowPanelFactory {

    public static JPanel getEmptyPanel(){
        return EmptyView.getInstance();
    }

    public static JPanel getItemView(String name, String surname, String email){
        return new ItemView(name, surname, email);
    }
}
