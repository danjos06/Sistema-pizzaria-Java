package sp.senac.br.pizzaria.dao;

import sp.senac.br.pizzaria.factory.ConnectionFactory;
import sp.senac.br.pizzaria.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements InterfaceDao<Cliente> {
    @Override
    public void inserir(Cliente objeto) {
        String sql = "INSERT INTO tb_cliente(nome, telefone, cpf, endereco, nascimento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getCpf());
            stmt.setString(4, objeto.getEndereco());
            stmt.setObject(5, objeto.getNascimento());
            stmt.execute();
        } catch (SQLIntegrityConstraintViolationException e){
            if (e.getErrorCode() == 1062) {
                throw new IllegalArgumentException("Já existe um cliente com esse telefone.");
            }
            throw new RuntimeException(e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterar(Cliente objeto) {
        String sql = "UPDATE tb_cliente SET nome = ?, telefone = ?, cpf = ?, endereco = ?, nascimento = ? WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getCpf());
            stmt.setString(4, objeto.getEndereco());
            stmt.setObject(5, objeto.getNascimento());
            stmt.setLong(6, objeto.getId());
            stmt.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluir(Long id) {
        String sql = "DELETE FROM tb_cliente WHERE id = ?";
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cliente> listar() {
        String sql = "SELECT * FROM tb_cliente ORDER BY nome";
        List<Cliente> lista = new ArrayList<>();
        try(Connection conexao = ConnectionFactory.getConnection();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setCpf(rs.getString("cpf"));
                c.setNascimento(rs.getObject("nascimento", LocalDate.class));
                lista.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public List<Cliente> buscar(String telefone) {
        String sql = "SELECT * FROM tb_cliente WHERE telefone LIKE ?";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, "%" + telefone + "%");
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Cliente c = new Cliente();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setCpf(rs.getString("cpf"));
                    c.setNascimento(rs.getObject("nascimento", LocalDate.class));
                    lista.add(c);
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return lista;
    }
}
