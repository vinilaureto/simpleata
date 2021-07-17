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
    private Button btnRemoveParticipant;
    @FXML
    private Button btnBackToPreviousScene;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnFindParticipant;
    @FXML
    private Button btnCreateGroup;

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
        tableDataAllParticipants.clear();
        tableDataAllParticipants.addAll(participantList);
    }

    public void btnAddParticipant(ActionEvent actionEvent) {
        Participant selectedItem = allParticipants.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            tableDataAllParticipants.remove(selectedItem);
            tableDataGroupParticipants.add(selectedItem);
            addParticipantToGroupUseCase.addParticipantToGroup(selectedItem, group);
        }
    }

    public void btnRemoveParticipant(ActionEvent actionEvent) {
        Participant selectedItem = groupParticipants.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            tableDataGroupParticipants.remove(selectedItem);
            tableDataAllParticipants.add(selectedItem);
            removeParticipantFromGroupUseCase.removeParticipantFromGroup(selectedItem, group);
        }
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        updateGroupUseCase.update(group);
        WindowLoader.setRoot("ManageGroupUI");
    }

    private void getEntityFromView() {
        if (group == null) {
            group = new Group();
        }
        group.setName(txtGroupName.getText());
        group.setParticipants(groupParticipants.getItems());
    }

    public void findParticipant(ActionEvent actionEvent) {
        List<Participant> list = findParticipantUseCase.findByName(txtParticipantName.getText());
        if (!list.isEmpty()) {
            tableDataAllParticipants.clear();
            tableDataAllParticipants.addAll(list);
            group.getParticipants().forEach(p -> tableDataAllParticipants.remove(p));
        } else {
            showAlert("Erro!", "Participante não encontrado", Alert.AlertType.ERROR);
            loadDataAndShow();
            group.getParticipants().forEach(p -> tableDataAllParticipants.remove(p));
        }
    }

    public void setGroup(Group group, UIMode mode) {
        if (group == null) {
            throw new IllegalArgumentException("Group can not be null.");
        }

        this.group = group;
        System.out.println(group);
        setEntityIntoView();

        if (mode == UIMode.VIEW) {
            configureViewMode();
        }

        if (mode == UIMode.UPDATE) {
            enableEditFields();
        }
    }

    private void setEntityIntoView() {
        txtGroupName.setText(group.getName());

        group.getParticipants().forEach(p -> tableDataAllParticipants.remove(p));

        tableDataGroupParticipants.clear();
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

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageGroupUI");
    }

    public void createGroup(ActionEvent actionEvent) {
        getEntityFromView();
        Integer newGroupId = createGroupUseCase.insert(group);
        group.setId(newGroupId);
        enableEditFields();
    }

    private void enableEditFields() {
        btnCreateGroup.setDisable(true);
        txtParticipantName.setDisable(false);
        btnFindParticipant.setDisable(false);
        btnAddParticipant.setDisable(false);
        btnRemoveParticipant.setDisable(false);
        allParticipants.setDisable(false);
        groupParticipants.setDisable(false);
        btnBackToPreviousScene.setVisible(false);
    }
}
