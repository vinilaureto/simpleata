package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateParticipantUseCase {
    private ParticipantDAO participantDAO;

    public UpdateParticipantUseCase(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public boolean update(Participant participant) {
        Validator<Participant> validator = new ParticipantInputRequestValidator();
        Notification notification = validator.validate(participant);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        Integer id = participant.getId();
        if (participantDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }

        return participantDAO.update(participant);
    }
}
