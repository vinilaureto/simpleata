package br.edu.ifsp.aluno.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO <T, K> {
    K insert(T type);

    Optional<T> findOne(K key);

    List<T> findAll();

    boolean update(T type);

    boolean deleteByKey(K key);

    boolean delete(T type);
}
