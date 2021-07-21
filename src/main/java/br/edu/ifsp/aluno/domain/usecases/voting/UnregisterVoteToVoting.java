package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.vote.VoteDAO;

public class UnregisterVoteToVoting {
    private VotingDAO votingDAO;
    private VoteDAO voteDAO;

    public UnregisterVoteToVoting(VotingDAO votingDAO, VoteDAO voteDAO) {
        this.votingDAO = votingDAO;
        this.voteDAO = voteDAO;
    }

    public boolean unregisterVoteToVoting(Vote vote, Voting voting) {
        if (voteDAO.findOne(vote.getId()).isEmpty()) {
            throw new EntityNotFoundException("Vote not found");
        }

        if (votingDAO.findOne(voting.getId()).isEmpty()) {
            throw new EntityNotFoundException("Voting not found");
        }

        voting.unregisterVote(vote);
        return votingDAO.update(voting);
    }
}
