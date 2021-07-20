package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;

public interface ScheduleDAO extends DAO<Schedule, Integer> {
    List<Schedule> findByMeetingMinutes(MeetingMinutes meetingMinutes);
}
