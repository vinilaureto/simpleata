package br.edu.ifsp.aluno.domain.entities.voting;


import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.vote.VoteValue;

import java.util.ArrayList;
import java.util.List;

public class Voting {
    private Integer id;
    private VoteResult result;
    private List<Vote> votes = new ArrayList<>();

    public Voting() {
    }

    public Voting(VoteResult result, List<Vote> votes) {
        this.result = result;
        this.votes = votes;
    }

    public Voting(Integer id, VoteResult result, List<Vote> votes) {
        this.id = id;
        this.result = result;
        this.votes = votes;
    }

    public void registerVote(Vote vote) {
        votes.add(vote);
    }

    public void unregisterVote(Vote vote){
        votes.remove(vote);
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
            this.result = VoteResult.REJECTED;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Voting{" +
                "id=" + id +
                ", result=" + result +
                ", votes=" + votes +
                '}';
    }
}
