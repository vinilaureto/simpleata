module br.edu.ifsp.aluno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires sqlite.jdbc;

    opens br.edu.ifsp.aluno.aplication.view to javafx.fxml;
    opens br.edu.ifsp.aluno.aplication.controller to javafx.fxml;
    opens br.edu.ifsp.aluno.domain.entities.participant to javafx.base;

    exports br.edu.ifsp.aluno.aplication.view;
    exports br.edu.ifsp.aluno.aplication.controller;

}