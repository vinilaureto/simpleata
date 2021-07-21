package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.vote.VoteValue;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.edu.ifsp.aluno.aplication.main.Main.*;

public class ScheduleUIController {

    @FXML
    private TextField txtScheduleTopic;
    @FXML
    private Button btnCreateSchedule;
    @FXML
    private TableView<Comment> tableViewComments;
    @FXML
    private TableColumn<Comment, String> cCommentParticipantName;
    @FXML
    private TableColumn<Comment, String> cCommentComment;
    @FXML
    private Button btnRemoveComment;
    @FXML
    private Button btnEditComment;
    @FXML
    private Button btnNewComment;
    @FXML
    final private ToggleGroup tgVoting = new ToggleGroup();
    @FXML
    private RadioButton rdNoVoting;
    @FXML
    private RadioButton rdVotingNamed;
    @FXML
    private RadioButton rdVotingAnonymous;
    @FXML
    private TableView<Vote> tableViewVotes;
    @FXML
    private TableColumn<Vote, String> cVoteParticipantName;
    @FXML
    private TableColumn<Vote, String> cVoteVote;
    @FXML
    private ComboBox<Participant> cbParticipant;
    @FXML
    private ComboBox<VoteValue> cbVoteValue;
    @FXML
    private Button btnAddVote;
    @FXML
    private Button btnRemoveVote;
    @FXML
    private Button btnBackToPreviousScene;
    @FXML
    private Button btnSaveOrUpdate;

    private ObservableList<Comment> tableDataComment;
    private ObservableList<Vote> tableDataVote;

    private Schedule schedule;
    private MeetingMinutes meetingMinutes;
    private Voting voting;

    @FXML
    private void initialize() {
//        ApplicationContext applicationContext = ApplicationContext.getInstance();
//        group = applicationContext.getCurrentGroup();

        if (schedule != null) {
            findVotingUseCase.findBySchedule(schedule).ifPresent(v -> voting = v);
            if (voting != null) {
                schedule.setVoting(voting);
                bindTableViewToItemsList();
                bindColumnsToValueSource();
                loadDataAndShow();
            }
        }
    }

    private void bindTableViewToItemsList() {
        tableDataComment = FXCollections.observableArrayList();
        tableViewComments.setItems(tableDataComment);

        tableDataVote = FXCollections.observableArrayList();
        tableViewVotes.setItems(tableDataVote);

        rdNoVoting.setToggleGroup(tgVoting);
        rdVotingNamed.setToggleGroup(tgVoting);
        rdVotingAnonymous.setToggleGroup(tgVoting);

    }

    private void bindColumnsToValueSource() {
        cCommentParticipantName.setCellValueFactory(new PropertyValueFactory<>("participantName"));
        cCommentComment.setCellValueFactory(new PropertyValueFactory<>("message"));

        cVoteParticipantName.setCellValueFactory(new PropertyValueFactory<>("participantName"));
        cVoteVote.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    private void loadDataAndShow() {
        System.out.println("Pauta " + schedule);
        List<Comment> comments = findCommentUseCase.findBySchedule(schedule);
        tableDataComment.clear();
        tableDataComment.addAll(comments);

        if (schedule.getVoting() != null) {
            voting = findVotingUseCase.findBySchedule(schedule).get();
            List<Vote> votes = findVoteUseCase.findByVoting(voting);
            tableDataVote.clear();
            tableDataVote.addAll(votes);
        }
    }

    public void setMeetingMinutesAndSchedule(MeetingMinutes meetingMinutes, Schedule schedule, UIMode mode) {
        if (meetingMinutes == null) {
            throw new IllegalArgumentException("Meeting Minutes can not be null.");
        }
        this.meetingMinutes = meetingMinutes;

        if (schedule == null) {
            throw new IllegalArgumentException("Schedule can not be null.");
        }
        this.schedule = schedule;

        setEntityIntoView();

        if (mode == UIMode.UPDATE) {
            enableEditFields();
        }
        initialize();
    }

    public void setMeetingMinutes(MeetingMinutes meetingMinutes,UIMode mode) {
        if (meetingMinutes == null) {
            throw new IllegalArgumentException("Meeting Minutes can not be null.");
        }
        this.meetingMinutes = meetingMinutes;

        if (mode == UIMode.UPDATE) {
            enableEditFields();
        }
        initialize();
    }

    public void setSchedule(Schedule schedule, UIMode mode) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule can not be null.");
        }

        this.schedule = schedule;
        setEntityIntoView();

        if (mode == UIMode.UPDATE) {
            initialize();
        }
    }

    private void enableEditFields() {
        btnCreateSchedule.setVisible(false);
        tableViewComments.setDisable(false);
        btnRemoveComment.setDisable(false);
        btnEditComment.setDisable(false);
        btnNewComment.setDisable(false);
        rdNoVoting.setDisable(false);
        rdVotingNamed.setDisable(false);
        rdVotingAnonymous.setDisable(false);
        tableViewVotes.setDisable(false);
        btnBackToPreviousScene.setDisable(false);
        btnSaveOrUpdate.setDisable(false);
    }

    public void createSchedule(ActionEvent actionEvent) {
        getEntityFromView();
        schedule.setTopic(txtScheduleTopic.getText());
        schedule.setMeetingMinutes(meetingMinutes);
        schedule.setId(createScheduleUseCase.insert(schedule));
        enableEditFields();
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        if (schedule.getId() == null) {
            createScheduleUseCase.insert(schedule);
        } else {
            updateScheduleUseCase.update(schedule);
        }
        WindowLoader.setRoot("MeetingMinutesUI");
        MeetingMinutesUIController controller = (MeetingMinutesUIController) WindowLoader.getController();
        controller.setMeetingMinutes(meetingMinutes,UIMode.UPDATE);
    }

    public void getEntityFromView() {
        if (schedule == null) {
            schedule = new Schedule();
        } else {
            schedule.setTopic(txtScheduleTopic.getText());
            schedule.setMeetingMinutes(meetingMinutes);
        }
    }

    private void configureMode() {
    }


    private void setEntityIntoView() {
        txtScheduleTopic.setText(schedule.getTopic());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MeetingMinutesUI");
        MeetingMinutesUIController controller = (MeetingMinutesUIController) WindowLoader.getController();
        controller.setMeetingMinutes(meetingMinutes,UIMode.UPDATE);
    }

    public void setSelectedVoting() {
        if (voting == null) {
            tgVoting.selectToggle(rdNoVoting);
        } else {
            List<Vote> votes = findVoteUseCase.findByVoting(voting);
            if (!votes.isEmpty() && votes.get(0).getParticipant() == null) {
                tgVoting.selectToggle(rdVotingAnonymous);
            } else if (!votes.isEmpty() && votes.get(0).getParticipant() != null) {
                tgVoting.selectToggle(rdVotingNamed);
            }
        }
    }

    // *****************
    // COMMENT MANAGEMENT
    public void newComment(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CommentUI");
        CommentUIController controller = (CommentUIController) WindowLoader.getController();
        controller.setMeetingMinutes(meetingMinutes);
        controller.setSchedule(schedule);
    }

    public void editComment(ActionEvent actionEvent) throws IOException {
        showCommentInMode(UIMode.UPDATE);
    }

    private void showCommentInMode(UIMode mode) throws IOException {
        Comment selectedItem = tableViewComments.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            WindowLoader.setRoot("CommentUI");
            CommentUIController controller = (CommentUIController) WindowLoader.getController();
            controller.setComment(selectedItem, mode);
            controller.setMeetingMinutes(meetingMinutes);
            controller.setSchedule(schedule);
        }
    }

    public void removeComment(ActionEvent actionEvent) {
        Comment selectedItem = tableViewComments.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteCommentUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    // *****************
    // VOTING MANAGEMENT
    public void doNotVote(ActionEvent actionEvent) {
        disableVotingFields();
        deleteVotingIfItExists();
        this.schedule.setVoting(voting);
    }

    private void deleteVotingIfItExists() {
        if (voting != null) {
            deleteVotingUseCase.delete(voting);
            voting = null;
        }
    }

    private void disableVotingFields() {
        cbParticipant.setDisable(true);
        cbVoteValue.setDisable(true);
        btnAddVote.setDisable(true);
        btnRemoveVote.setDisable(true);
    }

    public void voteNamed(ActionEvent actionEvent) {
        enableNamedVotingFields();
        deleteVotingIfItExists();
        if (rdVotingNamed.isSelected()) {
            setVotingIntoView();
        }
        this.schedule.setVoting(voting);
    }

    private void enableNamedVotingFields() {
        cbParticipant.setDisable(false);
        cbVoteValue.setDisable(false);
        btnAddVote.setDisable(false);
        btnRemoveVote.setDisable(false);
    }

    private void setVotingIntoView() {
        if (voting == null) {
            Voting voting = new Voting();
            voting.setSchedule(schedule);
            voting.setId(createVotingUseCase.insert(voting));
            this.voting = voting;
        }
        cbParticipant.getItems().setAll(findParticipantUseCase.findAllinGroup(meetingMinutes.getGroup()));
        cbVoteValue.getItems().setAll(VoteValue.values());
    }

    public void voteAnonymous(ActionEvent actionEvent) {
        enableAnonymousVotingFields();
        deleteVotingIfItExists();
        if (rdVotingAnonymous.isSelected()) {
            setVotingIntoView();
        }
        this.schedule.setVoting(voting);
    }

    private void enableAnonymousVotingFields() {
        cbParticipant.setDisable(true);
        cbVoteValue.setDisable(false);
        btnAddVote.setDisable(false);
        btnRemoveVote.setDisable(false);
    }

    public void addVote(ActionEvent actionEvent) {
        if (rdVotingNamed.isSelected()) {
            insertNewVote(cbParticipant.getValue());
        }
        if (rdVotingAnonymous.isSelected()) {
            insertNewVote(null);
        }
    }

    private void insertNewVote(Participant participant) {
        Vote vote = new Vote();
        vote.setVoting(this.voting);
        vote.setParticipant(participant);
        vote.setValue(cbVoteValue.getValue());
        createVoteUseCase.insert(vote);
        loadDataAndShow();
    }

    public void removeVote(ActionEvent actionEvent) {
        Vote selectedItem = tableViewVotes.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteVoteUseCase.delete(selectedItem);
            loadDataAndShow();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void editVote(ActionEvent actionEvent) {
    }
}

