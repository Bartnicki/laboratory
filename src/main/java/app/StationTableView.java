package app;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StationTableView extends AbstractTableModel {

    private ArrayList<StationDataModel> stations = new ArrayList<StationDataModel>();


    public int getRowCount() {
        return stations.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) return stations.get(rowIndex).getStationID();
        if(columnIndex == 1) return stations.get(rowIndex).getStationDescription();
        if(columnIndex == 2) {
            if (stations.get(rowIndex).getStationStatus() == 1) return "working";
            if (stations.get(rowIndex).getStationStatus() == 0) return "occupied";
            else return "broken";
        }
        else return "Nie znaleziono kolumny";
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0) return "Station ID";
        if(column == 1) return "Station Description";
        if(column == 2) return "Station Status";
        else return "Nie znaleziono kolumny";
    }

    public void addStationData(int stationID, String stationDescription, int roomID, int stationStatus) {

        stations.add(new StationDataModel(stationID, stationDescription, roomID, stationStatus));

    }
}
