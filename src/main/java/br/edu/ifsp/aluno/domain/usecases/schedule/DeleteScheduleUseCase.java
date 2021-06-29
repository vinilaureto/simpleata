package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteScheduleUseCase {
    private ScheduleDAO scheduleDAO;

    public DeleteScheduleUseCase(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public boolean delete(String topic) {
        if (topic == null || scheduleDAO.findOne(topic).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }
        return scheduleDAO.deleteByKey(topic);
    }

    public boolean delete(Schedule schedule) {
        if (schedule == null || scheduleDAO.findOne(schedule.getTopic()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }
        return scheduleDAO.delete(schedule);
    }
}
