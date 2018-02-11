package app;

public class StationDataModel {

    private int stationID;
    private String stationDescription;
    private int roomID;
    private int stationStatus;

    public StationDataModel(int stationID, String stationDescription, int roomID, int stationStatus){

        this.stationID = stationID;
        this.stationDescription = stationDescription;
        this.roomID = roomID;
        this.stationStatus = stationStatus;

    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationDescription() {
        return stationDescription;
    }

    public void setStationDescription(String stationDescription) {
        this.stationDescription = stationDescription;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }


    public int getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(int stationStatus) {
        this.stationStatus = stationStatus;
    }
}





