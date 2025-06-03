package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.Courier;
import util.DBPropertyUtil;

public class CourierServiceDb {
    private static Connection connection;

    public CourierServiceDb() {
    	connection = DBPropertyUtil.getDbConnection();
    }

    public boolean insertCourier(Courier courier) {
        boolean result = false;
        try {
            String query = "INSERT INTO Courier (CourierID, SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, courier.getCourierID());
            pstmt.setString(2, courier.getSenderName());
            pstmt.setString(3, courier.getSenderAddress());
            pstmt.setString(4, courier.getReceiverName());
            pstmt.setString(5, courier.getReceiverAddress());
            pstmt.setDouble(6, courier.getWeight());
            pstmt.setString(7, courier.getStatus());
            pstmt.setString(8, courier.getTrackingNumber());
            pstmt.setDate(9, new java.sql.Date(courier.getDeliveryDate().getTime()));

            int count = pstmt.executeUpdate();
            result = count > 0;
        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
        return result;
    }

    public boolean updateStatus(String trackingNumber, String status) {
        try {
            String query = "UPDATE Courier SET Status = ? WHERE TrackingNumber = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, status);
            pstmt.setString(2, trackingNumber);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
        return false;
    }

    public Courier getCourierByTrackingNumber(String trackingNumber) {
        Courier courier = null;
        try {
            String query = "SELECT * FROM Courier WHERE TrackingNumber = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, trackingNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                courier = new Courier(
                        rs.getInt("CourierID"),
                        rs.getString("SenderName"),
                        rs.getString("SenderAddress"),
                        rs.getString("ReceiverName"),
                        rs.getString("ReceiverAddress"),
                        rs.getDouble("Weight"),
                        rs.getString("Status"),
                        rs.getString("TrackingNumber"),
                        rs.getDate("DeliveryDate"),
                        0 // userId is not in DB schema but present in Courier POJO
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching courier: " + e.getMessage());
        }
        return courier;
    }

    public List<Courier> getDeliveryHistory() {
        List<Courier> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courier");

            while (rs.next()) {
                Courier courier = new Courier(
                        rs.getInt("CourierID"),
                        rs.getString("SenderName"),
                        rs.getString("SenderAddress"),
                        rs.getString("ReceiverName"),
                        rs.getString("ReceiverAddress"),
                        rs.getDouble("Weight"),
                        rs.getString("Status"),
                        rs.getString("TrackingNumber"),
                        rs.getDate("DeliveryDate"),
                        0
                );
                list.add(courier);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving history: " + e.getMessage());
        }
        return list;
    }
}