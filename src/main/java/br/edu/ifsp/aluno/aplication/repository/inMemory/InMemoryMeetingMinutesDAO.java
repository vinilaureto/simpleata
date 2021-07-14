package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.MeetingMinutesDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMeetingMinutesDAO implements MeetingMinutesDAO {
    private static final Map<Integer, MeetingMinutes> db = new LinkedHashMap<>();
    private static int meetingMinutesIdCounter;

    @Override
    public Integer insert(MeetingMinutes meetingMinutes) {
        meetingMinutesIdCounter++;
        meetingMinutes.setId(meetingMinutesIdCounter);
        db.put(meetingMinutesIdCounter, meetingMinutes);
        return meetingMinutesIdCounter;
    }

    @Override
    public Optional<MeetingMinutes> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<MeetingMinutes> findByTitle(String title) {
        // TODO: implementar medoto
        return null;
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
        return new ArrayList<>(db.values().stream()
                .filter(m -> m.getGroup().getParticipants().iterator().next().equals(participant))
                .collect(Collectors.toList())
        );
    }

    @Override
    public List<MeetingMinutes> findByIdentifier(String identifier) {
        // TODO: Implementar método
//        return db.values().stream()
//                .filter(meetingMinutes -> meetingMinutes.getIdentifier().equals(identifier)).findAny();
        return null;
    }

    @Override
    public List<MeetingMinutes> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(MeetingMinutes meetingMinutes) {
        Integer id = meetingMinutes.getId();
        if (db.containsKey(id)) {
            db.replace(id, meetingMinutes);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(MeetingMinutes meetingMinutes) {
        return deleteByKey(meetingMinutes.getId());
    }
}
