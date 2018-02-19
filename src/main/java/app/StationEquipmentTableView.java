package app;

import app.exceptions.XMLException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StationEquipmentTableView extends AbstractTableModel {

    private ArrayList<StationEquipmentDataModel> equipments = new ArrayList<StationEquipmentDataModel>();
    XMLReader reader = new XMLReader();

    public int getRowCount() {
        return equipments.size();
    }

    public int getColumnCount() {
        return 5;
    }



    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex>=equipments.size()) return null;
        if(columnIndex == 0) return equipments.get(rowIndex).getItemName();
        if(columnIndex == 1) {
            if(equipments.get(rowIndex).isItemQuality() == true) return "Working";
            else return "Broken";
        }
        if(columnIndex == 2) {
            if(equipments.get(rowIndex).isItemRequired() == true)return "Required";
            else return "Not Required";
        }
        if(columnIndex == 3) return equipments.get(rowIndex).getItemQuantity();
        //if(columnIndex == 4) {
            //if(equipments.get(rowIndex).isItemQuality()) return "";

        //}
        if(columnIndex == 4) return equipments.get(rowIndex).getItemID();
        else return "Nie znaleziono kolumny";
    }

    @Override
    public String getColumnName(int column) {
        try {
            if(column == 0) return reader.getWord("itemName");
            if(column == 1) return reader.getWord("itemStatus");
            if(column == 2) return reader.getWord("itemRequired");
            if(column == 3) return reader.getWord("quantity");
            if(column == 4) return reader.getWord("itemID");
            else return "Cannot found column";
        } catch (XMLException e) {
            return "XMLerror";
        }

    }

    public void addStationEquipmentData(int stationID, int itemID, String itemName, int itemQuantity, boolean itemRequired, boolean itemQuality) {

        equipments.add(new StationEquipmentDataModel(stationID, itemID, itemName, itemQuantity, itemRequired, itemQuality));
    }
}
