package br.edu.ifsp.aluno.domain.entities.inform;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;

import java.util.Optional;

public class Inform {
    private Integer id;
    private String description;
    private MeetingMinutes meetingMinutes;

    public Inform() {
    }

    public Inform(String description) {
        this.description = description;
    }

    public Inform(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Inform(Integer id, String description, MeetingMinutes meetingMinutes) {
        this.id = id;
        this.description = description;
        this.meetingMinutes = meetingMinutes;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MeetingMinutes getMeetingMinutes() {
        return meetingMinutes;
    }

    public void setMeetingMinutes(MeetingMinutes meetingMinutes) {
        this.meetingMinutes = meetingMinutes;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
