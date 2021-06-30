package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;

public interface ParticipantDAO extends DAO<Participant, String> {
    List<Participant> findByName(String name);

    List<Participant> findByTitle(String title);
}
