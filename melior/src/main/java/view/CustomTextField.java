package view;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {


    public CustomTextField(int width, int height) {
        setPreferredSize(new Dimension(width/8, height/15));
    }
}
