package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.usecases.vote.VoteDAO;

import java.util.*;

public class InMemoryVoteDAO implements VoteDAO {
    private static final Map<Integer, Vote> db = new LinkedHashMap<>();
    private static int voteIdCounter;

    @Override
    public Integer insert(Vote vote) {
        voteIdCounter++;
        vote.setId(voteIdCounter);
        db.put(voteIdCounter, vote);
        return voteIdCounter;
    }

    @Override
    public Optional<Vote> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Vote> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Vote vote) {
        Integer id = vote.getId();
        if (db.containsKey(id)) {
            db.replace(id, vote);
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
    public boolean delete(Vote vote) {
        return deleteByKey(vote.getId());
    }
}
