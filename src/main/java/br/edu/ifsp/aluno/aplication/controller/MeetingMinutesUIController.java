package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.group.Group;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
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
    @FXML
    private Button btnCreateMeetingMinutes;

    private ObservableList<Inform> informObservableList;
    private ObservableList<Schedule> scheduleObservableList;

    private MeetingMinutes meetingMinutes;

    @FXML
    private void initialize() {
        if (meetingMinutes != null) {
            bindTableViewToItemsList();
            bindColumnsToValueSource();
            loadDataAndShow();
        }
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

        List<Inform> informList = findInformUseCase.findByMeetingMinutes(meetingMinutes);
        informObservableList.clear();
        informObservableList.addAll(informList);

        List<Schedule> scheduleList = findScheduleUseCase.findByMeetingMinutes(meetingMinutes);
        scheduleObservableList.clear();
        scheduleObservableList.addAll(scheduleList);

    }

    public void createMeetingMinutes(ActionEvent actionEvent) {
        getEntityFromView();
        meetingMinutes.setCreationDate(LocalDate.now());
        meetingMinutes.setClosingDate(LocalDate.now());
        Integer newMeetingMinutesId = createMeetingMinutesUseCase.insert(meetingMinutes);
        meetingMinutes.setId(newMeetingMinutesId);
        enableEditFields();
    }

    private void getEntityFromView() {
        if (meetingMinutes == null) {
            meetingMinutes = new MeetingMinutes();
        }
        meetingMinutes.setTitle(txtMeetingMinutesTitle.getText());
        meetingMinutes.setGroup(cbGroup.getValue());
    }

    private void enableEditFields() {
        cbGroup.setDisable(true);
        btnCreateMeetingMinutes.setDisable(true);
        btnMeetingMinutesFindLogo.setDisable(false);
        tableViewInform.setDisable(false);
        btnRemoveInform.setDisable(false);
        btnEditInform.setDisable(false);
        btnNewInform.setDisable(false);
        tableViewSchedule.setDisable(false);
        btnRemoveSchedule.setDisable(false);
        btnEditSchedule.setDisable(false);
        btnNewSchedule.setDisable(false);
        btnBackToPreviousScreen.setVisible(false);
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        meetingMinutes.setClosingDate(LocalDate.now());
        updateMeetingMinutesUseCase.update(meetingMinutes);
        WindowLoader.setRoot("ManageMeetingMinutesUI");
    }

    public void backToPreviousScreen(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageMeetingMinutesUI");
    }

    public void setMeetingMinutes(MeetingMinutes meetingMinutes, UIMode mode) {
        if (meetingMinutes == null) {
            throw new IllegalArgumentException("Meeting Minutes can not be null.");
        }

        this.meetingMinutes = meetingMinutes;
        setEntityIntoView();

        if (mode == UIMode.UPDATE) {
            enableEditFields();
        }
        initialize();
    }

    private void setEntityIntoView() {
        txtMeetingMinutesTitle.setText(meetingMinutes.getTitle());
        cbGroup.setValue(meetingMinutes.getGroup());
    }

    public void exportMeetingMinutes(ActionEvent actionEvent) {
    }

    // *****************
    // LOGO MANAGEMENT

    public void meetingMinutesFindLogo(ActionEvent actionEvent) {
    }
    // *****************
    // INFORM MANAGEMENT

    public void newInform(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("InformUI");
        InformUIController inFormController = (InformUIController) WindowLoader.getController();
        inFormController.setMeetingMinutes(meetingMinutes);
    }

    public void editInform(ActionEvent actionEvent) throws IOException {
        showInformInMode(UIMode.UPDATE);
    }

    private void showInformInMode(UIMode mode) throws IOException {
        Inform selectedItem = tableViewInform.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("InformUI");
            InformUIController controller = (InformUIController) WindowLoader.getController();
            controller.setInform(selectedItem, mode);
            controller.setMeetingMinutes(this.meetingMinutes);
        }
    }

    public void removeInform(ActionEvent actionEvent) {
        Inform selectedItem = tableViewInform.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteInformUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    // *****************
    // SCHEDULE MANAGEMENT

    public void newSchedule(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ScheduleUI");
        ScheduleUIController controller = (ScheduleUIController) WindowLoader.getController();
        controller.setMeetingMinutes(meetingMinutes, UIMode.CREATE);
    }

    public void editSchedule(ActionEvent actionEvent) throws IOException {
        showScheduleInMode(UIMode.UPDATE);
        ScheduleUIController controller = (ScheduleUIController) WindowLoader.getController();
        controller.setSelectedVoting();
    }

    private void showScheduleInMode(UIMode mode) throws IOException {
        Schedule selectedItem = tableViewSchedule.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("ScheduleUI");
            ScheduleUIController controller = (ScheduleUIController) WindowLoader.getController();
            controller.setMeetingMinutesAndSchedule(meetingMinutes, selectedItem, mode);
//            controller.setSchedule(selectedItem, mode);
//            controller.setMeetingMinutes(this.meetingMinutes, mode);
        }
    }

    public void removeSchedule(ActionEvent actionEvent) {
        Schedule selectedItem = tableViewSchedule.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteScheduleUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }
}
