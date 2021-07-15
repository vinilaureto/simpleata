package br.edu.ifsp.aluno.domain.entities.group;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Group {
    private Integer id;
    private String name;
    private List<Participant> participants = new ArrayList<>();
    private List<MeetingMinutes> meetingMinutesList = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, List<Participant> participants, List<MeetingMinutes> meetingMinutesList) {
        this.name = name;
        this.participants = participants;
        this.meetingMinutesList = meetingMinutesList;
    }

    public Group(Integer id, String name, List<Participant> participants, List<MeetingMinutes> meetingMinutesList) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

//   todo: Verificar se precisa; parece que sim
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getParticipantsNames() {
        return participants.stream().map(p -> p.getName()).collect(Collectors.joining(", "));
    }

    public List<MeetingMinutes> getMeetingMinutesList() {
        return meetingMinutesList;
    }

    public Integer getTotalMeetingMinutesOfAYear(MeetingMinutes meetingMinutes) {
        Integer count = 0;
        for (MeetingMinutes mt: meetingMinutesList) {
            if (mt.getCreationDate().getYear() == meetingMinutes.getCreationDate().getYear()) {
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString() {
        return "\n Group " + name
                + "\n\t Participantes:"
                + "\t\t" + participants.stream().map(p -> p.toString()).collect(Collectors.joining("\n\t\t"));

    }
}
