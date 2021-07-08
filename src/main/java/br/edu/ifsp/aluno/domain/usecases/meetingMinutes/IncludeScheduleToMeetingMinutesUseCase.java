package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.schedule.FindScheduleUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class IncludeScheduleToMeetingMinutesUseCase {
    private FindScheduleUseCase findScheduleUseCase;
    private FindMeetingMinutesUseCase findMeetingMinutesUseCase;
    private UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;

    public IncludeScheduleToMeetingMinutesUseCase(FindScheduleUseCase findScheduleUseCase, FindMeetingMinutesUseCase findMeetingMinutesUseCase, UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase) {
        this.findScheduleUseCase = findScheduleUseCase;
        this.findMeetingMinutesUseCase = findMeetingMinutesUseCase;
        this.updateMeetingMinutesUseCase = updateMeetingMinutesUseCase;
    }

    public boolean includeScheduleToMeetingMinutes(Schedule schedule, MeetingMinutes meetingMinutes) {
        if (findScheduleUseCase.findOne(schedule.getId()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found");
        }

        if (findMeetingMinutesUseCase.findOne(meetingMinutes.getId()).isEmpty()) {
            throw new EntityNotFoundException("Meeting not found");
        }

        meetingMinutes.addSchedule(schedule);
        return updateMeetingMinutesUseCase.update(meetingMinutes);
    }
}
