import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
  
  Connection conn;
  PreparedStatement st;
  ResultSet rs;
  
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
  
  public List<ProdutosDTO> getProdutos() {
   conectaDAO conexao = new conectaDAO();
   conn = conexao.conectar(); 
    try {
      st = conn.prepareStatement("SELECT * FROM produtos");
      rs = st.executeQuery();
      
      List<ProdutosDTO> listaProdutos = new ArrayList<>();
      
      while (rs.next()) { 
        ProdutosDTO produto = new ProdutosDTO();
        
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setValor(rs.getInt("valor"));
        produto.setStatus(rs.getString("status"));
        listaProdutos.add(produto);
      }
      return listaProdutos;
    }
    catch (Exception e) {
      return null;
    }
  }
  
  public void venderProduto (int id){
    conectaDAO conexao = new conectaDAO();
    conn = conexao.conectar();
    
    try {
      st = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id=?",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      st.setInt(1, id);
      st.executeUpdate();
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null,"Erro ao vender o produto: " + e.getMessage());
    }
  }
  
  public List<ProdutosDTO> listarProdutosVendidos() {
   conectaDAO conexao = new conectaDAO();
   conn = conexao.conectar(); 
    try {
      st = conn.prepareStatement("SELECT * FROM produtos WHERE status LIKE 'Vendido' ");
      rs = st.executeQuery();
      
      List<ProdutosDTO> listaProdutos = new ArrayList<>();
      
      while (rs.next()) { 
        ProdutosDTO produto = new ProdutosDTO();
        
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setValor(rs.getInt("valor"));
        produto.setStatus(rs.getString("status"));
        listaProdutos.add(produto);
      }
      return listaProdutos;
    }
    catch (Exception e) {
      return null;
    }
  }
}

