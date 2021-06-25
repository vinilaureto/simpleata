package br.edu.ifsp.aluno.domain.usecases.utils;

import java.util.Collection;

public abstract class Validator<T> {
    public abstract Notification validate(T type);

    public static boolean isNullOrEmpty(String string) { return string == null || string.isEmpty(); }

    public static boolean isNullOrEmpty(Collection collection) { return collection == null || collection.isEmpty(); }
}
