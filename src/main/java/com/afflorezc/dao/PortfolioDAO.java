package com.afflorezc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.afflorezc.model.Portfolio;

public class PortfolioDAO {

    // SQL Queries para hacer el CRUD (Create, Read, Update, Delete)
    private static final String INSERT_PORTFOLIO = "INSERT INTO portfolio (unitValue," +
                                                                        "commision," +
                                                                        "portfolioName," +
                                                                        "riskGrade. " +
                                                                        "minimumInvestment) "+
                                                                        "VALUES" + 
                                                                        "(?, ?, ?, ?, ?)";
    private static final String SELECT_PORTFOLIO_NUMBER = "SELECT * FROM portfolio WHERE portfolioID = ?";
    private static final String SELECT_ALL = "SELECT * FROM portfolio";
    private static final String DELETE_PORTFOLIO = "DELETE FROM portfolio WHERE portfolioID = ?";
    private static final String SELECT_PORTFOLIO_NUMBER_BY_NAME = "SELECT portfolioID FROM portfolio "+
                                                                   "WHERE portfolioName = ?"; 
    private static final String UPDATE_PORTFOLIO = "UPDATE portfolio SET unitValue = ?, " +
                                                                    "commision = ?," +
                                                                    "portfolioName = ?," +
                                                                    "riskGrade = ?," +
                                                                    "minimumInvestment = ?" +
                                                                    "WHERE" +
                                                                    "portfolioID = ?)";

    public void insertPortfolio(Portfolio portfolio){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PORTFOLIO)) {
            preparedStatement.setDouble(1, portfolio.getUnitValue());
            preparedStatement.setDouble(2, portfolio.getCommision());
            preparedStatement.setString(3, portfolio.getPortfolioName());
            preparedStatement.setInt(4, portfolio.getRiskGrade());
            preparedStatement.setDouble(5, portfolio.getMinimumInvestment());
            preparedStatement.executeUpdate();
            System.out.println("Portafolio creado exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al insertar portafolio: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

    public void updatePortfolio(int portfolioID, Portfolio portfolio){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PORTFOLIO)) {
            preparedStatement.setDouble(1, portfolio.getUnitValue());
            preparedStatement.setDouble(2, portfolio.getCommision());
            preparedStatement.setString(3, portfolio.getPortfolioName());
            preparedStatement.setInt(4, portfolio.getRiskGrade());
            preparedStatement.setDouble(5, portfolio.getMinimumInvestment());
            preparedStatement.setInt(5,portfolioID);
            preparedStatement.executeUpdate();
            System.out.println("Portafolio actualizado exitosamente en la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al actualizar portafolio: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }
    
    public void deletePortfolio(int portfolioID){

        Connection connection = DBConnection.getConnection();
        
        try (
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PORTFOLIO)) {
            preparedStatement.setInt(1, portfolioID);
            preparedStatement.execute();
            System.out.println("Portafolio eliminado");
        } catch (SQLException e) {
            System.out.println("Error al eliminar portafolio: " + e.getMessage());
        }

        DBConnection.closeConnection(connection);
    }

     public Portfolio selectPortfolioByNumber(int portfolioID){

        Portfolio portfolio = null;
        Connection connection = DBConnection.getConnection();

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PORTFOLIO_NUMBER)){
                preparedStatement.setInt(1, portfolioID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    portfolio = new Portfolio();
                    portfolio.setPortfolioID(portfolioID);
                    portfolio.setUnitValue(resultSet.getDouble("unitValue"));
                    portfolio.setCommision(resultSet.getDouble("commision"));
                    portfolio.setPortfolioName(resultSet.getString("portfolioName"));
                    portfolio.setRiskGrade(resultSet.getInt("riskGrade"));
                    System.out.println("Portafolio encontrado");
                }
            } catch (SQLException e){
                System.out.println("Error en busqueda de portafolio: " + e.getMessage());
            }
            DBConnection.closeConnection(connection);
            return portfolio;
    }

    public List<Portfolio> selectAllPortfolios(){

        List<Portfolio> portfolios = new ArrayList<Portfolio>();
        Connection connection = DBConnection.getConnection();

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    Portfolio portfolio = new Portfolio();
                    portfolio.setPortfolioID(resultSet.getInt("portfolioID"));
                    portfolio.setUnitValue(resultSet.getDouble("unitValue"));
                    portfolio.setCommision(resultSet.getDouble("commision"));
                    portfolio.setPortfolioName(resultSet.getString("portfolioName"));
                    portfolio.setRiskGrade(resultSet.getInt("riskGrade"));

                    portfolios.add(portfolio);
                    System.out.println("Portafolio encontrado");
                }
            } catch (SQLException e){
                System.out.println("Error en busqueda de portafolio: " + e.getMessage());
            }
            DBConnection.closeConnection(connection);
            return portfolios;
    }

    public double maxRequiredInvestment(List<Portfolio> portfolios){

        double max=0;
        
        for(Portfolio portfolio:portfolios){
            if(portfolio.getMinimumInvestment()>max){
                max = portfolio.getMinimumInvestment();
            }
        }
        return max;
    }

    public List<Double> minRequiredInvestment(List<Portfolio> portfolios){
        
        List<Double> minInvestmentList = new ArrayList<Double>();
        for(Portfolio portfolio:portfolios){
            minInvestmentList.add(portfolio.getMinimumInvestment());
        }
        return minInvestmentList;
    }

    public int getPortfolioID(String portfolioName){

        int portfolioID = -1;
        Connection connection = DBConnection.getConnection();

        try(
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PORTFOLIO_NUMBER_BY_NAME)){
                preparedStatement.setString(1, portfolioName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    portfolioID = resultSet.getInt("portfolioID");
                }
            } catch (SQLException e){
                System.out.println("Error en busqueda de portafolio: " + e.getMessage());
            }

            DBConnection.closeConnection(connection);
            return portfolioID;

    }
        
}
