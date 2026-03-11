
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
  Connection conn = null;
  
  public Connection conectar(){
    
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root", "2008");
      JOptionPane.showMessageDialog(null, "Conexão com o banco de dados foi bem sucedida");
    }
    catch (ClassNotFoundException e) {
      JOptionPane.showMessageDialog(null,"Driver do MySQL não encontrado");
    }
    catch (SQLException e){
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Erro ConectaDAO " + e.getMessage());
    }
    return conn;
  }
  
  public void desconectar(){
    try {
      conn.close();
    }
    catch (SQLException ex) { }
  }

}
