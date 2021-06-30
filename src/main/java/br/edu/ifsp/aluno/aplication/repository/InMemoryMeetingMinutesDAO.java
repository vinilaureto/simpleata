package br.edu.ifsp.aluno.aplication.repository;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.MeetingMinutesDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMeetingMinutesDAO implements MeetingMinutesDAO {
    private static final Map<String, MeetingMinutes> db = new LinkedHashMap<>();

    @Override
    public String insert(MeetingMinutes meetingMinutes) {
        meetingMinutes.getGroup().addMeetingMinutes(meetingMinutes);
        String meetingMinutesId = setMeetingMinutesId(meetingMinutes);
        db.put(meetingMinutes.getTitle(), meetingMinutes);
        meetingMinutes.setIdentifier(meetingMinutesId);

        return meetingMinutesId;
    }

    private String setMeetingMinutesId(MeetingMinutes meetingMinutes) {
        String meetingMinutesId = meetingMinutes.getGroup().getTotalMeetingMinutesOfAYear(meetingMinutes).toString()
                + "/"
                + meetingMinutes.getCreationDate().getYear();

        return meetingMinutesId;
    }

    @Override
    public Optional<MeetingMinutes> findOne(String title) {
        if (db.containsKey(title)) {
            return Optional.of(db.get(title));
        }
        return Optional.empty();
    }

    @Override
    public List<MeetingMinutes> findByGroup(Group group) {
        return new ArrayList<>(db.values().stream()
                .filter(m -> m.getGroup().equals(group))
                .collect(Collectors.toList())
        );
    }

    @Override
    public List<MeetingMinutes> findByParticipant(Participant participant) {
        /*ArrayList<MeetingMinutes> resolveMeetingMinutes = new ArrayList<>();
        db.values().stream().map(m -> {
            ArrayList<Participant> participants = new ArrayList<>(m.getGroup().getParticipants());
            if (participants.contains(participant)) {
                resolveMeetingMinutes.add(m);
            }
            return null;
        });
        return resolveMeetingMinutes;*/
        //todo: Implementar
        return null;
    }

    @Override
    public Optional<MeetingMinutes> findByIdentifier(String identifier) {
        return db.values().stream()
                .filter(meetingMinutes -> meetingMinutes.getIdentifier().equals(identifier)).findAny();
    }

    @Override
    public List<MeetingMinutes> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(MeetingMinutes meetingMinutes) {
        String title = meetingMinutes.getTitle();
        if (db.containsKey(title)) {
            db.replace(title, meetingMinutes);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String title) {
        if (db.containsKey(title)) {
            db.remove(title);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(MeetingMinutes meetingMinutes) {
        return deleteByKey(meetingMinutes.getTitle());
    }
}
