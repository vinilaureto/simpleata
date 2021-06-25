package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteParticipantUseCase {
    private ParticipantDAO participantDAO;

    public DeleteParticipantUseCase(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public boolean delete(String email) {
        if (email == null || participantDAO.findOne(email).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }
        return participantDAO.deleteByKey(email);
    }

    public boolean delete(Participant participant) {
        if (participant == null || participantDAO.findOne(participant.getEmail()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }
        return participantDAO.delete(participant);
    }
}
