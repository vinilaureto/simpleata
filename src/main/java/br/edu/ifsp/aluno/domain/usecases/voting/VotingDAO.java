package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.Optional;

public interface VotingDAO extends DAO<Voting, Integer> {
    Optional<Voting> findBySchedule(Schedule schedule);
}
