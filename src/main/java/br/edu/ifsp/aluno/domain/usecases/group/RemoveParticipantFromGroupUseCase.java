package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.participant.FindParticipantUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

import java.util.Optional;

public class RemoveParticipantFromGroupUseCase {
    private FindGroupUseCase findGroupUseCase;
    private FindParticipantUseCase findParticipantUseCase;
    private UpdateGroupUseCase updateGroupUseCase;

    public RemoveParticipantFromGroupUseCase(FindGroupUseCase findGroupUseCase, FindParticipantUseCase findParticipantUseCase, UpdateGroupUseCase updateGroupUseCase) {
        this.findGroupUseCase = findGroupUseCase;
        this.findParticipantUseCase = findParticipantUseCase;
        this.updateGroupUseCase = updateGroupUseCase;
    }

    public boolean removeParticipantFromGroup(Participant participant, Group group) {
        Optional<Group> savedGroup = findGroupUseCase.findOne(group.getName());

        if (savedGroup.isEmpty()) {
            throw new EntityNotFoundException("Group not found");
        }

        if (findParticipantUseCase.findOne(participant.getEmail()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found");
        }

        group.removeParticipant(participant);
        return updateGroupUseCase.update(group);
    }
}
