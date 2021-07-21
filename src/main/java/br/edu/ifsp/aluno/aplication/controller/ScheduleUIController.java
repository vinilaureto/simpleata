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
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private Button btnEditVote;
    @FXML
    private Button btnRemoveVote;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnBackToPreviousScene;
    @FXML
    private Label lbVotingResult;

    private ObservableList<Comment> tableDataComment;
    private ObservableList<Vote> tableDataVote;
    private ObservableList<Participant> comboParticipant;
    private ObservableList<VoteValue> comboVoteValue;

    private Schedule schedule;
    private MeetingMinutes meetingMinutes;
    private Voting voting;

    @FXML
    private void initialize() {

        if (schedule != null) {
            findVotingUseCase.findBySchedule(schedule).ifPresent(v -> voting = v);

            schedule.setVoting(voting);
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

        comboParticipant = FXCollections.observableArrayList();
        cbParticipant.setItems(comboParticipant);

        comboVoteValue = FXCollections.observableArrayList();
        cbVoteValue.getItems().setAll(VoteValue.values());

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
        List<Comment> comments = findCommentUseCase.findBySchedule(schedule);
        tableDataComment.clear();
        tableDataComment.addAll(comments);

        findVotingUseCase.findBySchedule(schedule).ifPresent(v -> voting = v);
        if (voting != null) {
            voting = findVotingUseCase.findBySchedule(schedule).get();
            List<Vote> votes = findVoteUseCase.findByVoting(voting);
            voting.setVotes(votes);
            if (!votes.isEmpty()) {
                votes.stream().iterator().next().setVoting(voting);
            }
            schedule.setVoting(voting);
            tableDataVote.clear();
            tableDataVote.addAll(votes);
            voting.resolveVoting();
            updateVotingUseCase.update(voting);

            if (!tableViewVotes.getItems().isEmpty()){
                voting.resolveVoting();
                lbVotingResult.setText(voting.getResult().toString());
            }
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
        btnBackToPreviousScene.setVisible(false);
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
    public void doNotVote(ActionEvent actionEvent) {
        disableVotingFields();
        deleteVotingIfItExists();
        this.schedule.setVoting(voting);
    }

    private void deleteVotingIfItExists() {
        if (voting != null) {
            deleteVotingUseCase.delete(voting);
            voting = null;
            comboParticipant.clear();
            loadDataAndShow();
        }
    }

    private void disableVotingFields() {
        cbParticipant.setDisable(true);
        cbVoteValue.setDisable(true);
        btnAddVote.setDisable(true);
        btnEditVote.setDisable(true);
        btnRemoveVote.setDisable(true);
    }

    public void voteNamed(ActionEvent actionEvent) {
        enableNamedVotingFields();
        deleteVotingIfItExists();
        if (rdVotingNamed.isSelected()) {
            setVotingIntoView();
            if (!tableDataVote.isEmpty()) {
                lbVotingResult.setDisable(false);
            }
        }
        this.schedule.setVoting(voting);
    }

    private void enableNamedVotingFields() {
        cbParticipant.setDisable(false);
        cbVoteValue.setDisable(false);
        btnAddVote.setDisable(false);
        btnEditVote.setDisable(false);
        btnRemoveVote.setDisable(false);
    }

    private void setVotingIntoView() {
        if (voting == null) {
            Voting voting = new Voting();
            voting.setSchedule(schedule);
            voting.setId(createVotingUseCase.insert(voting));
            this.voting = voting;
        }
        List<Participant> leftToVote = findParticipantUseCase.findAllinGroup(meetingMinutes.getGroup());
        List<Participant> hasVoted = tableViewVotes.getItems().stream().map(v -> v.getParticipant()).collect(Collectors.toList());
        leftToVote.removeAll(hasVoted);
        comboParticipant.addAll(leftToVote);
        loadDataAndShow();
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
        btnEditVote.setDisable(false);
        btnRemoveVote.setDisable(false);
    }

    public void addVote(ActionEvent actionEvent) {
        Participant selecetdParticipant = cbParticipant.getValue();
        if (rdVotingNamed.isSelected() && selecetdParticipant != null) {
            comboParticipant.remove(selecetdParticipant);
            insertNewVote(selecetdParticipant);

        }
        int numberOfMaximumVotes = comboParticipant.size();
        if (rdVotingAnonymous.isSelected() && numberOfMaximumVotes != 0) {
            comboParticipant.remove(0);
            insertNewVote(null);
        }
    }

    private void insertNewVote(Participant participant) {
        Vote vote = new Vote();
        vote.setVoting(this.voting);
        vote.setParticipant(participant);
        vote.setValue(cbVoteValue.getValue());
        vote.setId(createVoteUseCase.insert(vote));
        loadDataAndShow();
    }

    public void editVote(ActionEvent actionEvent) {
        Vote selectedItem = tableViewVotes.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem.setParticipant(cbParticipant.getSelectionModel().getSelectedItem());
            selectedItem.setValue(cbVoteValue.getSelectionModel().getSelectedItem());
            updateVoteUseCase.update(selectedItem);
            loadDataAndShow();
            btnAddVote.setVisible(true);
        }
    }

    public void getContent(MouseEvent mouseEvent) {
        Vote selectedItem = tableViewVotes.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnAddVote.setVisible(false);
            cbParticipant.getSelectionModel().select(selectedItem.getParticipant());
            cbVoteValue.getSelectionModel().select(selectedItem.getValue());
        }
    }

    public void removeVote(ActionEvent actionEvent) {
        Vote selectedItem = tableViewVotes.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            cbParticipant.getItems().add(selectedItem.getParticipant());
            deleteVoteUseCase.delete(selectedItem);
            //unregisterVoteToVoting.unregisterVoteToVoting(selectedItem, voting);
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

    public void setSelectedVoting() {
        if (voting == null) {
            tgVoting.selectToggle(rdNoVoting);
            disableVotingFields();
        } else {
            List<Vote> votes = findVoteUseCase.findByVoting(voting);
            if (!votes.isEmpty() && votes.get(0).getParticipant() == null) {
                tgVoting.selectToggle(rdVotingAnonymous);
                enableAnonymousVotingFields();
            } else if (!votes.isEmpty() && votes.get(0).getParticipant() != null) {
                tgVoting.selectToggle(rdVotingNamed);
                enableNamedVotingFields();
            }
        }
        setVotingIntoView();
    }
}

