package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.afflorezc.model.SaleContract;

public class SaleContractDAO {
    private static final String INSERT_SALE_CONTRACT = "INSERT INTO sale_contract (propertyID, sellPrice, sellerID, buyerID, contractDate, paymentDate, auctionNumber, contractState, fulfillmentDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SALE_CONTRACT_ID = "SELECT * FROM sale_contract WHERE contractID = ?";
    private static final String SELECT_SALE_CONTRACT_USER = "SELECT * FROM sale_contract WHERE sellerID = ? OR buyerID = ?";

    public void insertAuction(SaleContract saleContract){

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SALE_CONTRACT); // ingresando la consulta sql

            preparedStatement.setInt(1, saleContract.getPropertyID()); // pasandole los datos a la consulta de sql
            preparedStatement.setDouble(2, saleContract.getSellPrice());
            preparedStatement.setInt(3, saleContract.getSellerID());
            preparedStatement.setInt(4, saleContract.getBuyerID());
            preparedStatement.setDate(5, saleContract.getContractDate());
            preparedStatement.setDate(6, saleContract.getPaymentDate());
            preparedStatement.setInt(7, saleContract.getAuctionNumber());
            preparedStatement.setString(8, saleContract.getContractState());
            preparedStatement.setDate(9, saleContract.getFulfillmentDate());

            preparedStatement.executeUpdate(); // ejecutando la consulta sql

            System.out.println("subasta creada exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar subasta: " + e.getMessage());
        }

        DBConnection.closeConnection(connection); // cerrando la conexion de la base de datos
    }

    public SaleContract selectSaleContractByID(int contractID){

        SaleContract saleContract = new SaleContract(); //definiendo el objeto que contendra el contrato

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SALE_CONTRACT_ID); // ingresando la consulta sql
            
            preparedStatement.setInt(1, contractID); // pasandole los datos a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            if(resultSet.next()){ // obteniendo los datos del contrato si existe

                int propertyID = resultSet.getInt("propertyID");
                double sellPrice = resultSet.getDouble("sellPrice");
                int sellerID = resultSet.getInt("sellerID");
                int buyerID = resultSet.getInt("buyerID");
                Date contractDate = resultSet.getDate("contractDate");
                Date paymentDate = resultSet.getDate("paymentDate");
                int auctionNumber = resultSet.getInt("auctionNumber");
                String contractState = resultSet.getString("contractState");
                Date fulfillmentDate = resultSet.getDate("fulfillmentDate");

                saleContract.setContractID(contractID);
                saleContract.setPropertyID(propertyID);
                saleContract.setSellPrice(sellPrice);
                saleContract.setSellerID(sellerID);
                saleContract.setBuyerID(buyerID);
                saleContract.setContractDate(contractDate);
                saleContract.setPaymentDate(paymentDate);
                saleContract.setAuctionNumber(auctionNumber);
                saleContract.setContractState(contractState);
                saleContract.setFulfillmentDate(fulfillmentDate);

            }

            } catch (SQLException e){
                System.out.println("Error al buscar las subastas " + e.getMessage());
            }

        return saleContract;
    }

    public List<SaleContract> selectSaleContractByUser(int userID){

        List<SaleContract> saleContracts = new ArrayList<>(); //definiendo el objeto que contendra los contrato

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SALE_CONTRACT_USER); // ingresando la consulta sql
            
            preparedStatement.setInt(1, userID); // pasandole los datos a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            while(resultSet.next()){ // obteniendo los datos del contrato si existe
                SaleContract saleContract = new SaleContract();

                int contractID = resultSet.getInt("contractID");
                int propertyID = resultSet.getInt("propertyID");
                double sellPrice = resultSet.getDouble("sellPrice");
                int sellerID = resultSet.getInt("sellerID");
                int buyerID = resultSet.getInt("buyerID");
                Date contractDate = resultSet.getDate("contractDate");
                Date paymentDate = resultSet.getDate("paymentDate");
                int auctionNumber = resultSet.getInt("auctionNumber");
                String contractState = resultSet.getString("contractState");
                Date fulfillmentDate = resultSet.getDate("fulfillmentDate");

                saleContract.setContractID(contractID);
                saleContract.setPropertyID(propertyID);
                saleContract.setSellPrice(sellPrice);
                saleContract.setSellerID(sellerID);
                saleContract.setBuyerID(buyerID);
                saleContract.setContractDate(contractDate);
                saleContract.setPaymentDate(paymentDate);
                saleContract.setAuctionNumber(auctionNumber);
                saleContract.setContractState(contractState);
                saleContract.setFulfillmentDate(fulfillmentDate);

                saleContracts.add(saleContract);
            }

            } catch (SQLException e){
                System.out.println("Error al buscar las subastas " + e.getMessage());
            }

        return saleContracts;
    }


}
