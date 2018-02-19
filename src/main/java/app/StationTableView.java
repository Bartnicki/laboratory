package app;

import app.exceptions.XMLException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StationTableView extends AbstractTableModel {

    private ArrayList<StationDataModel> stations = new ArrayList<StationDataModel>();
    XMLReader reader = new XMLReader();

    public int getRowCount() {
        return stations.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0) return stations.get(rowIndex).getStationID();
        if(columnIndex == 1) return stations.get(rowIndex).getStationDescription();
        else return "Cannot found column";
    }

    @Override
    public String getColumnName(int column) {
        try {
        if(column == 0)return reader.getWord("stationID");
        if(column == 1) return reader.getWord("stationDesc");
        else return "Cannot found column";
        } catch (XMLException e) {
            return "XMLerror";
        }

    }

    public void addStationData(int stationID, String stationDescription, int roomID, int stationStatus) {

        stations.add(new StationDataModel(stationID, stationDescription, roomID, stationStatus));

    }
}
