package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutesStatus;

public class ChangeStatusMeetingMinutesUseCase {
    private UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;

    public ChangeStatusMeetingMinutesUseCase(UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase) {
        this.updateMeetingMinutesUseCase = updateMeetingMinutesUseCase;
    }

    public boolean openMeetingMinutes(MeetingMinutes meetingMinutes) {
        if (meetingMinutes.getStatus() == MeetingMinutesStatus.OPENED) {
            throw new IllegalArgumentException("Meeting minutes is already opened");
        }

        if (meetingMinutes.getStatus() == MeetingMinutesStatus.CLOSED) {
            throw new IllegalArgumentException("Meeting minutes is already closed and can't be updated");
        }

        meetingMinutes.startMeetingMinute();
        return updateMeetingMinutesUseCase.update(meetingMinutes);
    }

    public boolean closeMeetingMinutes(MeetingMinutes meetingMinutes) {
        if (meetingMinutes.getStatus() == MeetingMinutesStatus.CLOSED) {
            throw new IllegalArgumentException("Meeting minutes is already closed");
        }

        meetingMinutes.closeMeetingMinute();
        return updateMeetingMinutesUseCase.update(meetingMinutes);
    }
}
