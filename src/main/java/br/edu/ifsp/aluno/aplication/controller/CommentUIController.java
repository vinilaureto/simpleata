package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.controller.utils.ApplicationContext;
import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;

import static br.edu.ifsp.aluno.aplication.main.Main.*;

public class CommentUIController {

    @FXML
    private TextArea txtComment;
    @FXML
    private ComboBox<Participant> cbParticipant;
    @FXML
    private Button btnBackToPreviousScene;
    @FXML
    private Button btnSaveOrUpdate;

    private Comment comment;
    private MeetingMinutes meetingMinutes;
    private Schedule schedule;

    @FXML
    private void initialize() {
//        ApplicationContext applicationContext = ApplicationContext.getInstance();
//        group = applicationContext.getCurrentGroup();
//
        if (meetingMinutes != null) {
            cbParticipant.getItems().setAll(findParticipantUseCase.findAllinGroup(meetingMinutes.getGroup()));
        }
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        if (comment.getId() == null) {
            createCommentUseCase.insert(comment);
        } else {
            updateCommentUseCase.update(comment);
        }
        WindowLoader.setRoot("ScheduleUI");
        ScheduleUIController controller = (ScheduleUIController) WindowLoader.getController();
        controller.setMeetingMinutesAndSchedule(meetingMinutes, schedule, UIMode.UPDATE);
//        controller.setSchedule(schedule, UIMode.UPDATE);
//        controller.setMeetingMinutes(meetingMinutes, UIMode.UPDATE);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ScheduleUI");
        ScheduleUIController controller = (ScheduleUIController) WindowLoader.getController();
        controller.setMeetingMinutesAndSchedule(meetingMinutes, schedule, UIMode.UPDATE);
//        controller.setSchedule(schedule, UIMode.UPDATE);
//        controller.setMeetingMinutes(meetingMinutes, UIMode.UPDATE);
    }

    public void setComment(Comment comment, UIMode mode) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment can not be null.");
        }

        this.comment = comment;
        setEntityIntoView();

        if (mode == UIMode.VIEW) {
            configureViewMode();
        }
    }

    private void getEntityFromView() {
        if (comment == null) {
            comment = new Comment();
        }

        comment.setMessage(txtComment.getText());
        comment.setParticipant(cbParticipant.getValue());
        comment.setSchedule(schedule);
    }

    private void setEntityIntoView() {
        txtComment.setText(comment.getMessage());
        cbParticipant.setValue(comment.getParticipant());
    }

    private void configureViewMode() {
        btnBackToPreviousScene.setLayoutX(btnSaveOrUpdate.getLayoutX());
        btnBackToPreviousScene.setLayoutY(btnSaveOrUpdate.getLayoutY());
        btnBackToPreviousScene.setText("Fechar");

        btnSaveOrUpdate.setVisible(false);

        txtComment.setDisable(true);
        cbParticipant.setDisable(true);
    }

    public void setMeetingMinutes(MeetingMinutes meetingMinutes) {
        if (meetingMinutes == null) {
            throw new IllegalArgumentException("Meeting minutes can not be null.");
        }
        this.meetingMinutes = meetingMinutes;
        cbParticipant.getItems().setAll(findParticipantUseCase.findAllinGroup(meetingMinutes.getGroup()));

    }

    public void setSchedule(Schedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule can not be null.");
        }
        this.schedule = schedule;
    }
}
