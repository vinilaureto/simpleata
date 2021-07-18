package br.edu.ifsp.aluno.domain.entities.comment;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;

import java.util.Optional;

public class Comment {
    private Integer id;
    private Participant participant;
    private String message;
    private Schedule schedule;

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

    public Comment(Participant participant, String message, Schedule schedule) {
        this.participant = participant;
        this.message = message;
        this.schedule = schedule;
    }

    public Comment(Integer id, Participant participant, String message, Schedule schedule) {
        this.id = id;
        this.participant = participant;
        this.message = message;
        this.schedule = schedule;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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
