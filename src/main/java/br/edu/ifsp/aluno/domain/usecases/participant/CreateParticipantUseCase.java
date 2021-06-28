package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateParticipantUseCase {
    private ParticipantDAO participantDAO;

    public CreateParticipantUseCase(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public String insert(Participant participant) {
        Validator<Participant> validator = new ParticipantInputRequestValidator();
        Notification notification = validator.validate(participant);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String email = participant.getEmail();
        if (participantDAO.findOne(email).isPresent()) {
            throw new EntityAlreadyExistsException("This e-mail is already in use.");
        }

        return participantDAO.insert(participant);
    }
}
