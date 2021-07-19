package br.edu.ifsp.aluno.domain.entities.comment;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(participant, comment.participant) && Objects.equals(schedule, comment.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, participant, schedule);
    }
}
