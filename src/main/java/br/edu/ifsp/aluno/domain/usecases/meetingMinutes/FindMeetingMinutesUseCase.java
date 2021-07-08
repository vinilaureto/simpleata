package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.group.GroupInputRequestValidator;
import br.edu.ifsp.aluno.domain.usecases.participant.ParticipantInputRequestValidator;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindMeetingMinutesUseCase {
    private MeetingMinutesDAO meetingMinutesDAO;

    public FindMeetingMinutesUseCase(MeetingMinutesDAO meetingMinutesDAO) {
        this.meetingMinutesDAO = meetingMinutesDAO;
    }

    public Optional<MeetingMinutes> findOne(Integer id) {
        if (id == 0)
            throw new IllegalArgumentException("Id can't be 0");
        return meetingMinutesDAO.findOne(id);
    }

    public List<MeetingMinutes> findByTitle(String title){
        if (Validator.isNullOrEmpty(title)) {
            throw new IllegalArgumentException("Title can't be empty or null");
        }
        return meetingMinutesDAO.findByTitle(title);
    }

    public List<MeetingMinutes> findByGroup(Group group) {
        Validator<Group> validator = new GroupInputRequestValidator();
        Notification notification = validator.validate(group);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        return meetingMinutesDAO.findByGroup(group);
    }

    public List<MeetingMinutes> findByParticipant(Participant participant) {
        Validator<Participant> validator = new ParticipantInputRequestValidator();
        Notification notification = validator.validate(participant);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
        return meetingMinutesDAO.findByParticipant(participant);
    }

    public List<MeetingMinutes> findByIdentifier(String identifier) {
        if (Validator.isNullOrEmpty(identifier)) {
            throw new IllegalArgumentException("Identifier can't be empty or null");
        }
        return meetingMinutesDAO.findByIdentifier(identifier);
    }

    public List<MeetingMinutes> findAll() {
        return meetingMinutesDAO.findAll();
    }
}
