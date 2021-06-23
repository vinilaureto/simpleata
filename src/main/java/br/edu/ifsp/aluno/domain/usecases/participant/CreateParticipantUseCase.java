package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;

public class CreateParticipantUseCase {
    private ParticipantDAO participantDAO;

    public CreateParticipantUseCase(ParticipantDAO participantDAO) {
        this.participantDAO = participantDAO;
    }

    public Integer insert(Participant participant) {
        //todo: fazer validação

        String email = participant.getEmail();
        if (participantDAO.findByEmail(email).isPresent()) {
            // todo: entidade já existe
        }

        return participantDAO.create(participant);
    }
}
