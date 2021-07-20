package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.controller.utils.ApplicationContext;
import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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
            bindTableViewToItemsList();
            bindColumnsToValueSource();
            loadDataAndShow();
        }
    }

    private void bindTableViewToItemsList() {
        tableDataComment = FXCollections.observableArrayList();
        tableViewComments.setItems(tableDataComment);

        tableDataVote = FXCollections.observableArrayList();
        tableViewVotes.setItems(tableDataVote);

        // TODO: 19/07/2021  Radio goes here
    }

    private void bindColumnsToValueSource() {
        cCommentParticipantName.setCellValueFactory(new PropertyValueFactory<>("participantName"));
        cCommentComment.setCellValueFactory(new PropertyValueFactory<>("message"));

        cVoteParticipantName.setCellValueFactory(new PropertyValueFactory<>("participantName"));
        cVoteVote.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    private void loadDataAndShow() {
        List<Comment> comments = findCommentUseCase.findBySchedule(schedule);
        tableDataComment.clear();
        tableDataComment.addAll(comments);

        if (voting != null) {
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
        setEntityIntoView();

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
        btnRemoveVote.setDisable(false);
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
    public void addVote(ActionEvent actionEvent) {

    }

    public void removeVote(ActionEvent actionEvent) {
    }

    private void showAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

