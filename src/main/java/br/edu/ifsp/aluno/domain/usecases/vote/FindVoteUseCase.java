package br.edu.ifsp.aluno.domain.usecases.vote;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;

import java.util.List;
import java.util.Optional;

public class FindVoteUseCase {
    private VoteDAO voteDAO;

    public FindVoteUseCase(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    public Optional<Vote> findOne(Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("ID can not be 0");
        }
        return voteDAO.findOne(id);
    }

    public List<Vote> findAll() {
        return voteDAO.findAll();
    }
}
