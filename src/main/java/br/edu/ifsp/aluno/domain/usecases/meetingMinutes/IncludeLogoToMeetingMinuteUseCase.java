package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;

public class IncludeLogoToMeetingMinuteUseCase {
    private UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;

    public IncludeLogoToMeetingMinuteUseCase(UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase) {
        this.updateMeetingMinutesUseCase = updateMeetingMinutesUseCase;
    }

    public boolean includeLogo(String logo, MeetingMinutes meetingMinutes) {
        meetingMinutes.setLogo(logo);
        return updateMeetingMinutesUseCase.update(meetingMinutes);
    }
}
