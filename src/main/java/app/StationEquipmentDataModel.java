package app;

public class StationEquipmentDataModel {

    private int stationID;
    private int itemID;
    private String itemName;
    private int itemQuantity;
    private boolean itemRequired;
    private boolean itemQuality;


    public StationEquipmentDataModel(int stationID, int itemID, String itemName, int itemQuantity, boolean itemRequired, boolean itemQuality){

        this.stationID = stationID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemRequired = itemRequired;
        this.itemQuality = itemQuality;

    }


    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }


    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public boolean isItemRequired() {
        return itemRequired;
    }

    public void setItemRequired(boolean itemRequired) {
        this.itemRequired = itemRequired;
    }

    public boolean isItemQuality() {
        return itemQuality;
    }

    public void setItemQuality(boolean itemQuality) {
        this.itemQuality = itemQuality;
    }
}
