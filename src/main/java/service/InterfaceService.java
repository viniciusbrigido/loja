package service;

import java.util.List;

public interface InterfaceService<T> {
    void salvar(T t);
    List<T> buscar();
    T buscar(int codigo);
    void atualizar(T t);
    void apagar(T t);
    void apagar(int codigo);
    void createOrUpdate(T t) throws Exception;
}
