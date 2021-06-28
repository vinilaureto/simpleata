package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateMeetingMinutesUseCase {
    private MeetingMinutesDAO meetingMinutesDAO;

    public UpdateMeetingMinutesUseCase(MeetingMinutesDAO meetingMinutesDAO) {
        this.meetingMinutesDAO = meetingMinutesDAO;
    }

    public boolean update(MeetingMinutes meetingMinutes) {
        Validator<MeetingMinutes> validator = new MeetingMinutesInputRequestValidator();
        Notification notification = validator.validate(meetingMinutes);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String title = meetingMinutes.getTitle();
        if (meetingMinutesDAO.findOne(title).isEmpty()) {
            throw new EntityNotFoundException("Meeting minutes not found");
        }
        // todo: Precisa verificar se a ata do banco tem status diferente de CLOSED

        return meetingMinutesDAO.update(meetingMinutes);
    }
}
