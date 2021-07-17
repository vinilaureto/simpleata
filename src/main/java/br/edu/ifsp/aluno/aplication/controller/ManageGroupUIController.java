package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.aluno.aplication.main.Main.*;

public class ManageGroupUIController {

    @FXML
    private TextField txtSearchGroup;
    @FXML
    private Button btnSearchGroup;
    @FXML
    private TableView<Group> tableView;
    @FXML
    private TableColumn<Group, String> cGroup;
    @FXML
    private TableColumn<Group, String> cParticipants;

    private ObservableList<Group> tableData;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSource();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSource() {
        cGroup.setCellValueFactory(new PropertyValueFactory<>("name"));
        cParticipants.setCellValueFactory(new PropertyValueFactory<>("participantsNames"));
    }

    private void loadDataAndShow() {
        List<Group> groupList = findGroupUseCase.findAll();
        tableData.clear();
        tableData.addAll(groupList);
    }

    public void newGroup(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("GroupUI");
    }

    public void editGroup(ActionEvent actionEvent) throws IOException {
        showGroupInMode(UIMode.UPDATE);
    }

    public void removeGroup(ActionEvent actionEvent) {
        Group selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteGroupUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void searchGroup(ActionEvent actionEvent) {
        Optional<Group> group = findGroupUseCase.findByName(txtSearchGroup.getText());
        if (group.isPresent()) {
            tableData.clear();
            tableData.addAll(group.get());
        } else {
            showAlert("Erro!", "Grupo não encontrado", Alert.AlertType.ERROR);
            loadDataAndShow();
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageMeetingMinutesUI");
    }

    private void showGroupInMode(UIMode mode) throws IOException {
        Group selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("GroupUI");
            GroupUIController controller = (GroupUIController) WindowLoader.getController();
            controller.setGroup(selectedItem, mode);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}