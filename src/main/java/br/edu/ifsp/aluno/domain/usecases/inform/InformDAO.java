package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;

public interface InformDAO extends DAO<Inform, Integer> {
    List<Inform> findByMeetingMinutes(MeetingMinutes meetingMinutes);
}
