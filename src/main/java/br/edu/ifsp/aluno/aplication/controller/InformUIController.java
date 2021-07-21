package br.edu.ifsp.aluno.aplication.controller;

import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

import static br.edu.ifsp.aluno.aplication.main.Main.*;

public class InformUIController {
    @FXML
    private TextArea txtInform;
    @FXML
    private Button btnSaveOrUpdate;
    @FXML
    private Button btnBackToPreviousScene;

    private Inform inform;
    private MeetingMinutes meetingMinutes;

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        if (inform.getId() == null) {
            createInformUseCase.insert(inform);
        } else {
            updateInformUseCase.update(inform);
        }
        System.out.println(meetingMinutes.getTitle());
        WindowLoader.setRoot("MeetingMinutesUI");
        MeetingMinutesUIController controller = (MeetingMinutesUIController) WindowLoader.getController();
        controller.setMeetingMinutes(meetingMinutes, UIMode.UPDATE);
    }

    private void getEntityFromView() {
        if (inform == null) {
            inform = new Inform();
        }
        inform.setDescription(txtInform.getText());
        inform.setMeetingMinutes(meetingMinutes);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MeetingMinutesUI");
        MeetingMinutesUIController controller = (MeetingMinutesUIController) WindowLoader.getController();
        controller.setMeetingMinutes(meetingMinutes, UIMode.UPDATE);
    }

    public void setInform(Inform inform, UIMode mode) {
        if (inform == null) {
            throw new IllegalArgumentException("Inform can not be null.");
        }
        this.inform = inform;
        setEntityIntoView();
    }

    private void setEntityIntoView() {
        txtInform.setText(inform.getDescription());
    }

    public void setMeetingMinutes(MeetingMinutes meetingMinutes) {
        if (meetingMinutes == null) {
            throw new IllegalArgumentException("Meeting Minutes can not be null.");
        }
        this.meetingMinutes = meetingMinutes;
    }
}
