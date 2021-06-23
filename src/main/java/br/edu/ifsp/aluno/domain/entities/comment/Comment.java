package br.edu.ifsp.aluno.domain.entities.comment;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;

public class Comment {
    private Participant participant;
    private String message;

    public Comment(Participant participant, String message) {
        this.participant = participant;
        this.message = message;
    }

    public Comment(String message) {
        this.message = message;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "participant=" + participant +
                ", message='" + message + '\'' +
                '}';
    }
}
