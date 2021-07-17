package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.controller.utils.ApplicationContext;
import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.group.Group;
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
    private Group group;

    @FXML
    private void initialize() {
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        group = applicationContext.getCurrentGroup();

        bindTableViewToItemsList();
        bindColumnsToValueSource();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableDataComment = FXCollections.observableArrayList();
        tableViewComments.setItems(tableDataComment);

        tableDataVote = FXCollections.observableArrayList();
        tableViewVotes.setItems(tableDataVote);

        // Radio goes here
    }

    private void bindColumnsToValueSource() {
        cCommentParticipantName.setCellValueFactory(new PropertyValueFactory<>("participantName"));
        cCommentComment.setCellValueFactory(new PropertyValueFactory<>("message"));

        cVoteParticipantName.setCellValueFactory(new PropertyValueFactory<>("participantName"));
        cVoteVote.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    private void loadDataAndShow() {
        List<Comment> commentList = schedule.getComments(); // erro aqui
        tableDataComment.clear();
        tableDataComment.addAll(commentList);

        if (schedule.getVoting() != null) {
            List<Vote> voteList = schedule.getVoting().getVotes();
            tableDataVote.clear();
            tableDataVote.addAll(voteList);
        }
    }

    public void newComment(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CommentUI");
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
        }
    }

    public void removeComment(ActionEvent actionEvent) {
    }

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



    public void saveOrUpdate(ActionEvent actionEvent) {
        getEntityFromView();
        if (schedule.getId() == null) {
            createScheduleUseCase.insert(schedule);
        } else {
            updateScheduleUseCase.update(schedule);
        }
        // mandar o window para onde?
    }

    public void getEntityFromView() {
        if (schedule == null) {
            schedule = new Schedule();
        } else {
            // fazer os sets no schedule aqui
        }
    }

    public void setSchedule(Schedule schedule, UIMode uiMode) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule can not be null.");
        }

        this.schedule = schedule;
        setEntityIntoView();
    }



    private void setEntityIntoView() {

    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MeetingMinutesUI");
    }


}
