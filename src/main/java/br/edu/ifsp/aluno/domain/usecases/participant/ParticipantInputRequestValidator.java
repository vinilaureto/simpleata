package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class ParticipantInputRequestValidator extends Validator<Participant> {
    @Override
    public Notification validate(Participant participant) {
        Notification notification = new Notification();

        if (participant == null) {
            notification.addError("Participant is null.");
            return notification;
        }
        if (isNullOrEmpty(participant.getName())) {
            notification.addError("Name is null or empty.");
        }
        if (isNullOrEmpty(participant.getEmail())) {
            notification.addError("E-mail is null or empty.");
        }
        if (isNullOrEmpty(participant.getTitle())) {
            notification.addError("Title is null or empty.");
        }

        return notification;
    }
}
