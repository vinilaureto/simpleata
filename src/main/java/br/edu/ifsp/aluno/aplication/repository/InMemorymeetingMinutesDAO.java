package br.edu.ifsp.aluno.aplication.repository;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.MeetingMinutesDAO;

import java.util.*;

public class InMemorymeetingMinutesDAO implements MeetingMinutesDAO {
    private static final Map<String, MeetingMinutes> db = new LinkedHashMap<>();

    @Override
    public String insert(MeetingMinutes meetingMinutes) {
        String title = meetingMinutes.getTitle();
        db.put(title, meetingMinutes);
        return title;
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
        // Implementação pendente
        return null;
    }

    @Override
    public List<MeetingMinutes> findByParticipant(Participant participant) {
        // Implementação pendente
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
