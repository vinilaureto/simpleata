package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.FindMeetingMinutesUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class UnregisterMeetingMinutesToGroup {
    private FindMeetingMinutesUseCase findMeetingMinutesUseCase;
    private FindGroupUseCase findGroupUseCase;
    private UpdateGroupUseCase updateGroupUseCase;

    public UnregisterMeetingMinutesToGroup(FindMeetingMinutesUseCase findMeetingMinutesUseCase, FindGroupUseCase findGroupUseCase, UpdateGroupUseCase updateGroupUseCase) {
        this.findMeetingMinutesUseCase = findMeetingMinutesUseCase;
        this.findGroupUseCase = findGroupUseCase;
        this.updateGroupUseCase = updateGroupUseCase;
    }

    public boolean unregisterMeetingMinutesToGroup(MeetingMinutes meetingMinutes, Group group) {
        if (findMeetingMinutesUseCase.findOne(meetingMinutes.getId()).isEmpty()) {
            throw new EntityNotFoundException("Meeting minutes not found");
        }

        if (findGroupUseCase.findOne(group.getId()).isEmpty()) {
            throw new EntityNotFoundException("Group not found");
        }

        group.removeMeetingMinutes(meetingMinutes);
        return updateGroupUseCase.update(group);
    }
}
