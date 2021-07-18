package br.edu.ifsp.aluno.domain.entities.vote;


import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;

import java.util.Optional;

public class Vote {
    private Integer id;
    private Participant participant;
    private VoteValue value;
    private Voting voting;

    public Vote(Participant participant, VoteValue value) {
        this.participant = participant;
        this.value = value;
    }

    public Vote(VoteValue value) {
        this.value = value;
    }

    public Vote(Integer id, Participant participant, VoteValue value) {
        this.id = id;
        this.participant = participant;
        this.value = value;
    }

    public Vote(Integer id, Participant participant, VoteValue value, Voting voting) {
        this.id = id;
        this.participant = participant;
        this.value = value;
        this.voting = voting;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public VoteValue getValue() {
        return value;
    }

    public void setValue(VoteValue value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    public String getParticipantName() {
        if (participant != null) {
            return participant.getName();
        }
        return "anônimo";
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", participant=" + participant +
                ", value=" + value +
                '}';
    }
}
