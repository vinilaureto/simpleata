package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.participant.FindParticipantUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class AddParticipantToGroupUseCase {
    private UpdateGroupUseCase updateGroupUseCase;
    private FindGroupUseCase findGroupUseCase;
    private FindParticipantUseCase findParticipantUseCase;

    public AddParticipantToGroupUseCase(UpdateGroupUseCase updateGroupUseCase, FindGroupUseCase findGroupUseCase, FindParticipantUseCase findParticipantUseCase) {
        this.updateGroupUseCase = updateGroupUseCase;
        this.findGroupUseCase = findGroupUseCase;
        this.findParticipantUseCase = findParticipantUseCase;
    }

    public boolean addParticipantToGroup(Participant participant, Group group) {
        if (findGroupUseCase.findOne(group.getName()).isEmpty()) {
            throw new EntityNotFoundException("Group not found");
        }

        if (findParticipantUseCase.findOne(participant.getEmail()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found");
        }

        group.addParticipant(participant);
        return updateGroupUseCase.update(group);
    }
}
