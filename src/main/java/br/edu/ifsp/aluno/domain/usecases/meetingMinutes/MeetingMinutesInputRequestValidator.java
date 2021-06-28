package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class MeetingMinutesInputRequestValidator extends Validator<MeetingMinutes> {
    @Override
    public Notification validate(MeetingMinutes meetingMinutes) {
        Notification notification = new Notification();

        if (meetingMinutes == null) {
            notification.addError("Meeting Minutes is null.");
            return notification;
        }
        if (isNullOrEmpty(meetingMinutes.getIdentifier())) {
            notification.addError("Meeting minutes' identifier is null or empty.");
        }
        if (isNullOrEmpty(meetingMinutes.getTitle())) {
            notification.addError("Meeting minutes' title is null or empty.");
        }

        return notification;
    }
}
