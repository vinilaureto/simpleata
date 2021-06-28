package br.edu.ifsp.aluno.domain.usecases.utils;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) { super(message); }
}
