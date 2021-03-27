import java.sql.*;
import java.util.*;

public class CheckInventory{
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private Connection dbConnect;
    private ResultSet results;

    public CheckInventory(String dburl, String username, String password){
        this.DBURL = dburl;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public void initializeConnection(){
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Furniture> selectMatchingFurniture(String category, String type){
        ArrayList<Furniture> matchingFurn = new ArrayList<Furniture>();
        try{
            String query = "SELECT * FROM "+category+" WHERE Type = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, type);
            results = myStmt.executeQuery();
            while(results.next()){
                Furniture newFurn;
                switch(category){
                    case "chair": 
                        newFurn = new Chair(results.getString("ID"),results.getString("Type"),results.getString("Legs"),results.getString("Arms"), results.getString("Seat"),results.getString("Cushion"), results.getInt("Price"),results.getString("ManuID"));
                        break;
                    case "desk": 
                        newFurn = new Desk(results.getString("ID"),results.getString("Type"),results.getString("Legs"),results.getString("Top"), results.getString("Drawer"), results.getInt("Price"),results.getString("ManuID"));
                        break;
                    case "filing": 
                        newFurn = new Filing(results.getString("ID"),results.getString("Type"),results.getString("Rails"),results.getString("Drawers"), results.getString("Cabinet"), results.getInt("Price"),results.getString("ManuID"));
                        break;
                    case "lamp": 
                        newFurn = new Lamp(results.getString("ID"),results.getString("Type"),results.getString("Base"),results.getString("Bulb"), results.getInt("Price"),results.getString("ManuID"));
                        break;
                    default: newFurn = null;
                }
                matchingFurn.add(newFurn);
            }
            myStmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        for(Furniture temp:matchingFurn){
            temp.print();
        }
        return matchingFurn;
    }
    public static void main(String[] args){
        CheckInventory myDB = new CheckInventory("jdbc:mysql://localhost/inventory","mathew","ensf409");
        myDB.initializeConnection();
        myDB.selectMatchingFurniture("desk", "Traditional");

    }
}