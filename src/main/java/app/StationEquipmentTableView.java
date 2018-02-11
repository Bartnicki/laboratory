package app;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StationEquipmentTableView extends AbstractTableModel {

    private ArrayList<StationEquipmentDataModel> equipments = new ArrayList<StationEquipmentDataModel>();


    public int getRowCount() {
        return equipments.size();
    }

    public int getColumnCount() {
        return 4;
    }



    public Object getValueAt(int rowIndex, int columnIndex) {
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
        else return "Nie znaleziono kolumny";
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0) return "Item Name";
        if(column == 1) return "Item Status";
        if(column == 2) return "Item Required";
        if(column == 3) return "Quantity";
        else return "Nie znaleziono kolumny";
    }

    public void addStationEquipmentData(int stationID, int itemID, String itemName, int itemQuantity, boolean itemRequired, boolean itemQuality) {

        equipments.add(new StationEquipmentDataModel(stationID, itemID, itemName, itemQuantity, itemRequired, itemQuality));
    }
}
