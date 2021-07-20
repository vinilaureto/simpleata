package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static br.edu.ifsp.aluno.aplication.main.Main.createParticipantUseCase;
import static br.edu.ifsp.aluno.aplication.main.Main.updateParticipantUseCase;

import java.io.IOException;

public class ParticipantUIController {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTitle;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnBackToPreviousScene;

    private Participant participant;

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        if (participant.getId() == null) {
            createParticipantUseCase.insert(participant);
        } else {
            updateParticipantUseCase.update(participant);
        }
        WindowLoader.setRoot("ManageParticipantUI");
    }

    private void getEntityFromView() {
        if (participant == null) {
            participant = new Participant();
        }
        participant.setName(txtName.getText());
        participant.setEmail(txtEmail.getText());
        participant.setTitle(txtTitle.getText());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageParticipantUI");
    }

    public void setParticipant(Participant participant, UIMode mode) {
        if (participant == null) {
            throw new IllegalArgumentException("Participant can not be null.");
        }

        this.participant = participant;
        setEntityIntoView();

        if (mode == UIMode.VIEW) {
            configureViewMode();
        }
    }

    private void setEntityIntoView() {
        txtName.setText(participant.getName());
        txtEmail.setText(participant.getEmail());
        txtTitle.setText(participant.getTitle());
    }

    private void configureViewMode() {
        btnBackToPreviousScene.setLayoutX(btnSaveOrUpdate.getLayoutX());
        btnBackToPreviousScene.setLayoutY(btnSaveOrUpdate.getLayoutY());
        btnBackToPreviousScene.setText("Fechar");

        btnSaveOrUpdate.setVisible(false);

        txtName.setDisable(true);
        txtEmail.setDisable(true);
        txtTitle.setDisable(true);
    }
}
