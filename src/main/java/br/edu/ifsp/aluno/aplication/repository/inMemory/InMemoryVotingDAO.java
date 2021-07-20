package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.voting.VotingDAO;

import java.util.*;

public class InMemoryVotingDAO implements VotingDAO {
    private static final Map<Integer, Voting> db = new LinkedHashMap<>();
    private static int votingIdCounter;

    @Override
    public Integer insert(Voting voting) {
        votingIdCounter++;
        voting.setId(votingIdCounter);
        db.put(votingIdCounter, voting);
        return votingIdCounter;
    }

    @Override
    public Optional<Voting> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Voting> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Voting voting) {
        Integer id = voting.getId();
        if (db.containsKey(id)) {
            db.replace(id, voting);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Voting voting) {
        return deleteByKey(voting.getId());
    }

    @Override
    public Optional<Voting> findBySchedule(Schedule schedule) {
        return Optional.empty();
    }
}
