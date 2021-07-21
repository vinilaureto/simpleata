package br.edu.ifsp.aluno.domain.entities.voting;


import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.vote.VoteValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voting {
    private Integer id;
    private VoteResult result;
    private List<Vote> votes = new ArrayList<>();
    private Schedule schedule;

    public Voting() {
        this.result = VoteResult.DRAW;
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

    public Voting(Integer id, VoteResult result, Schedule schedule) {
        this.id = id;
        this.result = result;
        this.schedule = schedule;
    }

    public Voting(Integer id, VoteResult result, List<Vote> votes, Schedule schedule) {
        this.id = id;
        this.result = result;
        this.votes = votes;
        this.schedule = schedule;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Voting{" +
                "id=" + id +
                ", result=" + result +
                ", votes=" + votes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voting voting = (Voting) o;
        return Objects.equals(id, voting.id) && Objects.equals(schedule, voting.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, schedule);
    }
}
