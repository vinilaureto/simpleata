package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface MeetingMinutesDAO extends DAO<MeetingMinutes, Integer> {
    List<MeetingMinutes> findByTitle(String title);

    List<MeetingMinutes> findByGroup(Group group);

    List<MeetingMinutes> findByParticipant(Participant participant);

    List<MeetingMinutes> findByIdentifier(String identifier);
}
