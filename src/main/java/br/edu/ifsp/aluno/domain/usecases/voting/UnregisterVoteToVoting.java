package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.vote.FindVoteUseCase;

public class UnregisterVoteToVoting {
    private FindVoteUseCase findVoteUseCase;
    private FindVotingUseCase findVotingUseCase;
    private UpdateVotingUseCase updateVotingUseCase;

    public UnregisterVoteToVoting(FindVoteUseCase findVoteUseCase, FindVotingUseCase findVotingUseCase, UpdateVotingUseCase updateVotingUseCase) {
        this.findVoteUseCase = findVoteUseCase;
        this.findVotingUseCase = findVotingUseCase;
        this.updateVotingUseCase = updateVotingUseCase;
    }

    public boolean unregisterVoteToVoting(Vote vote, Voting voting) {
        if (findVoteUseCase.findOne(vote.getId()).isEmpty()) {
            throw new EntityNotFoundException("Vote not found");
        }

        if (findVotingUseCase.findOne(voting.getId()).isEmpty()) {
            throw new EntityNotFoundException("Voting not found");
        }

        voting.unregisterVote(vote);
        return updateVotingUseCase.update(voting);
    }
}
