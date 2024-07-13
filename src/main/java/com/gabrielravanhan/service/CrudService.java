package com.gabrielravanhan.service;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> buscarTodos();

    T buscarPeloId(ID id);

    T criar(T entity);

    T atualizar(ID id, T entity);

    void deletar(ID id);
}
