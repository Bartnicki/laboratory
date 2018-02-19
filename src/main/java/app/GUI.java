package app;

import app.exceptions.ItemQuantityException;
import app.exceptions.XMLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JPanel{

    public static Logger log;
    private JButton increaseItemQuantity;
    private JButton decreaseItemQuantity;
    private JButton changeItemStatus;
    private CustomButton changeItemRequirement;
    private JButton changeSkinButton1;
    private JButton changeSkinButton2;
    private JButton changeSkinButton3;

    private CustomLabel labelChooseRoom;
    private CustomJTable tableRooms = new CustomJTable();

    private JLabel chooseStation;
    private CustomJTable tableStations = new CustomJTable();

    private JLabel stationEquipment;
    private CustomJTable tableStationEquipment = new CustomJTable();


    StationTableView dataTableStationView;
    StationEquipmentTableView dataTableStationEquipmentView;
    RoomTableView dataTableRoomView;

    private int selectedRoomID;
    private ListSelectionModel modelRoomRow;

    private int selectedStationID;
    private ListSelectionModel modelStationRow;

    private int selectedItemID;
    private ListSelectionModel modelItemRow;

    private int selectedItemQuantity;
    private String selectedItemStatus;
    private String selectedItemRequirement;

    public GUI() {

       tableRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       tableStations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       tableStationEquipment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

       tableRooms.getTableHeader().setReorderingAllowed(false);
       tableStations.getTableHeader().setReorderingAllowed(false);
       tableStationEquipment.getTableHeader().setReorderingAllowed(false);

       log = LogManager.getLogger(GUI.class.getName());
       XMLReader reader = new XMLReader();


        try {
            increaseItemQuantity = new JButton(reader.getWord("increase"));
        } catch (XMLException e) {
            increaseItemQuantity = new JButton("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            decreaseItemQuantity = new JButton(reader.getWord("decrease"));
        } catch (XMLException e) {
            decreaseItemQuantity = new JButton("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            changeItemStatus = new JButton(reader.getWord("working"));
        } catch (XMLException e) {
            changeItemStatus = new JButton("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            changeItemRequirement = new CustomButton(reader.getWord("require"));
        } catch (XMLException e) {
            changeItemRequirement = new CustomButton("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            changeSkinButton1 = new JButton(reader.getWord("skin1"));
        } catch (XMLException e) {
            changeSkinButton1 = new JButton("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            changeSkinButton2 = new JButton(reader.getWord("skin2"));
        } catch (XMLException e) {
            changeSkinButton2 = new JButton("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            changeSkinButton3 = new JButton(reader.getWord("skin3"));
        } catch (XMLException e) {
            changeSkinButton3 = new JButton("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            labelChooseRoom = new CustomLabel(reader.getWord("chooseRoom"));
        } catch (XMLException e) {
            labelChooseRoom = new CustomLabel("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            chooseStation = new JLabel(reader.getWord("chooseStation"));
        } catch (XMLException e) {
            chooseStation = new JLabel("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }
        try {
            stationEquipment = new JLabel(reader.getWord("stationEquipment"));
        } catch (XMLException e) {
            stationEquipment = new JLabel("XMLerror");
            log.log(Level.ERROR,"xml reading error");
        }


        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {


            dataTableRoomView = DBConnection.getTableRoomData();

            return null;
            }
            @Override
            protected void done(){
                tableRooms.setModel(dataTableRoomView);
            }
        };
            worker.execute();


        modelRoomRow = tableRooms.getSelectionModel();
        modelRoomRow.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!modelRoomRow.isSelectionEmpty()){
                    int selectedRoomRow = modelRoomRow.getMinSelectionIndex();
                    selectedRoomID = (Integer) tableRooms.getValueAt(selectedRoomRow, 0);
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            dataTableStationView = DBConnection.getTableStationData(selectedRoomID);
                                    return null;
                        }
                        @Override
                         protected void done(){
                            tableStations.setModel(dataTableStationView);
                            tableStationEquipment.setModel(new StationEquipmentTableView());
                        }
                    };
                    worker.execute();
                }
            }
        });


        modelStationRow = tableStations.getSelectionModel();
        modelStationRow.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!modelStationRow.isSelectionEmpty()){
                    int selectedStationRow = modelStationRow.getMinSelectionIndex();
                    selectedStationID = (Integer) tableStations.getValueAt(selectedStationRow, 0);
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            dataTableStationEquipmentView = DBConnection.getTableStationEquipmentData(selectedStationID);
                            return null;
                        }
                        @Override
                        protected void done(){
                            tableStationEquipment.setModel(dataTableStationEquipmentView);

                        }
                    };
                    worker.execute();
                }
            }
        });



        JScrollPane scrollPane1 = new JScrollPane(tableStations);
        scrollPane1.getViewportBorderBounds();
        scrollPane1.setColumnHeaderView(tableStations.getTableHeader());

        JScrollPane scrollPane2 = new JScrollPane(tableStationEquipment);
        scrollPane2.getViewportBorderBounds();
        scrollPane2.setColumnHeaderView(tableStationEquipment.getTableHeader());


        modelItemRow = tableStationEquipment.getSelectionModel();
        modelItemRow.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!modelItemRow.isSelectionEmpty()){
                    int selectedItemRow = modelItemRow.getMinSelectionIndex();
                    selectedItemID = (Integer) tableStationEquipment.getValueAt(selectedItemRow, 4);
                    selectedItemQuantity = (Integer) tableStationEquipment.getValueAt(selectedItemRow, 3);
                    selectedItemStatus = (String) tableStationEquipment.getValueAt(selectedItemRow, 1);
                    selectedItemRequirement = (String) tableStationEquipment.getValueAt(selectedItemRow, 2);
                }
            }
        });


        increaseItemQuantity.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                log.log(Level.INFO,"Item quantity increased");
                StationEquipmentTableView dataTableStationEquipmentView = DBConnection.increaseQuantityData( selectedItemQuantity,  selectedStationID, selectedItemID);
                tableStationEquipment.setModel(dataTableStationEquipmentView);
            }
        });

        decreaseItemQuantity.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                log.log(Level.INFO,"Item quantity decreased");
                StationEquipmentTableView dataTableStationEquipmentView = null;
                try {
                    dataTableStationEquipmentView = DBConnection.decreaseQuantityData( selectedItemQuantity,  selectedStationID, selectedItemID);
                    tableStationEquipment.setModel(dataTableStationEquipmentView);
                } catch (ItemQuantityException itemQuantityException) {
                    selectedItemQuantity = 0;
                    log.log(Level.ERROR,"Item quantity cannot be less than null");
                }

            }
        });

        changeItemStatus.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                log.log(Level.INFO,"Item status changed!");
                StationEquipmentTableView dataTableStationEquipmentView = DBConnection.changeItemStatus(selectedStationID, selectedItemID, selectedItemStatus);
                tableStationEquipment.setModel(dataTableStationEquipmentView);
            }
        });

        changeItemRequirement.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                log.log(Level.INFO,"Item requirement status changed!");
                StationEquipmentTableView dataTableStationEquipmentView = DBConnection.changeItemRequirement(selectedStationID, selectedItemID, selectedItemRequirement);
                tableStationEquipment.setModel(dataTableStationEquipmentView);
            }
        });



        JButton twitterButton = new JButton();
        twitterButton.setIcon(new ImageIcon("data/twitter.png"));
        twitterButton.setBorder(BorderFactory.createEmptyBorder());
        twitterButton.setContentAreaFilled(false);
        twitterButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                TwitterApi twit = new TwitterApi();
                String dataTwitterArea = twit.twitter();

                JFrame twitterFrame = new JFrame("Twitter Posts");

                JTextArea twitterArea = new JTextArea();
                twitterArea.setText(dataTwitterArea);

                //button do Abstract Action
                JButton changeTwitterSkin1 = new JButton();
                JButton changeTwitterSkin2 = new JButton();


                twitterFrame.getContentPane().add(twitterArea, BorderLayout.CENTER);
                twitterFrame.getContentPane().add(changeTwitterSkin1, BorderLayout.EAST);
                twitterFrame.getContentPane().add(changeTwitterSkin2, BorderLayout.WEST);

                SkinAction skinButtonTwitterAction1 = new SkinAction(twitterFrame, "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                changeTwitterSkin1.addActionListener(skinButtonTwitterAction1);


                SkinAction skinButtonTwitterAction2 = new SkinAction(twitterFrame, "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                changeTwitterSkin2.addActionListener(skinButtonTwitterAction2);

                twitterFrame.setSize(1000, 400);
                twitterFrame.setLocationRelativeTo(null);
                twitterFrame.setVisible(true);

            }
        });


        SkinAction skinButtonAction1 = new SkinAction(this, "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        changeSkinButton1.addActionListener(skinButtonAction1);

        SkinAction skinButtonAction2 = new SkinAction(this, "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        changeSkinButton2.addActionListener(skinButtonAction2);

        SkinAction skinButtonAction3 = new SkinAction(this, "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        changeSkinButton3.addActionListener(skinButtonAction3);



        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(labelChooseRoom, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        add(tableRooms, c);

        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(chooseStation, c);


        c.gridx = 0;
        c.gridy = 3;
        add(scrollPane1, c);

        c.gridwidth = 3;
        c.gridx = 2;
        c.gridy = 0;
        add(stationEquipment, c);

        c.gridheight = 3;
        c.gridx = 2;
        c.gridy = 1;
        add(scrollPane2,c);

        c.gridx = 3;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(increaseItemQuantity, c);

        c.gridx = 4;
        c.gridy = 4;
        add(decreaseItemQuantity, c);

        c.gridx = 3;
        c.gridy = 5;
        add(changeItemStatus, c);

        c.gridx = 4;
        c.gridy = 5;
        add(changeItemRequirement, c);

        c.fill = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 4;
        add(changeSkinButton2, c);

        c.gridx = 0;
        c.gridy = 4;
        add(changeSkinButton1, c);

        c.gridx = 1;
        c.gridy = 5;
        add(changeSkinButton3, c);

        c.gridx = 0;
        c.gridy = 5;
        add(twitterButton,c);

    }

}
