package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import static br.edu.ifsp.aluno.aplication.main.Main.deleteParticipantUseCase;
import static br.edu.ifsp.aluno.aplication.main.Main.findParticipantUseCase;

import java.io.IOException;
import java.util.List;

public class ManageParticipantUIController {

    @FXML
    private TextField txtSearchParticipant;
    @FXML
    private TableView<Participant> tableView;
    @FXML
    private TableColumn<Participant, String> cName;
    @FXML
    private TableColumn<Participant, String> cEmail;
    @FXML
    private TableColumn<Participant, String> cTitle;

    private ObservableList<Participant> tableData;

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
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    private void loadDataAndShow() {
        List<Participant> participantList = findParticipantUseCase.findAll();
        tableData.clear();
        tableData.addAll(participantList);
    }

    public void newParticipant(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ParticipantUI");
    }

    public void editParticipant(ActionEvent actionEvent) throws IOException {
        showParticipantInMode(UIMode.UPDATE);
    }

    public void removeParticipant(ActionEvent actionEvent) {
        Participant selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteParticipantUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    public void searchParticipant(ActionEvent actionEvent) {
        List<Participant> list = findParticipantUseCase.findByName(txtSearchParticipant.getText());
        if (!list.isEmpty()) {
            tableData.clear();
            tableData.addAll(list);
        } else {
            showAlert("Erro!", "Participante não encontrado", Alert.AlertType.ERROR);
            loadDataAndShow();
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageMeetingMinutesUI");
    }

    private void showParticipantInMode(UIMode mode) throws IOException {
        Participant selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("ParticipantUI");
            ParticipantUIController controller = (ParticipantUIController) WindowLoader.getController();
            controller.setParticipant(selectedItem, mode);
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
