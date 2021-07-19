module br.edu.ifsp.aluno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens br.edu.ifsp.aluno.aplication.view to javafx.fxml;
    opens br.edu.ifsp.aluno.aplication.controller to javafx.fxml;
    opens br.edu.ifsp.aluno.domain.entities.participant to javafx.base;
    opens br.edu.ifsp.aluno.domain.entities.group to javafx.base;
    opens br.edu.ifsp.aluno.domain.entities.meetingMinutes to javafx.base;
    opens br.edu.ifsp.aluno.domain.entities.inform to javafx.base;
    opens br.edu.ifsp.aluno.domain.entities.schedule to javafx.base;
    opens br.edu.ifsp.aluno.domain.entities.comment to javafx.base;
    opens br.edu.ifsp.aluno.domain.entities.vote to javafx.base;
    opens br.edu.ifsp.aluno.domain.entities.voting to javafx.base;

    exports br.edu.ifsp.aluno.aplication.view;
    exports br.edu.ifsp.aluno.aplication.controller;

}