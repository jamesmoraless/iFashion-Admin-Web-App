package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CatalogAdapter
{
    Connection connection;

    //Creates the table
    public CatalogAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;

        if(reset){
            Statement stmt = connection.createStatement();
            try {
                //Since Products references Catalogs, Products must be dropped first
                stmt.execute("DROP TABLE Products");
                stmt.execute("DROP TABLE Catalogs");
            } catch(SQLException ex){} finally {
                stmt.execute("CREATE TABLE Catalogs ("
                        + "UnitNo INT NOT NULL PRIMARY KEY, "
                        + "Name CHAR(15) "
                        + ")");
            }
        }
    }

    //Inserts a new Catalog
    public void insertCatalog(int num, String name) throws SQLException
    {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Catalogs (UnitNo, Name) "
                + "VALUES (" + num + " , '" + name +"')");
    }

    //Deletes a catalog from the table
    public void deleteCatalog(int id) throws SQLException
    {
        Statement stmt = connection.createStatement();
        String sqlStatement = "DELETE FROM Catalogs WHERE UnitNo = "+id;
        if (isEmpty(id))
        {
            stmt.executeUpdate(sqlStatement);
        }
        else
        {
            return;
        }
        //stmt.executeUpdate(sqlStatement);
    }

    //CHECK BELOW I am not quite sure how to implement a method to check whether the catalog is empty or not, but I think this is right
    public boolean isEmpty(int id)throws SQLException
    {
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Products WHERE CatalogId = "+id;
        ResultSet resultSet = stmt.executeQuery(sqlStatement);
        return !resultSet.next();
    }


    //Updates the Catalog
    public void updateCatalog(int id, String newName) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "UPDATE Catalogs "
                +"SET Name = '"+newName+"' " +"WHERE UnitNo = "+id;
        stmt.executeUpdate(sqlStatement);
    }

    //Returns a list that is used to populate the combo boxes for multiple scenes
    public ObservableList<String> getCatalogNameList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Catalogs";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        while(rs.next()){
            int id = rs.getInt("UnitNo");
            String region = rs.getString("Name");
            String category = id+" - "+region;
            list.add(category);
        }
        return list;
    }

    //Returns the catalog  that can then be 'looked up' to populate fields for the edit/delete windows
    public Catalog lookUpCategory(int id) throws SQLException {

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Catalogs";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        Catalog catalog = null;

        while(rs.next()){
            int rsId = rs.getInt("UnitNo");
            if(rsId == id){
                catalog = new Catalog(id,rs.getString("Name"));
                break;
            }
        }
        return catalog;
    }

    //Returns the list that is used to populate the combo box for the edit delete author
    public ObservableList<Catalog> getCatalogList() throws SQLException {
        ObservableList<Catalog> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Catalogs";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        while(rs.next()){
            list.add(new Catalog(rs.getInt("UnitNo"),
                    rs.getString("Name")
            ));
        }
        return list;
    }

    //Gets the max that is then used to set the id
    public int getMax() throws SQLException {
        int num = 0;

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT MAX(UnitNo) from Catalogs";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        while (rs.next()){
            num = rs.getInt(1) + 1;
        }
        return num;
    }



}
