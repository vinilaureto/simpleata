package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindParticipantUseCase {
    private ParticipantDAO participantDAO;

    public FindParticipantUseCase(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public Optional<Participant> findOne(String email) {
        if (Validator.isNullOrEmpty(email)) {
            throw new IllegalArgumentException("E-mail can not be null.");
        }
        return participantDAO.findOne(email);
    }

    public List<Participant> findAll() {
        return participantDAO.findAll();
    }
}
