package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.usecases.vote.VoteDAO;

import java.util.List;
import java.util.Optional;

public class SqliteVoteDAO implements VoteDAO {
    @Override
    public Integer insert(Vote type) {
        return null;
    }

    @Override
    public Optional<Vote> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Vote> findAll() {
        return null;
    }

    @Override
    public boolean update(Vote type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Vote type) {
        return false;
    }
}
