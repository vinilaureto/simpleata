package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateScheduleUseCase {
    private ScheduleDAO scheduleDAO;

    public UpdateScheduleUseCase(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public boolean update(Schedule schedule) {
        Validator<Schedule> validator = new ScheduleInputRequestValidator();
        Notification notification = validator.validate(schedule);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String topic = schedule.getTopic();
        if (scheduleDAO.findOne(topic).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }

        return scheduleDAO.update(schedule);
    }
}
