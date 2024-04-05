package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

//////import com.mysql.cj.protocol.Resultset;
//import com.mysql.cj.xdevapi.Client;

import entities.Cliente;
import until.ApiException;

public class ClienteDao {

 
    public Cliente getByNit (int nit ){
        System.out.println("en cliente get by nit");

        try {
            PreparedStatement stmt = DBConnection.getInstance().getConection().prepareStatement("SELECT * FROM cliente WHERE NIT = ?;");
            stmt.setString(1, String.valueOf(nit));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                
                Cliente cliente = new Cliente();
                cliente.setNit(rs.getInt("NIT"));
                cliente.setNombre(rs.getString("nombre"));
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente insert (Cliente cliente) throws ApiException {

       try {
        
        PreparedStatement stmt = DBConnection.getInstance().getConection().prepareStatement("INSERT INTO cliente (NIT,nombre) VALUES (?,?);", 
                Statement.RETURN_GENERATED_KEYS);
         stmt.setInt(1, cliente.getNit());
         stmt.setString(2, cliente.getNombre());

         stmt.execute();
         ResultSet generatedKeys = stmt.getGeneratedKeys();
         if (generatedKeys.next()) {
            cliente.setNit(generatedKeys.getInt(1));
            return cliente;
         }
         return null;

       } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
}
