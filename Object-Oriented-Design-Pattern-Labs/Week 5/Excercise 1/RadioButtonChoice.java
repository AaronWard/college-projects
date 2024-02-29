import java.awt.*;
import java.util.*;
import javax.swing.*;
public class RadioButtonChoice extends multiChoice {
    int count;           
    JPanel p;       
    JRadioButton rb;


    public RadioButtonChoice(Vector choices) {
        super(choices);
        count = 0;
        p = new JPanel();
    }

    public JPanel getUI() {
        String s;
        p.setLayout(new GridLayout(choices.size(), 2));  

        for (int i=0; i< choices.size(); i++) {
            s =(String)choices.elementAt(i);
            p.add(new JRadioButton(s));
            count++;
        }
        return p;
    }

//--------------------------------------------
    public String[] getSelected() {
        Vector clist = new Vector();

        for (int i = 0; i < count; i++ ) {
            rb = (JRadioButton)p.getComponent(i);
            if (rb.isSelected ())
                clist.addElement(rb.getText());
        }
        //create a string array the size of the
        //number of checked boxes
        String[] slist = new String[clist.size()];

        //copy labels of chekced boxes into
        //the string array
        for (int i = 0; i < clist.size(); i++){
            slist[i] = (String)(clist.elementAt(i));
        }
        return slist;
    }


    public void clearAll() {
        for (int i=0; i < count; i++) {
            // JRadioButton.remove();
        }
    }
}

