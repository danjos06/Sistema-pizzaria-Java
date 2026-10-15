package sp.senac.br.pizzaria.dao;

import java.util.List;

public interface InterfaceDao<T> {
    void inserir(T objeto);
    void alterar(T objeto);
    void excluir(Long id);
    List<T> listar();
    List<T> buscar(String valor);
}
