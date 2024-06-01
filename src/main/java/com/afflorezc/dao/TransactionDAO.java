package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.afflorezc.model.Transaction;

public class TransactionDAO {

    // SQL Queries para hacer el CRUD (Create, Read, Update, Delete)
    private static final String INSERT_TRANSACTION = "INSERT INTO transaction (bankAccount," +
                                                                        "transactionDate," +
                                                                        "transactionAmount," +
                                                                        "initialBalance," +
                                                                        "finalBalance," +
                                                                        "transactionType) " +
                                                                        "VALUES" + 
                                                                        "(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_TRANSACTION_BY_ID = "SELECT * FROM transaction WHERE transactionID = ?";
    private static final String SELECT_ALL = "SELECT * FROM transaction WHERE bankAccount = ?";

    public void insertTransaction(Transaction transaction){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION)) {
            preparedStatement.setInt(1, transaction.getBankAccount());
            preparedStatement.setTimestamp(2, transaction.getTransactionDate());
            preparedStatement.setDouble(3, transaction.getTransactionAmount());
            preparedStatement.setDouble(4, transaction.getInitialBalance());
            preparedStatement.setDouble(5, transaction.getFinalBalance());
            preparedStatement.setString(6, transaction.getTransactionType());
            
            preparedStatement.executeUpdate();
            System.out.println("Transacción registrada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al insertar un usuario: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

    public Transaction selectTransactionByID(int transactionID){

        Transaction transaction = null;
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTION_BY_ID)) {
            preparedStatement.setInt(1, transactionID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                transaction = new Transaction();
                transaction.setTransactionID(resultSet.getInt("transactionID"));
                transaction.setTransactionDate(resultSet.getTimestamp("transactionDate"));
                transaction.setTransactionAmount(resultSet.getDouble("transactionAmount"));
                transaction.setInitialBalance(resultSet.getDouble("initialBalance"));
                transaction.setFinalBalance(resultSet.getDouble("finalBalance"));
                transaction.setBankAccount(resultSet.getInt("bankAccount"));
                transaction.setTransactionType(resultSet.getString("transactionType"));
            }
            System.out.println("Transacción encontrada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al buscar la transacción: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
        return transaction;

    }

    public List<Transaction> selectAllTransactions(int bankAccount){

        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = DBConnection.getConnection();

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Transaction transaction = new Transaction();
                    transaction.setTransactionID(resultSet.getInt("transactionID"));
                    transaction.setTransactionDate(resultSet.getTimestamp("transactionDate"));
                    transaction.setTransactionAmount(resultSet.getDouble("transactionAmount"));
                    transaction.setInitialBalance(resultSet.getDouble("initialBalance"));
                    transaction.setFinalBalance(resultSet.getDouble("finalBalance"));
                    transaction.setBankAccount(resultSet.getInt("bankAccount"));
                    transaction.setTransactionType(resultSet.getString("transactionType"));
                    transactions.add(transaction);
                }
       } catch(SQLException e){
           System.out.println("Error al leer la base de datos: ");
       }

       DBConnection.closeConnection(connection);
       return transactions;

    }

}
