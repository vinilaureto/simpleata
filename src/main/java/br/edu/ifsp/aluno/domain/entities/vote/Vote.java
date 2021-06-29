package br.edu.ifsp.aluno.domain.entities.vote;


import br.edu.ifsp.aluno.domain.entities.participant.Participant;

public class Vote {
    private Integer id;
    private Participant participant;
    private VoteValue value;

    public Vote(VoteValue value, Participant participant) {
        this.value = value;
        this.participant = participant;
    }

    public Vote(VoteValue value) {
        this.value = value;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "participant=" + participant +
                ", value=" + value +
                '}';
    }
}
