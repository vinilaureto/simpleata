package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;

import java.util.List;
import java.util.Optional;

public class FindVotingUseCase {
    private VotingDAO votingDAO;

    public FindVotingUseCase(VotingDAO votingDAO) {
        this.votingDAO = votingDAO;
    }

    public Optional<Voting> findOne(Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("ID can not be 0");
        }
        return votingDAO.findOne(id);
    }

    public List<Voting> findAll() {
        return votingDAO.findAll();
    }

    public Optional<Voting> findBySchedule(Schedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule can not be null");
        }
        return votingDAO.findBySchedule(schedule);
    }
}
