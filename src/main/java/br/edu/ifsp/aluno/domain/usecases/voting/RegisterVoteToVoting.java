package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.vote.FindVoteUseCase;

public class RegisterVoteToVoting {
    private FindVoteUseCase findVoteUseCase;
    private FindVotingUseCase findVotingUseCase;
    private UpdateVotingUseCase updateVotingUseCase;

    public RegisterVoteToVoting(FindVoteUseCase findVoteUseCase) {
        this.findVoteUseCase = findVoteUseCase;
    }

    public boolean registerVoteToVoting(Vote vote, Voting voting, Participant participant) {
        if (findVoteUseCase.findOne(vote.getId()).isEmpty()) {
            throw new EntityNotFoundException("Vote not found");
        }

        if (findVotingUseCase.findOne(voting.getId()).isEmpty()) {
            throw new EntityNotFoundException("Voting not found");
        }

        voting.registerVote(vote);
        return updateVotingUseCase.update(voting);
    }
}
