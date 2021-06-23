package br.edu.ifsp.aluno.domain.entities.group;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<Participant> participants = new ArrayList<>();
    private List<MeetingMinutes> meetingMinutesList = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, List<Participant> participants, List<MeetingMinutes> meetingMinutesList) {
        this.name = name;
        this.participants = participants;
        this.meetingMinutesList = meetingMinutesList;
    }

    public void addMeetingMinutes(MeetingMinutes meetingMinutes) {
        meetingMinutesList.add(meetingMinutes);
    }

    public void removeMeetingMinutes(MeetingMinutes meetingMinutes) {
        meetingMinutesList.remove(meetingMinutes);
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", participants=" + participants +
                ", meetingMinutesList=" + meetingMinutesList +
                '}';
    }
}
