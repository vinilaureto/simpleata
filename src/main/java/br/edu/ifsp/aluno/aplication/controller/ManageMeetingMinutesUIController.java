package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static br.edu.ifsp.aluno.aplication.main.Main.findMeetingMinutesUseCase;

import java.util.List;

public class ManageMeetingMinutesUIController {

    @FXML
    private TableView<MeetingMinutes> tableView;
    @FXML
    private TableColumn<MeetingMinutes, String> cIdentifier;
    @FXML
    private TableColumn<MeetingMinutes, String> cTitle;
    @FXML
    private TableColumn<MeetingMinutes, String> cGroup;
    @FXML
    private TableColumn<MeetingMinutes, String> cStatus;

    private ObservableList<MeetingMinutes> tableData;

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
        cIdentifier.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        cTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        //cGroup.setCellValueFactory(new PropertyValueFactory<>(""));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataAndShow() {
        List<MeetingMinutes> meetingMinutes = findMeetingMinutesUseCase.findAll();
        tableData.clear();
        tableData.addAll(meetingMinutes);
    }

    public void searchMeetingMinutes(MouseEvent mouseEvent) {
    }

    public void manageParticipants(ActionEvent actionEvent) {
    }

    public void manageGroups(ActionEvent actionEvent) {
    }

    public void newMeetingMinutes(ActionEvent actionEvent) {
    }

    public void editMeetingMinutes(ActionEvent actionEvent) {
    }

    public void removeMeetingMinutes(ActionEvent actionEvent) {
    }
}