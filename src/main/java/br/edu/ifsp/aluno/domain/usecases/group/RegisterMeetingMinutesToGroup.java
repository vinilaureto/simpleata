package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.FindMeetingMinutesUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class RegisterMeetingMinutesToGroup {
    private FindMeetingMinutesUseCase findMeetingMinutesUseCase;
    private FindGroupUseCase findGroupUseCase;
    private UpdateGroupUseCase updateGroupUseCase;

    public RegisterMeetingMinutesToGroup(FindMeetingMinutesUseCase findMeetingMinutesUseCase, FindGroupUseCase findGroupUseCase, UpdateGroupUseCase updateGroupUseCase) {
        this.findMeetingMinutesUseCase = findMeetingMinutesUseCase;
        this.findGroupUseCase = findGroupUseCase;
        this.updateGroupUseCase = updateGroupUseCase;
    }

    public boolean registerMeetingMinutesToGroup(MeetingMinutes meetingMinutes, Group group) {
        if (findMeetingMinutesUseCase.findOne(meetingMinutes.getTitle()).isEmpty()) {
            throw new EntityNotFoundException("Meeting minutes not found");
        }

        if (findGroupUseCase.findOne(group.getName()).isEmpty()) {
            throw new EntityNotFoundException("Group not found");
        }

        group.addMeetingMinutes(meetingMinutes);
        return updateGroupUseCase.update(group);
    }
}
