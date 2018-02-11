package app;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RoomTableView extends AbstractTableModel {

    private ArrayList<RoomDataModel> rooms = new ArrayList<RoomDataModel>();


    public int getRowCount() {
        return rooms.size();
    }

    public int getColumnCount() {
        return 1;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) return rooms.get(rowIndex).getRoomNumber();
        else return "Nie znaleziono kolumny o indeksie 0";
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0)
            return "Numer Sali";
        else return "Nie znaleziono kolumny";
    }

    public void addRoomData(int roomID, int roomNumber){

        rooms.add(new RoomDataModel(roomID, roomNumber));
    }


}
