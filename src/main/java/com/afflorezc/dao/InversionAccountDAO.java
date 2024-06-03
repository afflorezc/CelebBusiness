package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.afflorezc.model.InversionAccount;

public class InversionAccountDAO {

    // SQL Queries para hacer el CRUD (Create, Read, Update, Delete)
    private static final String INSERT_INVERSION = "INSERT INTO inversion_account (inversionType," +
                                                                        "balance," +
                                                                        "openDate," +
                                                                        "dueDate," +
                                                                        "accumulatedProfit," +
                                                                        "chargedProfitOnTaxes," +
                                                                        "isEmbargoed," +
                                                                        "embargoedValue," +
                                                                        "portfolioID, " +
                                                                        "personID) " +
                                                                        "VALUES" + 
                                                                        "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_INVERSION_NUMBER = "SELECT * FROM inversion_account WHERE inversionNumber = ?";
    private static final String SELECT_INVERSION_USER_ID = "SELECT * FROM inversion_account WHERE personID = ?";
    private static final String SELECT_ALL = "SELECT * FROM inversion_account";
    private static final String DELETE_INVERSION = "DELETE FROM inversion_account WHERE inversionNumber = ?";
    private static final String UPDATE_INVERSION = "UPDATE inversion_account SET inversionType =?," +
                                                                                "balance =?," +
                                                                                "openDate =?," +
                                                                                "dueDate =?," +
                                                                                "accumulatedProfit =?," +
                                                                                "chargedProfitOnTaxes =?," +
                                                                                "isEmbargoed =?," +
                                                                                "embargoedValue =?," +
                                                                                "portfolioID =?, " +
                                                                                "WHERE inversionNumber = ?";

    public void insertInversion(InversionAccount account){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVERSION)) {
            preparedStatement.setString(1, account.getInversionType());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setDate(3, account.getOpenDate());
            preparedStatement.setDate(4, account.getDueDate());
            preparedStatement.setDouble(5, account.getAccumulatedProfit());
            preparedStatement.setDouble(6, account.getChargedProfitOnTaxes());
            preparedStatement.setBoolean(7, account.isEmbargoed());
            preparedStatement.setDouble(8, account.getEmbargoedValue());
            preparedStatement.setInt(9, account.getPortfolioID());
            preparedStatement.setInt(10, account.getPersonID());
            preparedStatement.executeUpdate();
            System.out.println("Cuenta de inversion creada exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar cuenta de inversion: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

    public void updateBalance(int accountNumber, InversionAccount account){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVERSION)) {
            preparedStatement.setString(1, account.getInversionType());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setDate(3, account.getOpenDate());
            preparedStatement.setDate(4, account.getDueDate());
            preparedStatement.setDouble(5, account.getAccumulatedProfit());
            preparedStatement.setDouble(6, account.getChargedProfitOnTaxes());
            preparedStatement.setBoolean(7, account.isEmbargoed());
            preparedStatement.setDouble(8, account.getEmbargoedValue());
            preparedStatement.setInt(9, account.getPortfolioID());
            preparedStatement.setInt(10, account.getPersonID());
            preparedStatement.executeUpdate();
            System.out.println("Actualización correcta de inversion");
        } catch (SQLException e) {
            System.out.println("Error al actualizar inversion: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

    public void deleteAccount(int inversionNumber){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVERSION)) {
            preparedStatement.setInt(1, inversionNumber);
            preparedStatement.execute();
            System.out.println("Cuenta de inversion eliminada");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cuenta de inversion: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);

    }

    public InversionAccount selectAccountByNumber(int inversionNumber){

        InversionAccount inversion = null;
        Connection connection = DBConnection.getConnection();

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVERSION_NUMBER)){
                preparedStatement.setInt(1, inversionNumber);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    inversion = new InversionAccount();
                    inversion.setInversionNumber(inversionNumber);
                    inversion.setPersonID(resultSet.getInt("personID"));
                    inversion.setPortfolioID(resultSet.getInt("portfolioID"));
                    inversion.setBalance(resultSet.getDouble("balance"));
                    inversion.setEmbargoed(resultSet.getBoolean("isEmbargoed"));
                    inversion.setAccumulatedProfit(resultSet.getDouble("accumulatedProfit"));
                    inversion.setEmbargoedValue(resultSet.getDouble("embargoedValue"));
                    inversion.setChargedProfitOnTaxes(resultSet.getDouble("chargedProfitOnTaxes"));
                    inversion.setOpenDate(resultSet.getDate("openDate"));
                    inversion.setDueDate(resultSet.getDate("dueDate"));
                    inversion.setInversionType(resultSet.getString("inversionType"));
                }
            } catch (SQLException e){
                System.out.println("Cuenta no encontrada: " + e.getMessage());
            }
            return inversion;
    }

    public List<InversionAccount> selectAccountByPersonID(int personID){

        List<InversionAccount> inversions = new ArrayList<InversionAccount>();
        Connection connection = DBConnection.getConnection();

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVERSION_USER_ID)){
                preparedStatement.setInt(1, personID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    InversionAccount inversion = new InversionAccount();
                    inversion.setInversionNumber(resultSet.getInt("inversionNumber"));
                    inversion.setPersonID(resultSet.getInt("personID"));
                    inversion.setPortfolioID(resultSet.getInt("portfolioID"));
                    inversion.setBalance(resultSet.getDouble("balance"));
                    inversion.setEmbargoed(resultSet.getBoolean("isEmbargoed"));
                    inversion.setAccumulatedProfit(resultSet.getDouble("accumulatedProfit"));
                    inversion.setEmbargoedValue(resultSet.getDouble("embargoedValue"));
                    inversion.setChargedProfitOnTaxes(resultSet.getDouble("chargedProfitOnTaxes"));
                    inversion.setOpenDate(resultSet.getDate("openDate"));
                    inversion.setDueDate(resultSet.getDate("dueDate"));
                    inversion.setInversionType(resultSet.getString("inversionType"));

                    inversions.add(inversion);
                }
            } catch (SQLException e){
                System.out.println("Ha habido un error o no existen cuentas de inversion para el usuario: " + e.getMessage());
            }
            return inversions;
    }

    public List<InversionAccount> selectAllAccounts(){

        List<InversionAccount> inversions = new ArrayList<InversionAccount>();
        Connection connection = DBConnection.getConnection();

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    InversionAccount inversion = new InversionAccount();
                    inversion.setInversionNumber(resultSet.getInt("inversionNumber"));
                    inversion.setPersonID(resultSet.getInt("personID"));
                    inversion.setPortfolioID(resultSet.getInt("portfolioID"));
                    inversion.setBalance(resultSet.getDouble("balance"));
                    inversion.setEmbargoed(resultSet.getBoolean("isEmbargoed"));
                    inversion.setAccumulatedProfit(resultSet.getDouble("accumulatedProfit"));
                    inversion.setEmbargoedValue(resultSet.getDouble("embargoedValue"));
                    inversion.setChargedProfitOnTaxes(resultSet.getDouble("chargedProfitOnTaxes"));
                    inversion.setOpenDate(resultSet.getDate("openDate"));
                    inversion.setDueDate(resultSet.getDate("dueDate"));
                    inversion.setInversionType(resultSet.getString("inversionType"));

                    inversions.add(inversion);
                }
            } catch (SQLException e){
                System.out.println("Hubo un error al leer la base de datos: " + e.getMessage());
            }
            return inversions;
    }
 
}

