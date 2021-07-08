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

    public boolean Delete(Integer id) {
        if (id == 0) {
            throw new EntityNotFoundException("Id is null or empty");
        }

        MeetingMinutes meetingMinutes = meetingMinutesDAO.findOne(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find meeting minute with Id" + id));

        if (meetingMinutes.getStatus() == MeetingMinutesStatus.CLOSED) {
            throw new IllegalArgumentException("Can't delete a closed meeting minutes");
        }

        return meetingMinutesDAO.deleteByKey(id);
    }

    public boolean Delete (MeetingMinutes meetingMinutes) {
        if (meetingMinutes == null || meetingMinutesDAO.findOne(meetingMinutes.getId()).isEmpty()) {
            throw new EntityNotFoundException("Meeting minutes not found");
        }
        return meetingMinutesDAO.delete(meetingMinutes);
    }

}
