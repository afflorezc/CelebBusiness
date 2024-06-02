package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.afflorezc.model.Bill;

public class BillDAO {
    private static final String INSERT_BILL = "INSERT INTO bill (contractID, basePrice, taxes, comisions, fines, billDate) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BILL_CONTRACT = "SELECT * FROM bill WHERE contractID = ?";

    public void insertBill(Bill bill){

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BILL); // ingresando la consulta sql

            preparedStatement.setInt(1, bill.getContractID()); // pasandole los datos a la consulta de sql
            preparedStatement.setDouble(2, bill.getBasePrice());
            preparedStatement.setDouble(3, bill.getTaxes());
            preparedStatement.setDouble(4, bill.getComisions());
            preparedStatement.setDouble(4, bill.getFines());
            preparedStatement.setDate(4, bill.getBillDate());

            preparedStatement.executeUpdate(); // ejecutando la consulta sql

            System.out.println("factura creada exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar la factura: " + e.getMessage());
        }

        DBConnection.closeConnection(connection); // cerrando la conexion de la base de datos
    }

    public Bill selectBillByContract(int contractID){

        Bill bill = new Bill(); //definiendo el objeto que contendra la factura

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILL_CONTRACT); // ingresando la consulta sql
            
            preparedStatement.setInt(1, contractID); // pasandole los datos a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            if(resultSet.next()){ // obteniendo los datos de la factura si existe
                int billNumber = resultSet.getInt("billNumber");
                double basePrice = resultSet.getDouble("basePrice");
                double taxes = resultSet.getDouble("taxes");
                double comisions = resultSet.getDouble("comisions");
                double fines = resultSet.getDouble("fines");
                Date billDate = resultSet.getDate("billDate");

                bill.setBillNumber(billNumber);
                bill.setContractID(contractID);
                bill.setBasePrice(basePrice);
                bill.setTaxes(taxes);
                bill.setComisions(comisions);
                bill.setFines(fines);
                bill.setBillDate(billDate);
                
            }
              
            } catch (SQLException e){
                System.out.println("Error al buscar la factura " + e.getMessage());
            }

        return bill;
    }

}