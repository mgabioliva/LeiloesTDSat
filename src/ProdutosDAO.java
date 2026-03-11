
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
  
  Connection conn;
  PreparedStatement st;
  ResultSet rs;
  
  ArrayList<ProdutosDTO> listagem = new ArrayList<>();
  
  public int cadastrarProduto (ProdutosDTO produto){
    int status;
    conectaDAO conexao = new conectaDAO();
    conn = conexao.conectar();
    
    try {
      st = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
      st.setString(1,produto.getNome());
      st.setInt(2,produto.getValor());
      st.setString(3,produto.getStatus());
      status = st.executeUpdate();
      
      return status;
    }
    
    catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Erro ao cadastar produtos" + e.getMessage());
      return e.getErrorCode();
    }
  }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
        
}

