package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.controller.utils.ExportMeetingMinutesPDF;
import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static br.edu.ifsp.aluno.aplication.main.Main.findMeetingMinutesUseCase;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    @FXML
    private Button btnExportMeetingMinutes;
    @FXML
    private Label lbExportMeetingMinutes;

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
        
        cGroup.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataAndShow() {
        List<MeetingMinutes> meetingMinutes = findMeetingMinutesUseCase.findAll();
        tableData.clear();
        tableData.addAll(meetingMinutes);
    }

    public void searchMeetingMinutes(MouseEvent mouseEvent) {
    }

    public void manageParticipants(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageParticipantUI");
    }

    public void manageGroups(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ManageGroupUI");
    }

    public void newMeetingMinutes(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MeetingMinutesUI");
    }

    public void editMeetingMinutes(ActionEvent actionEvent) throws IOException {
        MeetingMinutes selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("MeetingMinutesUI");
            MeetingMinutesUIController controller = (MeetingMinutesUIController) WindowLoader.getController();
            controller.setMeetingMinutes(selectedItem, UIMode.UPDATE);
        }
    }

    public void removeMeetingMinutes(ActionEvent actionEvent) {
    }

    public void exportMeetingMinutes(ActionEvent actionEvent) throws DocumentException, FileNotFoundException {
        MeetingMinutes selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ExportMeetingMinutesPDF exportMeetingMinutesPDF = new ExportMeetingMinutesPDF();
            exportMeetingMinutesPDF.exportPDF(selectedItem);
            lbExportMeetingMinutes.setText("Ata exportada em PDF");
        }
    }
}
