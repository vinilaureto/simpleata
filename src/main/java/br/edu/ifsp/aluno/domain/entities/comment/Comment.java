package br.edu.ifsp.aluno.domain.entities.comment;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;

public class Comment {
    private Integer id;
    private Participant participant;
    private String message;

    public Comment() {
    }

    public Comment(Participant participant, String message) {
        this.participant = participant;
        this.message = message;
    }

    public Comment(Integer id, Participant participant, String message) {
        this.id = id;
        this.participant = participant;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParticipanName() {
        return participant.getName();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", participant=" + participant +
                ", message='" + message + '\'' +
                '}';
    }
}
