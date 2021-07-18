package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.voting.VotingDAO;

import java.util.List;
import java.util.Optional;

public class SqliteVotingDAO implements VotingDAO {
    @Override
    public Integer insert(Voting type) {
        return null;
    }

    @Override
    public Optional<Voting> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Voting> findAll() {
        return null;
    }

    @Override
    public boolean update(Voting type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Voting type) {
        return false;
    }
}
