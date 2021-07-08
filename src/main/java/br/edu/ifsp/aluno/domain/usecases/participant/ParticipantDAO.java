package br.edu.ifsp.aluno.domain.usecases.participant;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface ParticipantDAO extends DAO<Participant, Integer> {
    Optional<Participant> findByEmail(String email);

    List<Participant> findByName(String name);

    List<Participant> findByTitle(String title);
}
