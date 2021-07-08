package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.participant.ParticipantInputRequestValidator;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateScheduleUseCase {
    private ScheduleDAO scheduleDAO;

    public CreateScheduleUseCase(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public Integer insert(Schedule schedule) {
        Validator<Schedule> validator = new ScheduleInputRequestValidator();
        Notification notification = validator.validate(schedule);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        return scheduleDAO.insert(schedule);
    }
}
