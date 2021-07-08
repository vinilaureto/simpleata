package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteParticipantUseCase {
    private ParticipantDAO participantDAO;

    public DeleteParticipantUseCase(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public boolean delete(Integer id) {
        if (id == 0 || participantDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }
        return participantDAO.deleteByKey(id);
    }

    public boolean delete(Participant participant) {
        if (participant == null || participantDAO.findOne(participant.getId()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }
        return participantDAO.delete(participant);
    }
}
