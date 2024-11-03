package DAOExample_Wine;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class WineryDAOImpl implements WineryDAO{
    // get one winery records
    @Override
    public Winery getOne(int id) throws SQLException{
        Connection DBconnection = DatabaseWine.getConnection();
        
        String sql = "SELECT * FROM winery WHERE winery_id = ?";
        PreparedStatement SQLstatement = DBconnection.prepareStatement(sql);
        SQLstatement.setInt(1,id);
       
        ResultSet result = SQLstatement.executeQuery();
        Winery oneWinery = null;
        while (result.next()){
            oneWinery = new Winery();
            oneWinery.setWineryID(result.getInt("winery_id"));
            oneWinery.setWineryName(result.getString("winery_name"));
            oneWinery.setViticulturalAreaID(result.getInt("viticultural_area_id"));
            oneWinery.setOfferingToursFlag(result.getInt("Offering_tours_flag"));
        }

        // use try-with-resources to close the connection,even if an exception is thrown
        try{
            DatabaseWine.closeStatement();
            DatabaseWine.closeStatement();
        }catch(SQLException e){
            System.out.println("Something went wrong when closing the connection" + e);
        }finally{
            DatabaseWine.closeStatement();
            DatabaseWine.closeStatement();
        }

        return oneWinery;
    }

    @Override
    public List<Winery> getAll() throws SQLException{
        Connection DBconnection = DatabaseWine.getConnection();

        String sql = "SELECT * FROM winery";
        PreparedStatement SQLstatement = DBconnection.prepareStatement(sql);
        
        ResultSet results = SQLstatement.executeQuery();
        List<Winery> allWineries = new ArrayList<>();
        Winery oneWinery = null;
        while (results.next()){
            oneWinery = new Winery();
            oneWinery.setWineryID(results.getInt("winery_id"));
            oneWinery.setWineryName(results.getString("wine"));
            oneWinery.setViticulturalAreaID(results.getInt("viticultural_area_id"));
            oneWinery.setOfferingToursFlag(results.getInt("Offering_tours_flag"));
            allWineries.add(oneWinery);
        }

        try{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }catch (SQLException e){
            System.out.println("Something went wrong when closing the connection" + e);
        }finally{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }

        return allWineries;
    }

    @Override
    public int insert(Winery winery) throws SQLException{
        Connection DBconnection = DatabaseWine.getConnection();

        String sql = "INSERT INTO winery () VALUES(?,?,?,?)";
        PreparedStatement SQLstatement = DBconnection.prepareStatement(sql);
        SQLstatement.setInt(1,winery.getWineryID());
        SQLstatement.setString(2,winery.getWineryName());
        SQLstatement.setInt(3,winery.getViticulturalAreaID());
        SQLstatement.setInt(4,winery.getOfferingToursFlag());

        int result = SQLstatement.executeUpdate();
      
        try{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }catch(SQLException e){
            System.out.println("Something went wrong when closing the connection" + e);
        }finally{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }

        return result;
    }
    
    @Override
    public int update(Winery winery) throws SQLException{
        Connection DBconnection = DatabaseWine.getConnection();
            
        String sql = "UPDATE winery SET winery_name = ?, viticultural_area_id = ?, Offering_tours_flag = ? WHERE winery_id = ?";
        PreparedStatement SQLstatement = DBconnection.prepareStatement(sql);
        SQLstatement.setString(1,winery.getWineryName());
        SQLstatement.setInt(2,winery.getViticulturalAreaID());
        SQLstatement.setInt(3,winery.getOfferingToursFlag());
        SQLstatement.setInt(4,winery.getWineryID());
    
        int result = SQLstatement.executeUpdate();
    
        try{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }catch(SQLException e){
            System.out.println("Something went wrong when closing the connection" + e);
        }finally{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }
    
        return result;
    }

    @Override
    public int delete(int id) throws SQLException{
        Connection DBconnection = DatabaseWine.getConnection();

        String sql = "DELETE FROM winery WHERE winery_id = ?";
        PreparedStatement SQlstatement = DBconnection.prepareStatement(sql);
        SQlstatement.setInt(1,id);

        int result = SQlstatement.executeUpdate();

        try{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }catch (SQLException e){
            System.out.println("Something went wrong when closing the connection" + e);
        }finally{
            DatabaseWine.closeConnection();
            DatabaseWine.closeStatement();
        }

        return result;
    }

}


