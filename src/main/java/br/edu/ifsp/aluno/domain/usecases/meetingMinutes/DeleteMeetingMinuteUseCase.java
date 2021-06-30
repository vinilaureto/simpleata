package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutesStatus;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

import java.util.Optional;

public class DeleteMeetingMinuteUseCase {
    private MeetingMinutesDAO meetingMinutesDAO;

    public DeleteMeetingMinuteUseCase(MeetingMinutesDAO meetingMinutesDAO) {
        this.meetingMinutesDAO = meetingMinutesDAO;
    }

    public boolean Delete(String title) {
        if (Validator.isNullOrEmpty(title)) {
            throw new EntityNotFoundException("Title is null or empty");
        }

        MeetingMinutes meetingMinutes = meetingMinutesDAO.findOne(title)
                .orElseThrow(() -> new EntityNotFoundException("Can't find meeting minute with title" + title));

        if (meetingMinutes.getStatus() == MeetingMinutesStatus.CLOSED) {
            throw new IllegalArgumentException("Can't delete a closed meeting minutes");
        }

        return meetingMinutesDAO.deleteByKey(title);
    }

    public boolean Delete (MeetingMinutes meetingMinutes) {
        if (meetingMinutes == null || meetingMinutesDAO.findOne(meetingMinutes.getTitle()).isEmpty()) {
            throw new EntityNotFoundException("Meeting minutes not found");
        }
        return meetingMinutesDAO.delete(meetingMinutes);
    }

    //  esse use case esta um pouco confuso em relação ao status da ata
}
