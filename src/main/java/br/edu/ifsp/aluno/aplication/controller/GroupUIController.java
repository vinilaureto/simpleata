package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.io.IOException;
import java.util.List;

public class GroupUIController {

    @FXML
    private TextField txtGroupName;
    @FXML
    private TextField txtParticipantName;
    @FXML
    private TableView<Participant> allParticipants;
    @FXML
    private TableColumn<Participant, String> cAllName;
    @FXML
    private TableColumn<Participant, String> cAllEmail;
    @FXML
    private TableView<Participant> groupParticipants;
    @FXML
    private TableColumn<Participant, String> cGroupName;
    @FXML
    private TableColumn<Participant, String> cGroupEmail;
    @FXML
    private Button btnAddParticipant;
    @FXML
    private Button btnBackToPreviousScene;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnFindParticipant;

    private ObservableList<Participant> tableDataAllParticipants;
    private ObservableList<Participant> tableDataGroupParticipants;

    private Group group;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSource();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableDataAllParticipants = FXCollections.observableArrayList();
        allParticipants.setItems(tableDataAllParticipants);
        tableDataGroupParticipants = FXCollections.observableArrayList();
        groupParticipants.setItems(tableDataGroupParticipants);
    }

    private void bindColumnsToValueSource() {
        cAllName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cAllEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cGroupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cGroupEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadDataAndShow() {
        List<Participant> participantList = findParticipantUseCase.findAll();
        //List<Participant> groupParticipants = findGroupUseCase.findByName(group.getName()).get().getParticipants();
//        participantList.remove(group.getParticipants());
        tableDataAllParticipants.clear();
        tableDataAllParticipants.addAll(participantList);
        tableDataGroupParticipants.clear();
//        tableDataGroupParticipants.addAll(group.getParticipants());
    }

    public void btnAddParticipant(ActionEvent actionEvent) throws IOException {
    }

    public void btnRemoveParticipant(ActionEvent actionEvent) throws IOException {
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageGroupUI");
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        if (group.getId() == null) {
            createGroupUseCase.insert(group);
        } else {
            updateGroupUseCase.update(group);
        }
        WindowLoader.setRoot("ManageGroupUI");
    }

    private void getEntityFromView() {
        if (group == null) {
            group = new Group();
        }
        group.setName(txtGroupName.getText());
        group.setParticipants(groupParticipants.getItems());
    }

    public void findParticipant(ActionEvent actionEvent) throws IOException {
        List<Participant> list = findParticipantUseCase.findByName(txtParticipantName.getText());
        if (!list.isEmpty()) {
            tableDataGroupParticipants.clear();
            tableDataAllParticipants.addAll(list);
        } else {
            showAlert("Erro!", "Participante não encontrado", Alert.AlertType.ERROR);
            loadDataAndShow();
        }
    }

    public void setGroup(Group group, UIMode mode) {
        if (group == null) {
            throw new IllegalArgumentException("Group can not be null.");
        }

        this.group = group;
        setEntityIntoView();

        if (mode == UIMode.VIEW) {
            configureViewMode();
        }
    }

    private void setEntityIntoView() {
        txtGroupName.setText(group.getName());
        tableDataGroupParticipants.addAll(group.getParticipants());
    }

    private void configureViewMode() {
        btnBackToPreviousScene.setLayoutX(btnSaveOrUpdate.getLayoutX());
        btnBackToPreviousScene.setLayoutY(btnSaveOrUpdate.getLayoutY());
        btnBackToPreviousScene.setText("Fechar");

        btnSaveOrUpdate.setVisible(false);

        txtGroupName.setDisable(true);
        txtParticipantName.setDisable(true);
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
