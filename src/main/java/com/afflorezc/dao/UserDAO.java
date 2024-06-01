package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.afflorezc.model.User;

public class UserDAO {

    // SQL Queries para hacer el CRUD (Create, Read, Update, Delete)
    private static final String INSERT_USER_CLIENT = "INSERT INTO user (userName," +
                                                                        "password," +
                                                                        "isCelebrity," +
                                                                        "hasBankAccount," +
                                                                        "personID," +
                                                                        "userType) " +
                                                                        "VALUES" + 
                                                                        "(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_CLIENT_ID = "SELECT * FROM user WHERE clientID = ?";
    private static final String SELECT_USER_USERNAME = "SELECT * FROM user WHERE userName = ?";
    private static final String SELECT_ALL = "SELECT * FROM user";
    private static final String DELETE_USER = "DELETE FROM user WHERE clientID = ?";
    private static final String UPDATE_USER = "UPDATE user SET password = ?," +
                                                                   "hasBankAccount = ?" +
                                                                   "WHERE clientID = ?";
    
    public void insertUser(User newUser){
        
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_CLIENT)) {
            preparedStatement.setString(1, newUser.getUserName());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setBoolean(3, newUser.isCelebrity());
            preparedStatement.setBoolean(4, newUser.hasBankAccount());
            preparedStatement.setInt(5, newUser.getPersonID());
            preparedStatement.setString(6, newUser.getUserType());
            
            preparedStatement.executeUpdate();
            System.out.println("Usuario insertado exitosamente a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar un usuario: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

    public void updateUser(int clientID, String password, boolean hasAccount){
        
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            preparedStatement.setString(1, password);
            preparedStatement.setBoolean(2, hasAccount);
            preparedStatement.setInt(3, clientID);
            
            preparedStatement.executeUpdate();
            System.out.println("Usuario actualizado exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

    public User selectUserByID(int clientID){
        
        User user = null;
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_CLIENT_ID)) {
            preparedStatement.setInt(1, clientID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setClientID(resultSet.getInt("clientID"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setCelebrity(resultSet.getBoolean("isCelebrity"));
                user.setHasBankAccount(resultSet.getBoolean("hasBankAccount"));
                user.setPersonID(resultSet.getInt("personID"));
                user.setUserType(resultSet.getString("userType"));
            }
            System.out.println("Usurario encontrado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al buscar al usuario: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
        return user;
    }

    public boolean userNameInUse(String username){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                DBConnection.closeConnection(connection);
                System.out.println("Usurario encontrado exitosamente, username en uso");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar al usuario: " + e.getMessage());
            DBConnection.closeConnection(connection);  
            return true;
        }
        DBConnection.closeConnection(connection);
        return false;
        
    }

    public User selectUserByUserName(String username){

        User user = null;
        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setClientID(resultSet.getInt("clientID"));
                user.setUserName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setCelebrity(resultSet.getBoolean("isCelebrity"));
                user.setHasBankAccount(resultSet.getBoolean("hasBankAccount"));
                user.setPersonID(resultSet.getInt("personID"));
                user.setUserType(resultSet.getString("userType"));
                System.out.println("Usurario encontrado exitosamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar al usuario: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
        return user;
    }

    public void deleteUser(int clientID){

        Connection connection = DBConnection.getConnection();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){

               preparedStatement.execute();
       } catch(SQLException e){
           System.out.println("Error al eliminar usuario con id: "+clientID);
       }

       DBConnection.closeConnection(connection);
    }

    public List<User> selectAllClients(){

        List<User> users = new ArrayList<User>();
        Connection connection = DBConnection.getConnection();

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    User user = new User();
                    user.setClientID(resultSet.getInt("clientID"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setPassword(resultSet.getString("password"));
                    user.setCelebrity(resultSet.getBoolean("isCelebrity"));
                    user.setHasBankAccount(resultSet.getBoolean("hasBankAccount"));
                    user.setPersonID(resultSet.getInt("personID"));
                    user.setUserType(resultSet.getString("userType"));
                    users.add(user);
                }
                System.out.println("lista de usuarios encontrados exitosamente");
       } catch(SQLException e){
           System.out.println("Error al leer la base de datos: ");
       }

       DBConnection.closeConnection(connection);
       return users;
    }

}
