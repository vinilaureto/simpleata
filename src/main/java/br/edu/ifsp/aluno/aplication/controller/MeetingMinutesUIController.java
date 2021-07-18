package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MeetingMinutesUIController {

    @FXML
    private TextField txtMeetingMinutesTitle;
    @FXML
    private ComboBox<Group> cbGroup;
    @FXML
    private Button btnMeetingMinutesFindLogo;
    @FXML
    private TableView<Inform> tableViewInform;
    @FXML
    private TableColumn<Inform, String> cInform;
    @FXML
    private Button btnRemoveInform;
    @FXML
    private Button btnEditInform;
    @FXML
    private Button btnNewInform;
    @FXML
    private TableView<Schedule> tableViewSchedule;
    @FXML
    private TableColumn<Schedule, String> cSchedule;
    @FXML
    private Button btnRemoveSchedule;
    @FXML
    private Button btnEditSchedule;
    @FXML
    private Button btnNewSchedule;
    @FXML
    private Button btnExportMeetingMinutes;
    @FXML
    private Button btnBackToPreviousScreen;
    @FXML
    private Button btnSaveOrUpdate;

    private ObservableList<Inform> informObservableList;
    private ObservableList<Schedule> scheduleObservableList;

    private MeetingMinutes meetingMinutes;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSource();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        informObservableList = FXCollections.observableArrayList();
        tableViewInform.setItems(informObservableList);
        scheduleObservableList = FXCollections.observableArrayList();
        tableViewSchedule.setItems(scheduleObservableList);
    }

    private void bindColumnsToValueSource() {
        cInform.setCellValueFactory(new PropertyValueFactory<>("description"));
        cSchedule.setCellValueFactory(new PropertyValueFactory<>("topic"));
    }

    private void loadDataAndShow() {
        cbGroup.getItems().setAll(findGroupUseCase.findAll());
    }

    public void newSchedule(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ScheduleUI");
    }

    public void editSchedule(ActionEvent actionEvent) {
    }

    public void removeSchedule(ActionEvent actionEvent) {
    }

    public void exportMeetingMinutes(ActionEvent actionEvent) {
    }

    public void newInform(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("InformUI");
    }

    public void editInform(ActionEvent actionEvent) {
    }

    public void removeInform(ActionEvent actionEvent) {
    }

    public void meetingMinutesFindLogo(ActionEvent actionEvent) {
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        if (meetingMinutes.getId() == null) {
            meetingMinutes.setCreationDate(LocalDate.now());
            meetingMinutes.setClosingDate(LocalDate.now());
            createMeetingMinutesUseCase.insert(meetingMinutes);
        } else {
            meetingMinutes.setClosingDate(LocalDate.now());
            updateMeetingMinutesUseCase.update(meetingMinutes);
        }
        WindowLoader.setRoot("ManageMeetingMinutesUI");
    }

    private void getEntityFromView() {
        if (meetingMinutes == null) {
            meetingMinutes = new MeetingMinutes();
        }
        meetingMinutes.setTitle(txtMeetingMinutesTitle.getText());
        meetingMinutes.setGroup(cbGroup.getValue());
    }

    public void backToPreviousScreen(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageMeetingMinutesUI");
    }
}
