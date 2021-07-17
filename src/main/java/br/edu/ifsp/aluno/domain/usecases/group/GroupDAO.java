package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.Optional;

public interface GroupDAO extends DAO<Group, Integer> {
    Optional<Group> findByName(String name);
    boolean addParticipantToGroup(Participant participant, Group group);
    boolean removeParticipantFromGroup(Participant participant, Group group);
}
