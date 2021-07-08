package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.inform.FindInformUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class IncludeInformToMeetingMinutesUseCase {
    private FindInformUseCase findInformUseCase;
    private FindMeetingMinutesUseCase findMeetingMinutesUseCase;
    private UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;

    public IncludeInformToMeetingMinutesUseCase(FindInformUseCase findInformUseCase, FindMeetingMinutesUseCase findMeetingMinutesUseCase, UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase) {
        this.findInformUseCase = findInformUseCase;
        this.findMeetingMinutesUseCase = findMeetingMinutesUseCase;
        this.updateMeetingMinutesUseCase = updateMeetingMinutesUseCase;
    }

    public boolean includeInformToMeetingMinutes(Inform inform, MeetingMinutes meetingMinutes) {
        if (findInformUseCase.findOne(inform.getId()).isEmpty()) {
            throw new EntityNotFoundException("Inform not found");
        }

        if (findMeetingMinutesUseCase.findOne(meetingMinutes.getId()).isEmpty()) {
            throw new EntityNotFoundException("Meeting minutes not found");
        }

        meetingMinutes.addInform(inform);
        return updateMeetingMinutesUseCase.update(meetingMinutes);
    }
}
