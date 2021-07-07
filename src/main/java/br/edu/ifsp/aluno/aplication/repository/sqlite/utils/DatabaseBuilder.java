package br.edu.ifsp.aluno.aplication.repository.sqlite.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() { return !Files.exists(Paths.get("SimpleAtaDatabase.db")); }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createXxxTable());
            statement.addBatch(createXxxTable());
            statement.addBatch(createXxxTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createXxxTable() {           //todo: Fazer um método similar para cada tabela
        StringBuilder builder = new StringBuilder();

        builder.append("cada linha da criação da tabela");
        builder.append("cada linha da criação da tabela");
        builder.append("cada linha da criação da tabela");
        // ...

        System.out.println(builder.toString());

        return builder.toString();
    }
}
