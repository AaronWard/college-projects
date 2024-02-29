import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.border.*;


/**
 * The driver program that is used to display the information 
 * obtained using the Abstract Factory Pattern.
 * 
 * The program displays 4 buttons for each town, once clicked the UI 
 * is updated which the appropriate information.
 * ActionListeners are used to create a new App object.
 * 
 * The Buttons also update the frame to display a map picture with 
 * road map. 
 */

public class ApplicationTest extends JFrame{
    JButton dublinButton, corkButton, galwayButton, kilkennyButton;
    JPanel outterPanel, northPanel, centerPanel, southPanel;
    JTextArea townText, restaurantText, hotelText, placeText, descriptionText;
    JLabel imageLabel;
    Font font = new Font("", Font.PLAIN, 18);
    ImageIcon icon = null;
    private App town = null;

    public static void main(String[] args) {
        JFrame frame = new ApplicationTest();
        frame.setVisible(true);
        frame.setLocation(200, 200);
        frame.setSize(995, 525);
    }

    /**
     *  GUI method for JFrame 
     */
    public ApplicationTest(){    
        /**Creat panels for UI */
        Container contentPane = getContentPane();

        outterPanel = new JPanel();
        northPanel = new JPanel();
        centerPanel = new JPanel();
        southPanel = new JPanel();

        /** Set Layouts */
        outterPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new GridLayout(1, 2));
        northPanel.setLayout(new GridLayout(2,4));
    
        /** Create Buttons for north panel */
        dublinButton = new JButton("Dublin");
        corkButton = new JButton("Cork");
        galwayButton = new JButton("Galway");
        kilkennyButton = new JButton("Kilkenny");

        /** Set colour of buttons **/ 
        dublinButton.setBackground(new Color(255, 217, 179));
        corkButton.setBackground(new Color(255, 217, 179));
        galwayButton.setBackground(new Color(255, 217, 179));
        kilkennyButton.setBackground(new Color(255, 217, 179));

        /**Add action listeners to the buttons */
        dublinButton.addActionListener(new DublinListener());
        corkButton.addActionListener(new CorkListener());
        galwayButton.addActionListener(new GalwayListener());
        kilkennyButton.addActionListener(new KilkennyListener());

        /** add buttons to the north panel */
        northPanel.add(dublinButton);
        northPanel.add(corkButton);
        northPanel.add(galwayButton);
        northPanel.add(kilkennyButton);

        /** Create JLabels for UI **/
        JLabel townLabel = new JLabel("Town");
        JLabel restLabel = new JLabel("Restaurants");
        JLabel hotelLabel = new JLabel("Hotels");
        JLabel placeLabel = new JLabel("Places of Interest");

        townLabel.setFont(new Font("", Font.BOLD, 20));
        restLabel.setFont(new Font("", Font.BOLD, 20));
        hotelLabel.setFont(new Font("", Font.BOLD, 20));
        placeLabel.setFont(new Font("", Font.BOLD, 20));

        /** add labels to panel **/
        northPanel.add(townLabel);
        northPanel.add(restLabel);
        northPanel.add(hotelLabel);
        northPanel.add(placeLabel);

        /** create new text areas */
        townText = new JTextArea();
        restaurantText = new JTextArea();
        hotelText = new JTextArea();
        placeText = new JTextArea();
        descriptionText = new JTextArea();

        /**Settings for text on UI */
        townText.setFont(font);
        hotelText.setFont(font);
        restaurantText.setFont(font);
        placeText.setFont(font);
        descriptionText.setFont(new Font("", Font.PLAIN, 18));
        
        descriptionText.setLineWrap(true);
        descriptionText.setWrapStyleWord(true);
        descriptionText.setBackground(new Color(255, 217, 179));


        /** Apply borders to the text areas to seperate information */
        townText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        restaurantText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hotelText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        placeText.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        /** Disable editing for text areas */
        townText.setEditable(false);
        restaurantText.setEditable(false);
        hotelText.setEditable(false);
        placeText.setEditable(false);

        /** Give a grid layout and add text areas */
        centerPanel.setLayout(new GridLayout(1, 4));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerPanel.add(townText);
        centerPanel.add(restaurantText);
        centerPanel.add(hotelText);
        centerPanel.add(placeText);

        /**add components to south panel */
        southPanel.add(descriptionText);
        
        /** Add panels to content pane */
        outterPanel.add(northPanel, BorderLayout.NORTH);
        outterPanel.add(centerPanel, BorderLayout.CENTER);
        outterPanel.add(southPanel, BorderLayout.SOUTH);

        /** Set border for padding*/
        outterPanel.setBorder(new EmptyBorder(10,10,10,10));

        contentPane.add(outterPanel);
        setTitle("Tourist Helper");
        setVisible(true);
    }


    /**
    * The following classes are listeners made for each of 
    * buttons on the user interface. Once a button is clicked
    * a new town object is instantiated and the text is 
    * updated accordingly.
    * 
    */
    class DublinListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            town = new Dublin();
            setImage("Dublin");
            setText();
        }
    }

    class CorkListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            town = new Cork();
            setImage("cork");
            setText();
        }
    }
    class GalwayListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            town = new Galway();
            setImage("galway");
            setText();
        }
    }
    class KilkennyListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            town = new Kilkenny();
            setImage("kilkenny");
            setText();
        }
    }

    /**
    * this method sets the text of the texts areas when it is called from the 
    * Action Listeners
    */
    public void setText(){
        townText.setText(town.getTown().getName());
        restaurantText.setText(town.getRestaurant().getName());
        hotelText.setText(town.getHotel().getName());
        placeText.setText(town.getPlace().getName());
        descriptionText.setText(town.getDescription().getName());
    }

    /**
     * Image is set using a parameter being passed to the 
     * path to set the image
     */
    public void setImage(String url){

        try {
            if(imageLabel != null){
                southPanel.remove(imageLabel);            
            }

            icon = new ImageIcon(getClass().getResource("/images/"+ url +".png"));
            imageLabel = new JLabel();
            imageLabel.setIcon(icon);
            imageLabel.setSize(10, 10);
            southPanel.add(imageLabel); 
                
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
