package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.afflorezc.model.Property;

public class PropertyDAO {
    private static final String INSERT_PROPERTY = "INSERT INTO property (countryLocation, stateLocation, cityLocation, address, owner, onSale, valuation, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PROPERTY = "SELECT * FROM property";
    private static final String SELECT_PROPERTY_ID = "SELECT * FROM property WHERE propertyID = ?";
    private static final String SELECT_PROPERTY_USER = "SELECT * FROM property WHERE owner = ?";

    public void insertAuction(Property property){

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROPERTY); // ingresando la consulta sql

            preparedStatement.setString(1,property.getCountryLocation()); // pasandole los datos a la consulta de sql
            preparedStatement.setString(2, property.getStateLocation());
            preparedStatement.setString(3, property.getCityLocation());
            preparedStatement.setString(4, property.getAddress());
            preparedStatement.setInt(5, property.getOwner());
            preparedStatement.setBoolean(6, property.isOnSale());
            preparedStatement.setDouble(7, property.getValuation());
            preparedStatement.setString(8, property.getDescription());

            preparedStatement.executeUpdate(); // ejecutando la consulta sql

            System.out.println("propiedad creada exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar la propiedad: " + e.getMessage());
        }

        DBConnection.closeConnection(connection); // cerrando la conexion de la base de datos
    }

    public List<Property> selectAllProperties(){

        List<Property> properties = new ArrayList<Property>(); // definiendo la lista que contendra las propiedades

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROPERTY); // ingresando la consulta sql
            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta de sql

            while(resultSet.next()){ //obteniendo todos los datos de las propiedades
                int propertyID = resultSet.getInt("propertyID");
                String countryLocation = resultSet.getString("countryLocation");
                String stateLocation = resultSet.getString("stateLocation");
                String cityLocation = resultSet.getString("cityLocation");
                String address = resultSet.getString("address");
                int owner = resultSet.getInt("owner");
                boolean onSale = resultSet.getBoolean("onSale");
                double valuation = resultSet.getDouble("valuation");
                String description = resultSet.getString("description");


                Property property = new Property(propertyID, countryLocation, stateLocation, cityLocation, address, owner, onSale, valuation, description);

                properties.add(property);
            }
            
            } catch (SQLException e){
                System.out.println("Error al buscar las propiedades " + e.getMessage());
            }

        return properties;
    }

    public Property selectPropertyByID(int propertyID){

        Property property = new Property(); //definiendo el objeto que contendra la propiedad

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROPERTY_ID); // ingresando la consulta sql
            
            preparedStatement.setInt(1, propertyID); // pasandole los datos a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            if(resultSet.next()){ // obteniendo los datos de la propiedad si existe
                String countryLocation = resultSet.getString("countryLocation");
                String stateLocation = resultSet.getString("stateLocation");
                String cityLocation = resultSet.getString("cityLocation");
                String address = resultSet.getString("address");
                int owner = resultSet.getInt("owner");
                boolean onSale = resultSet.getBoolean("onSale");
                double valuation = resultSet.getDouble("valuation");
                String description = resultSet.getString("description");

                property.setPropertyID(propertyID);
                property.setCountryLocation(countryLocation);
                property.setStateLocation(stateLocation);
                property.setCityLocation(cityLocation);
                property.setAddress(address);
                property.setOwner(owner);
                property.setOnSale(onSale);
                property.setValuation(valuation);
                property.setDescription(description);
            }
            
             
            } catch (SQLException e){
                System.out.println("Error al buscar las subastas " + e.getMessage());
            }

        return property;
    }

    public List<Property> selectPropertyByOwner(int ownerID){

        List<Property> properties = new ArrayList<>(); //definiendo el objeto que contendra las propiedades

        Connection connection = DBConnection.getConnection(); // haciendo la conexion a la base de datos

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROPERTY_USER); // ingresando la consulta sql
            
            preparedStatement.setInt(1, ownerID); // pasandole los datos a la consulta sql

            ResultSet resultSet = preparedStatement.executeQuery(); // ejecutando la consulta sql

            while(resultSet.next()){ // obteniendo los datos de las propiedades si existe
                Property property = new Property();

                int propertyID = resultSet.getInt("propertyID");
                String countryLocation = resultSet.getString("countryLocation");
                String stateLocation = resultSet.getString("stateLocation");
                String cityLocation = resultSet.getString("cityLocation");
                String address = resultSet.getString("address");
                boolean onSale = resultSet.getBoolean("onSale");
                double valuation = resultSet.getDouble("valuation");
                String description = resultSet.getString("description");

                property.setPropertyID(propertyID);
                property.setCountryLocation(countryLocation);
                property.setStateLocation(stateLocation);
                property.setCityLocation(cityLocation);
                property.setAddress(address);
                property.setOwner(ownerID);
                property.setOnSale(onSale);
                property.setValuation(valuation);
                property.setDescription(description);

                properties.add(property);
                }
            } catch (SQLException e){
                System.out.println("Error al buscar las subastas " + e.getMessage());
            }

        return properties;
    }

}
