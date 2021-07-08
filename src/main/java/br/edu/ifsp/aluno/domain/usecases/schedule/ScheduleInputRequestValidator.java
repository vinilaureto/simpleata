package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class ScheduleInputRequestValidator extends Validator<Schedule> {
    @Override
    public Notification validate(Schedule schedule) {
        Notification notification = new Notification();

        if (schedule == null) {
            notification.addError("Schedule is null.");
            return notification;
        }
        if (isNullOrEmpty(schedule.getTopic())) {
            notification.addError("Schedule's topic is null or empty.");
        }

        return notification;
    }
}
