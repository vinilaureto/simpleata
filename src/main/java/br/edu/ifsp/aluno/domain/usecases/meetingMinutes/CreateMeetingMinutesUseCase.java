package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateMeetingMinutesUseCase {
    private MeetingMinutesDAO meetingMinutesDAO;

    public CreateMeetingMinutesUseCase(MeetingMinutesDAO meetingMinutesDAO) {
        this.meetingMinutesDAO = meetingMinutesDAO;
    }

    public String insert(MeetingMinutes meetingMinutes) {
        Validator<MeetingMinutes> validator = new MeetingMinutesInputRequestValidator();
        Notification notification = validator.validate(meetingMinutes);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String title = meetingMinutes.getTitle();
        if (meetingMinutesDAO.findOne(title).isPresent()) {
            throw new EntityAlreadyExistsException("This meeting minutes' title is already in use");
        }
        return meetingMinutesDAO.insert(meetingMinutes);
    }
}
