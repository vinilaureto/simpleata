package br.edu.ifsp.aluno.domain.usecases.vote;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteVoteUseCase {
    private VoteDAO voteDAO;

    public DeleteVoteUseCase(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    public boolean delete(Integer id) {
        if (id == null || voteDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Vote not found.");
        }
        return voteDAO.deleteByKey(id);
    }

    public boolean delete(Vote vote) {
        if (vote == null || voteDAO.findOne(vote.getId()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }
        return voteDAO.delete(vote);
    }
}
