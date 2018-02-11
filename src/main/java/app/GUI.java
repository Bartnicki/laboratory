package app;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class GUI extends JPanel{


    public GUI() {

                                // komponenty

        // kolumna 1 grida

        //TABELA choose room
        JLabel labelChooseRoom = new JLabel("Choose room:");

        RoomTableView dataTableRoomView = DBConnection.getTableRoomData();
        final JTable tableRooms = new JTable(dataTableRoomView);

 //       JScrollPane scrollPane0 = new JScrollPane(tableRooms);

        // ROW SELECTION
        final ListSelectionModel model = tableRooms.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){

                    //get selected row
                    int selectedRow = model.getMinSelectionIndex();
                    int selectedRoomNumber = (Integer) tableRooms.getValueAt(selectedRow, 0);

                    JOptionPane.showMessageDialog(null, "Wybrałeś pokój numer: " + selectedRoomNumber);

                }
            }
        });


        //TABELA  choose station
        JLabel chooseStation = new JLabel("Choose station:");

        StationTableView dataTableStationView = DBConnection.getTableStationData();
        JTable tableStations = new JTable(dataTableStationView);

        JScrollPane scrollPane1 = new JScrollPane(tableStations);
        scrollPane1.getViewportBorderBounds();
        scrollPane1.setColumnHeaderView(tableStations.getTableHeader());

        //TABELA  station equipment
        JLabel stationEquipment = new JLabel("Station equipment:");

        StationEquipmentTableView dataTableStationEquipmentView = DBConnection.getTableStationEquipmentData();
        JTable tableStationEquipment = new JTable(dataTableStationEquipmentView);

        JScrollPane scrollPane2 = new JScrollPane(tableStationEquipment);
        scrollPane2.getViewportBorderBounds();
        scrollPane2.setColumnHeaderView(tableStationEquipment.getTableHeader());

        //GRID
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,10,10,10);
        c.fill = GridBagConstraints.BOTH;
        add(labelChooseRoom, c);

        c.gridx = 0;
        c.gridy = 1;
        add(tableRooms, c);

        c.gridx = 0;
        c.gridy = 2;
        add(chooseStation, c);

        c.gridx = 0;
        c.gridy = 3;
        add(scrollPane1, c);

        c.gridx = 1;
        c.gridy = 0;
        add(stationEquipment, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 2;
        add(scrollPane2,c);


    }

}
