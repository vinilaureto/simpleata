package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutesStatus;

public class ChangeStatusMeetingMinutesUseCase {
    private UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;

    public ChangeStatusMeetingMinutesUseCase(UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase) {
        this.updateMeetingMinutesUseCase = updateMeetingMinutesUseCase;
    }

    public boolean changeStatus(MeetingMinutesStatus status, MeetingMinutes meetingMinutes) {
        if (status == MeetingMinutesStatus.OPENED) {
            meetingMinutes.startMeetingMinute();
        } else if (status == MeetingMinutesStatus.CLOSED) {
            meetingMinutes.closeMeetingMinute();
        }

        return updateMeetingMinutesUseCase.update(meetingMinutes);
    }
}
