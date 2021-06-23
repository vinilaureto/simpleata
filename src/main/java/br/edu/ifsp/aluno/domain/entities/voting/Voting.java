package br.edu.ifsp.aluno.domain.entities.voting;


import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.vote.VoteValue;

import java.util.ArrayList;
import java.util.List;

public class Voting {
    private VoteResult result;
    private List<Vote> votes = new ArrayList<>();

    public Voting(VoteResult result, List<Vote> votes) {
        this.result = result;
        this.votes = votes;
    }

    public void registerVote(VoteValue value) {
        Vote vote = new Vote(value);
        votes.add(vote);
    }

    public void registerVote(VoteValue value, Participant participant) {
        Vote vote = new Vote(value, participant);
        votes.add(vote);
    }

    public void resolveVoting() {
        Integer result = 0;
        for(Vote vote : votes) {
            if (vote.getValue() == VoteValue.POSITIVE) {
                result++;
            } else if (vote.getValue() == VoteValue.NEGATIVE) {
                result--;
            }
        }

        if (result > 0) {
            this.result = VoteResult.APPROVED;
        } else if (result < 0) {
            this.result = VoteResult.REJECT;
        } else {
            this.result = VoteResult.DRAW;
        }
    }

    public VoteResult getResult() {
        return result;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "Voting{" +
                "result=" + result +
                ", votes=" + votes +
                '}';
    }
}
