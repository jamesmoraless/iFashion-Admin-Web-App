package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductAdapter
{
    Connection connection;

    //Creates the table
    public ProductAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;

        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                //No table references products only products needs to be dropped
                stmt.execute("DROP TABLE Products");
            } catch (SQLException ex) {} finally {
                stmt.execute("CREATE TABLE Products ("
                        + "ProductId INT NOT NULL PRIMARY KEY, "
                        + "Name CHAR(15), "
                        + "Description CHAR(50), "
                        + "ProductNo INT, "
                        + "Brand CHAR(15),"
                        + "ProductQty INT, "
                        + "CatalogId INT NOT NULL REFERENCES Catalogs (UnitNo) ON DELETE CASCADE"
                        + ")");
            }
        }
    }

    //Inserts a new product into the table
    public void insertProduct(int productId, String name, String description, int productNo, String brand, int productQty, int catalogId) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Products (ProductId, Name, Description, ProductNo, Brand, ProductQty, CatalogId)"
                +"VALUES (" + productId + ", '" + name + "' , '" + description + "', " + productNo + ", '" + brand + "', " +  productQty + " ,"+catalogId + ")");
    }

    //Deletes aa product from the table
    public void deleteProduct(int productId) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "DELETE FROM Products WHERE ProductId = "+ productId;
        stmt.executeUpdate(sqlStatement);
    }

    //Updates the product in the table
    public void updateProduct(int productId, String name, String description, int productNo,String brand, int productQty, int catalogId) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement =  "UPDATE Products "

                +" SET Name = '"+ name +"', Description = '"+ description +"', ProductNo = "+ productNo +", Brand = '"+ brand +"', ProductQty = " + productQty +", CatalogId = "+ catalogId +""
                +" WHERE ProductId = "+ productId;
        stmt.executeUpdate(sqlStatement);
    }

    //Returns the product that can then be looked up' to populate fields for the edit/delete windows
    public Product lookUpProduct(int productId) throws SQLException {
        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Products";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        Product product = null;

        while(rs.next()){
            int id = rs.getInt("ProductId");
            if(productId == id){
                product = new Product(productId,
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getInt("ProductNo"),
                        rs.getString("Brand"),
                        rs.getInt("ProductQty"),
                        rs.getInt("CatalogId"));
                break;
            }
        }
        return product;
    }

    //Returns the list that is used to populate the combo boxes
    public ObservableList<String> getProductList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Products";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        while(rs.next()){
            String prodNum = String.valueOf(rs.getInt("ProductId"));
            String name = rs.getString("Name");
            String desc = rs.getString("Description");
            String value = prodNum+" - "+name+" - "+desc;
            list.add(value);
        }
        return list;
    }

    //Gets the max that is then used to set the authors id
    public int getMax() throws SQLException {
        int num = 1;

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT MAX(ProductId) from Products";
        ResultSet rs = stmt.executeQuery(sqlStatement);

        while (rs.next()){
            num = rs.getInt(1)+1;
        }

        return num;
    }

}
