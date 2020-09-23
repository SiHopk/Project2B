package databasetest;

import java.sql.*;
import org.json.simple.*;
import java.util.*;

public class DatabaseTest {

    public static void main(String[] args) {
        getJSONData();
    }
    
    public static JSONArray getJSONData(){
        Connection conn = null;
        PreparedStatement pstSelect = null, pstUpdate = null;
        ResultSet resultset = null;
        JSONArray data = new JSONArray();
        JSONObject x = new JSONObject();

        try 
        {
            
            /* Identify the Server */
            
            String server = ("jdbc:mysql://localhost/p2_test");
            String username = "root";
            String password = "CS488";
            System.out.println("Connecting to " + server + "...");
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection(server, username, password);
            
            if (conn.isValid(0)) 
            {
                
                /* Connection Open! */
                
                System.out.println("Connected Successfully!"); 
                
                Statement stat = conn.createStatement();
                String query = "SELECT * FROM p2_test.people";
                ResultSet result = stat.executeQuery(query);
                
                while(result.next())
                {
                    x.put("firstname", result.getString("firstname"));
                    x.put("middleinitial", result.getString("middleinitial"));
                    x.put("laststname", result.getString("lastname"));
                    x.put("address", result.getString("address"));
                    x.put("city", result.getString("city"));
                    x.put("state", result.getString("state"));
                    x.put("zip", result.getString("zip"));
                    data.add(x);
                }
            }
            /* Close Database Connection */            
            conn.close();
            }          
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        /* Close Other Database Objects */
        
        finally 
        {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
        return data;
    }  
}

