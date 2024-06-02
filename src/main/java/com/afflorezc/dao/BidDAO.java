package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.afflorezc.model.Bid;

public class BidDAO {
    private static final String INSERT_BID = "INSET INTO bid (auctionID, offererID, bidValue, message) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BID_AUCTION = "SELECT * FROM bid WHERE auctionID = ? ";

    public void insertBid(Bid bid){

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BID); // ingresando la consulta sql

            preparedStatement.setInt(1, bid.getAuctionID()); // pasandole los datos a la consulta de sql
            preparedStatement.setInt(2, bid.getOffererID());
            preparedStatement.setDouble(3, bid.getBidValue());
            preparedStatement.setString(4, bid.getMessage());

            preparedStatement.executeUpdate(); // ejecutando la consulta sql

            System.out.println("oferta creada exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar oferta: " + e.getMessage());
        }

        DBConnection.closeConnection(connection); // cerrando la conexion de la base de datos
    }

    public List<Bid> selectBidsByAuction(int auctionID){

        List<Bid> bids = new ArrayList<Bid>(); //definiendo la lista que contendra las ofertas

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BID_AUCTION); // ingresando la consulta sql
            
            preparedStatement.setInt(1, auctionID); // pasandole el adminID a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            while(resultSet.next()){// obteniendo los datos de las ofertas

                int bidID = resultSet.getInt("bidID");
                int offererID = resultSet.getInt("offererID");
                double bidValue = resultSet.getDouble("bidValue");
                String message = resultSet.getString("message");

                Bid bid = new Bid(bidID, auctionID, offererID, bidValue, message);

                bids.add(bid);
            }
            
             
            } catch (SQLException e){
                System.out.println("Error al buscar las ofertas " + e.getMessage());
            }

        return bids;
    }


}
