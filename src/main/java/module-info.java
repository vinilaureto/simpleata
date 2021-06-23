module br.edu.ifsp.aluno {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.edu.ifsp.aluno to javafx.fxml;
    exports br.edu.ifsp.aluno;
}