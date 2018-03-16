
package studentclient.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class DB_DAO 
{

    DBConnecter connecter;

    public DB_DAO()
    {
        connecter = new DBConnecter();
    }
    
    public void SOMEMETHOD() throws DALException
    {
        try (Connection con = connecter.getConnection())
        {
            String sql = "SELECT * FROM Message";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            

        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getMessage(), ex.getCause());
        }
    }
}
