package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindScheduleUseCase {
    private ScheduleDAO scheduleDAO;

    public FindScheduleUseCase(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public Optional<Schedule> findOne(String topic) {
        if (Validator.isNullOrEmpty(topic)) {
            throw new IllegalArgumentException("Topic can not be null.");
        }
        return scheduleDAO.findOne(topic);
    }

    public List<Schedule> findAll() {
        return scheduleDAO.findAll();
    }
}
