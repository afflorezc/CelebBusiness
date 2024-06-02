package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.afflorezc.model.BankTransfer;

public class BankTransferDAO {

    // SQL Queries para hacer el CRUD (Create, Read, Update, Delete)
    private static final String INSERT_TRANSFER = "INSERT INTO bank_transfer (emiterAccount," +
                                                                        "receptorAccount," +
                                                                        "transferAmount," +
                                                                        "emiterInitialBalance," +
                                                                        "emiterFinalalance," +
                                                                        "receptorInitialBalance," +
                                                                        "receptorFinalBalance," +
                                                                        "transferDate) " +
                                                                        "VALUES" + 
                                                                        "(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_TRANSFER_BY_ID = "SELECT * FROM bank_transfer WHERE transferID = ?";
    private static final String SELECT_ALL_TRANSFERS = "SELECT * FROM bank_transfer";
    private static final String SELECT_TRANSFER_BY_EMITER = "SELECT * FROM bank_transfer WHERE emiterAccount =?";
    private static final String SELECT_TRANSFER_BY_RECPETOR = "SELECT * FROM bank_transfer WHERE receptorAccount =?";

    public void insertBankTransfer(BankTransfer transfer){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSFER)) {
            preparedStatement.setInt(1, transfer.getEmiterAccount());
            preparedStatement.setInt(2, transfer.getReceptorAccount());
            preparedStatement.setDouble(3, transfer.getTransferAmount());
            preparedStatement.setDouble(4, transfer.getEmiterInitialBalance());
            preparedStatement.setDouble(5, transfer.getEmiterFinalBalance());
            preparedStatement.setDouble(6, transfer.getReceptorInitialBalance());
            preparedStatement.setDouble(7, transfer.getReceptorFinalBalance());
            preparedStatement.setTimestamp(8, transfer.getTransferDate());
            
            preparedStatement.executeUpdate();
            System.out.println("Transferencia registrada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al registrar transferencia: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

    public BankTransfer selectTransferByID(int transferID){

        BankTransfer transfer = null;
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSFER_BY_ID)) {
            preparedStatement.setInt(1, transferID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                transfer = new BankTransfer();
                transfer.setTransferID(resultSet.getInt("transactionID"));
                transfer.setEmiterAccount(resultSet.getInt("emiterAccount"));
                transfer.setReceptorAccount(resultSet.getInt("receptorAccount"));
                transfer.setTransferAmount(resultSet.getDouble("transferAmount"));
                transfer.setEmiterInitialBalance(resultSet.getDouble("emiterInitialBalance"));
                transfer.setEmiterFinalBalance(resultSet.getDouble("emiterfinalBalance"));
                transfer.setReceptorInitialBalance(resultSet.getDouble("receptorInitialBalance"));
                transfer.setReceptorFinalBalance(resultSet.getDouble("receptorfinalBalance"));
                transfer.setTransferDate(resultSet.getTimestamp("transferDate"));
                System.out.println("Transferencia encontrada exitosamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la transferencia: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
        return transfer;

    }

    public List<BankTransfer> selectAllTransferForEmiter(int emiterAccount){

        List<BankTransfer> transfers = new ArrayList<BankTransfer>();
        Connection connection = DBConnection.getConnection();

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSFER_BY_EMITER)){
                preparedStatement.setInt(1, emiterAccount);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    BankTransfer transfer = new BankTransfer();
                    transfer.setTransferID(resultSet.getInt("transactionID"));
                    transfer.setEmiterAccount(resultSet.getInt("emiterAccount"));
                    transfer.setReceptorAccount(resultSet.getInt("receptorAccount"));
                    transfer.setTransferAmount(resultSet.getDouble("transferAmount"));
                    transfer.setEmiterInitialBalance(resultSet.getDouble("emiterInitialBalance"));
                    transfer.setEmiterFinalBalance(resultSet.getDouble("emiterfinalBalance"));
                    transfer.setReceptorInitialBalance(resultSet.getDouble("receptorInitialBalance"));
                    transfer.setReceptorFinalBalance(resultSet.getDouble("receptorfinalBalance"));
                    transfer.setTransferDate(resultSet.getTimestamp("transferDate"));
                    transfers.add(transfer);
                }
                System.out.println("Transferencias encontradas exitosamente");
       } catch(SQLException e){
           System.out.println("Error al buscar transferencias: "+e.getMessage());
       }

       DBConnection.closeConnection(connection);
       return transfers;
    }

    public List<BankTransfer> selectAllTransferForReceptor(int receptorAccount){

        List<BankTransfer> transfers = new ArrayList<BankTransfer>();
        Connection connection = DBConnection.getConnection();

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSFER_BY_RECPETOR)){
                preparedStatement.setInt(1, receptorAccount);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    BankTransfer transfer = new BankTransfer();
                    transfer.setTransferID(resultSet.getInt("transactionID"));
                    transfer.setEmiterAccount(resultSet.getInt("emiterAccount"));
                    transfer.setReceptorAccount(resultSet.getInt("receptorAccount"));
                    transfer.setTransferAmount(resultSet.getDouble("transferAmount"));
                    transfer.setEmiterInitialBalance(resultSet.getDouble("emiterInitialBalance"));
                    transfer.setEmiterFinalBalance(resultSet.getDouble("emiterfinalBalance"));
                    transfer.setReceptorInitialBalance(resultSet.getDouble("receptorInitialBalance"));
                    transfer.setReceptorFinalBalance(resultSet.getDouble("receptorfinalBalance"));
                    transfer.setTransferDate(resultSet.getTimestamp("transferDate"));
                    transfers.add(transfer);
                }
                System.out.println("Transferencias encontradas exitosamente");
       } catch(SQLException e){
           System.out.println("Error al buscar transferencias: "+e.getMessage());
       }

       DBConnection.closeConnection(connection);
       return transfers;
    }

    public List<BankTransfer> selectAllTransfers(){

        List<BankTransfer> transfers = new ArrayList<BankTransfer>();
        Connection connection = DBConnection.getConnection();

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRANSFERS)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    BankTransfer transfer = new BankTransfer();
                    transfer.setTransferID(resultSet.getInt("transactionID"));
                    transfer.setEmiterAccount(resultSet.getInt("emiterAccount"));
                    transfer.setReceptorAccount(resultSet.getInt("receptorAccount"));
                    transfer.setTransferAmount(resultSet.getDouble("transferAmount"));
                    transfer.setEmiterInitialBalance(resultSet.getDouble("emiterInitialBalance"));
                    transfer.setEmiterFinalBalance(resultSet.getDouble("emiterfinalBalance"));
                    transfer.setReceptorInitialBalance(resultSet.getDouble("receptorInitialBalance"));
                    transfer.setReceptorFinalBalance(resultSet.getDouble("receptorfinalBalance"));
                    transfer.setTransferDate(resultSet.getTimestamp("transferDate"));
                    transfers.add(transfer);
                }
                System.out.println("Transferencias encontradas exitosamente");
       } catch(SQLException e){
           System.out.println("Error al buscar transferencias: "+e.getMessage());
       }

       DBConnection.closeConnection(connection);
       return transfers;
    }

}
