package app;

import java.io.*;
import java.sql.*;


public class DBConnection {


    public static RoomTableView getTableRoomData(){

        RoomTableView dataRoom = new RoomTableView();

        try {
            Connection conn = DriverManager.getConnection(getURL());
            String sqlStatement = "SELECT \"ID_Sali\", \"Numer_Sali\" \n" +
                    "FROM public.\"Sala\"\n" ;

            PreparedStatement statement = conn.prepareStatement(sqlStatement);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                dataRoom.addRoomData(rs.getInt(1), rs.getInt(2));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataRoom;
    }
//
public static StationTableView getTableStationData(){

    StationTableView dataStation = new StationTableView();

    try {
        Connection conn = DriverManager.getConnection(getURL());

        String sqlStatement = "SELECT \"ID_Stanowiska\", \"Opis_Stanowiska\", \"ID_Sali\", \"Stan_Stanowiska\"\n" +
                "\tFROM public.\"Stanowisko\"\n" +
                "    WHERE \"ID_Sali\" = '1';"; // TU MUSISZ DODAć ZMEINNAA KTORA MI USTAWI TEGO SELECTA

        PreparedStatement statement = conn.prepareStatement(sqlStatement);
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            dataStation.addStationData(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }

        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return dataStation;
}
//
public static StationEquipmentTableView getTableStationEquipmentData(){

    StationEquipmentTableView dataStationEquipment = new StationEquipmentTableView();

    try {
        Connection conn = DriverManager.getConnection(getURL());
        String sqlStatement = "SELECT \n" +
                "\"Pozycja_Stanowiska\".\"ID_Stanowiska\", \n" +
                "\"Pozycja_Stanowiska\".\"ID_Sprzetu\", \n" +
                "\"Sprzet\".\"Nazwa_Sprzetu\",\n" +
                "\"Pozycja_Stanowiska\".\"Ilosc_Sprzetu\", \n" +
                "\"Pozycja_Stanowiska\".\"Sprzet_Kluczowy\", \n" +
                "\"Pozycja_Stanowiska\".\"Sprzet_Dzialajacy\"\n" +
                "\n" +
                "\tFROM public.\"Pozycja_Stanowiska\", public.\"Sprzet\"\n" +
                "    WHERE \"Sprzet\".\"ID_Sprzetu\" = \"Pozycja_Stanowiska\".\"ID_Sprzetu\";" ;

        PreparedStatement statement = conn.prepareStatement(sqlStatement);
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            dataStationEquipment.addStationEquipmentData(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getBoolean(6));
        }

        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return dataStationEquipment;
}



    public static String getURL(){

        String urls = "";

        try {
            File file = new File("konfiguracja/test.txt");
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls;
    }



}
