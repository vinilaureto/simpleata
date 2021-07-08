package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteScheduleUseCase {
    private ScheduleDAO scheduleDAO;

    public DeleteScheduleUseCase(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public boolean delete(Integer id) {
        if (id == 0 || scheduleDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }
        return scheduleDAO.deleteByKey(id);
    }

    public boolean delete(Schedule schedule) {
        if (schedule == null || scheduleDAO.findOne(schedule.getId()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }
        return scheduleDAO.delete(schedule);
    }
}
