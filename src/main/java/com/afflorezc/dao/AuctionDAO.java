package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.afflorezc.model.Auction;

import java.util.ArrayList;
import java.util.List;

public class AuctionDAO {
    private static final String INSERT_AUCTION = "INSERT INTO auction (auctionDate, offeredProperties, sales, adminID) VALUES ( ?, ?, ?, ? )";
    private static final String SELECT_ALL_AUCTION = "SELECT * FROM auction";
    private static final String SELECT_AUCTION_ADMIN = "SELECT * FROM auction WHERE adminID = ?";
    private static final String SELECT_AUCTION_ID = "SELECT * FROM auction WHERE auctionID = ?";

    public void insertAuction(Auction auction){

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUCTION); // ingresando la consulta sql

            preparedStatement.setDate(1, auction.getAuctionDate()); // pasandole los datos a la consulta de sql
            preparedStatement.setInt(2, auction.getOfferedProperties());
            preparedStatement.setInt(3, auction.getSales());
            preparedStatement.setInt(4, auction.getAdminID());

            preparedStatement.executeUpdate(); // ejecutando la consulta sql

            System.out.println("subasta creada exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar subasta: " + e.getMessage());
        }

        DBConnection.closeConnection(connection); // cerrando la conexion de la base de datos
    }

    public List<Auction> selectAllAuction(){

        List<Auction> auctions = new ArrayList<Auction>(); // definiendo la lista que contendra las subastas

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUCTION); // ingresando la consulta sql
            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta de sql

            while(resultSet.next()){ //obteniendo todos los datos de las subastas
                int auctionID = resultSet.getInt("auctionID");
                Date auctionDAte = resultSet.getDate("auctionDAte");
                int offeredProperties = resultSet.getInt("offeredProperties");
                int sales = resultSet.getInt("sales");
                int adminID = resultSet.getInt("adminID");

                Auction auction = new Auction(auctionID, auctionDAte, offeredProperties, sales, adminID);

                auctions.add(auction);
            }
            
            } catch (SQLException e){
                System.out.println("Error al buscar las subastas " + e.getMessage());
            }

        return auctions;
    }

    
    public Auction selectAuctionByID(int id){

        Auction auction = new Auction(); //definiendo el objeto que contendra la subasta

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUCTION_ID); // ingresando la consulta sql
            
            preparedStatement.setInt(1, id); // pasandole los datos a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            if(resultSet.next()){ // obteniendo los datos de la subasta si existe
                Date auctionDAte = resultSet.getDate("auctionDAte");
                int offeredProperties = resultSet.getInt("offeredProperties");
                int sales = resultSet.getInt("sales");

                auction.setAuctionID(id);
                auction.setAuctionDate(auctionDAte);
                auction.setOfferedProperties(offeredProperties);
                auction.setSales(sales);
                
            }
            
             
            } catch (SQLException e){
                System.out.println("Error al buscar las subastas " + e.getMessage());
            }

        return auction;
    }

    public List<Auction> selectAuctionsByAdmin(int adminID){

        List<Auction> auctions = new ArrayList<Auction>(); //definiendo la lista que contendra las subastas

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUCTION_ADMIN); // ingresando la consulta sql
            
            preparedStatement.setInt(1, adminID); // pasandole el adminID a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            while(resultSet.next()){// obteniendo los datos de las subastas
                int auctionID = resultSet.getInt("auctionID");
                Date auctionDAte = resultSet.getDate("auctionDAte");
                int offeredProperties = resultSet.getInt("offeredProperties");
                int sales = resultSet.getInt("sales");

                Auction auction = new Auction(auctionID, auctionDAte, offeredProperties, sales, adminID);

                auctions.add(auction);
            }
            
             
            } catch (SQLException e){
                System.out.println("Error al buscar las subastas " + e.getMessage());
            }

        return auctions;
    }

}
