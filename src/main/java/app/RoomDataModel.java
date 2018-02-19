package app;

public class RoomDataModel {

    private int roomID;
    private int roomNumber;


    public RoomDataModel(int roomID, int roomNumber){

        this.roomID = roomID;
        this.roomNumber = roomNumber;

    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

}
