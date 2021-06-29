package br.edu.ifsp.aluno.domain.usecases.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface MeetingMinutesDAO extends DAO<MeetingMinutes, String> {
    List<MeetingMinutes> findByGroup(Group group);

    List<MeetingMinutes> findByParticipant(Participant participant);

    Optional<MeetingMinutes> findByIdentifier(String identifier);
}
