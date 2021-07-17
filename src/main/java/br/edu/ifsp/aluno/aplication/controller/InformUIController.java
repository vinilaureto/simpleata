package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class InformUIController {
    public void saveOrUpdate(ActionEvent actionEvent) {
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MeetingMinutesUI");
    }
}
