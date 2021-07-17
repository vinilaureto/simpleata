package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.participant.FindParticipantUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

import java.util.Optional;

public class RemoveParticipantFromGroupUseCase {
    private FindGroupUseCase findGroupUseCase;
    private FindParticipantUseCase findParticipantUseCase;
    private GroupDAO groupDAO;

    public RemoveParticipantFromGroupUseCase(FindGroupUseCase findGroupUseCase, FindParticipantUseCase findParticipantUseCase,  GroupDAO groupDAO) {
        this.findGroupUseCase = findGroupUseCase;
        this.findParticipantUseCase = findParticipantUseCase;
        this.groupDAO = groupDAO;
    }

    public boolean removeParticipantFromGroup(Participant participant, Group group) {
        Optional<Group> savedGroup = findGroupUseCase.findOne(group.getId());

        if (savedGroup.isEmpty()) {
            throw new EntityNotFoundException("Group not found");
        }

        if (findParticipantUseCase.findOne(participant.getId()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found");
        }

        group.removeParticipant(participant);
        return groupDAO.removeParticipantFromGroup(participant, group);
    }
}
