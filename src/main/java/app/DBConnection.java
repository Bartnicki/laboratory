package app;


import app.exceptions.ItemQuantityException;
import app.exceptions.NoConnectionStringDetectedException;


import java.io.*;
import java.sql.*;


public class DBConnection {

    public static RoomTableView getTableRoomData() {

        RoomTableView dataRoom = new RoomTableView();

        try {

            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());
            } catch (NoConnectionStringDetectedException noConnectionStringDetectedException) {

                GUI.log.error("NO DATABASE STRING URL");

            }

            GUI.log.info("Successfully connected to database");

            String sqlStatement = "SELECT \"ID_Sali\", \"Numer_Sali\" \n" +
                    "FROM public.\"Sala\"\n";

            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                dataRoom.addRoomData(rs.getInt(1), rs.getInt(2));
            }

            conn.close();
            GUI.log.info("Connection with database closed");
        } catch (SQLException e) {
            GUI.log.error("SQL exception");

        }
        return dataRoom;
    }

    public static StationTableView getTableStationData(int selectedRoomNumber){

        StationTableView dataStation = new StationTableView();

        try {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());
            } catch (NoConnectionStringDetectedException noConnectionStringDetectedException) {
                GUI.log.error("NO DATABASE STRING URL");

            }
            GUI.log.info("Successfully connected to database");
            String sqlStatement = "SELECT \"ID_Stanowiska\", \"Opis_Stanowiska\", \"ID_Sali\", \"Stan_Stanowiska\"\n" +
                    "\tFROM public.\"Stanowisko\"\n" +
                    "    WHERE \"ID_Sali\" = ?;";

            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, selectedRoomNumber);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                dataStation.addStationData(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }

            conn.close();
            GUI.log.info("Connection with database closed");
        } catch (SQLException e) {
            GUI.log.error("SQL exception");
        }
        return dataStation;
    }

    public static StationEquipmentTableView getTableStationEquipmentData(int selectedStationNumber){

        StationEquipmentTableView dataStationEquipment = new StationEquipmentTableView();

        try {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());
            } catch (NoConnectionStringDetectedException noConnectionStringDetectedException) {
                GUI.log.error("NO DATABASE STRING URL");

            }
            String sqlStatement = "SELECT \n" +
                    "\"Pozycja_Stanowiska\".\"ID_Stanowiska\", \n" +
                    "\"Pozycja_Stanowiska\".\"ID_Sprzetu\", \n" +
                    "\"Sprzet\".\"Nazwa_Sprzetu\",\n" +
                    "\"Pozycja_Stanowiska\".\"Ilosc_Sprzetu\", \n" +
                    "\"Pozycja_Stanowiska\".\"Sprzet_Kluczowy\", \n" +
                    "\"Pozycja_Stanowiska\".\"Sprzet_Dzialajacy\"\n" +
                    "\n" +
                    "\tFROM public.\"Pozycja_Stanowiska\", public.\"Sprzet\"\n" +
                    "    WHERE \"Sprzet\".\"ID_Sprzetu\" = \"Pozycja_Stanowiska\".\"ID_Sprzetu\" AND \"ID_Stanowiska\" = ?;" ;


            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            statement.setInt(1, selectedStationNumber);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                dataStationEquipment.addStationEquipmentData(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getBoolean(6));
            }

            conn.close();
            GUI.log.info("Connection with database closed");
        } catch (SQLException e) {
            GUI.log.error("SQL exception");
        }
        return dataStationEquipment;
    }

    public static StationEquipmentTableView increaseQuantityData(int selectedItemQuantity, int selectedStationID, int selectedItemID){

        StationEquipmentTableView increaseUpdateDataEquipment = new StationEquipmentTableView();
        try {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());
            } catch (NoConnectionStringDetectedException noConnectionStringDetectedException) {
                GUI.log.error("NO DATABASE STRING URL");

            }
            String sqlStatement = "UPDATE public.\"Pozycja_Stanowiska\"\n" +
                    "\tSET \"Ilosc_Sprzetu\"=?\n" +
                    "\tWHERE \"ID_Stanowiska\"=? AND \"ID_Sprzetu\"=?;";


            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            selectedItemQuantity = selectedItemQuantity + 1;
            statement.setInt(1, selectedItemQuantity);
            statement.setInt(2, selectedStationID);
            statement.setInt(3, selectedItemID);
            statement.executeUpdate();

            conn.close();
            GUI.log.info("Connection with database closed");
        } catch (SQLException e) {
            GUI.log.error("SQL exception");
        }
        increaseUpdateDataEquipment = getTableStationEquipmentData(selectedStationID);
        return increaseUpdateDataEquipment;
    }

    public static StationEquipmentTableView decreaseQuantityData(int selectedItemQuantity, int selectedStationID, int selectedItemID) throws ItemQuantityException {

        if(selectedItemQuantity<=0) throw new ItemQuantityException();

        StationEquipmentTableView decreaseUpdateDataEquipment = new StationEquipmentTableView();
        try {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());
            } catch (NoConnectionStringDetectedException noConnectionStringDetectedException) {
                GUI.log.error("NO DATABASE STRING URL");

            }

            String sqlStatement = "UPDATE public.\"Pozycja_Stanowiska\"\n" +
                    "\tSET \"Ilosc_Sprzetu\"=?\n" +
                    "\tWHERE \"ID_Stanowiska\"=? AND \"ID_Sprzetu\"=?;";

            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            selectedItemQuantity = selectedItemQuantity - 1;
            statement.setInt(1, selectedItemQuantity);
            statement.setInt(2, selectedStationID);
            statement.setInt(3, selectedItemID);
            statement.executeUpdate();

            conn.close();
            GUI.log.info("Connection with database closed");
        } catch (SQLException e) {
            GUI.log.error("SQL exception");
        }
        decreaseUpdateDataEquipment = getTableStationEquipmentData(selectedStationID);
        return decreaseUpdateDataEquipment;
    }

    public static StationEquipmentTableView changeItemStatus(int selectedStationID, int selectedItemID, String selectedItemStatus){

        StationEquipmentTableView changeItemStatus = new StationEquipmentTableView();
        boolean newItemStatus;
        try {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());
            } catch (NoConnectionStringDetectedException noConnectionStringDetectedException) {
                GUI.log.error("NO DATABASE STRING URL");

            }

            if(selectedItemStatus == "Working"){
                newItemStatus = false;
            }
            else{
                newItemStatus = true;
            }



            String sqlStatement = "UPDATE public.\"Pozycja_Stanowiska\"\n" +
                    "\tSET \"Sprzet_Dzialajacy\"=?\n" +
                    "\tWHERE \"ID_Stanowiska\"=? AND \"ID_Sprzetu\"=?;";

            PreparedStatement statement = conn.prepareStatement(sqlStatement);

            statement.setBoolean(1, newItemStatus);
            statement.setInt(2, selectedStationID);
            statement.setInt(3, selectedItemID);
            statement.executeUpdate();

            conn.close();
            GUI.log.info("Connection with database closed");
        } catch (SQLException e) {
            GUI.log.error("SQL exception");
        }
        changeItemStatus = getTableStationEquipmentData(selectedStationID);
        return changeItemStatus;
    }

    public static StationEquipmentTableView changeItemRequirement(int selectedStationID, int selectedItemID, String selectedItemRequirement){

        StationEquipmentTableView changeItemRequirement = new StationEquipmentTableView();
        boolean newItemRequirement;
        try {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(getURL());
            } catch (NoConnectionStringDetectedException noConnectionStringDetectedException) {
                GUI.log.error("NO DATABASE STRING URL");

            }

            if(selectedItemRequirement == "Required"){
                newItemRequirement = false;
            }
            else{
                newItemRequirement = true;
            }

            String sqlStatement = "UPDATE public.\"Pozycja_Stanowiska\"\n" +
                    "\tSET \"Sprzet_Kluczowy\"=?\n" +
                    "\tWHERE \"ID_Stanowiska\"=? AND \"ID_Sprzetu\"=?;";

            PreparedStatement statement = conn.prepareStatement(sqlStatement);

            statement.setBoolean(1, newItemRequirement);
            statement.setInt(2, selectedStationID);
            statement.setInt(3, selectedItemID);
            statement.executeUpdate();

            conn.close();
            GUI.log.info("Connection with database closed");
        } catch (SQLException e) {
            GUI.log.error("SQL exception");
        }
        changeItemRequirement = getTableStationEquipmentData(selectedStationID);
        return changeItemRequirement;
    }


    public static String getURL() throws NoConnectionStringDetectedException {

        String urls = "";

        try {
            File file = new File("data/test.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;


            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            fileReader.close();
            String url=stringBuffer.toString();

            urls = url.substring(0,(url.length()-1));

            if(urls.equals("")) throw new NoConnectionStringDetectedException();

        } catch (FileNotFoundException e) {
            GUI.log.error("file not found!");
        } catch (IOException e) {
            GUI.log.error("io exception!");
        }

        return urls;
    }

}
