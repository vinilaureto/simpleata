package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.participant.ParticipantDAO;

import java.util.*;

public class InMemoryParticipantDAO implements ParticipantDAO {
    private static final Map<Integer, Participant> db = new LinkedHashMap<>();
    private static int participantIdCounter;

    @Override
    public Integer insert(Participant participant) {
        participantIdCounter++;
        participant.setId(participantIdCounter);
        db.put(participantIdCounter, participant);
        return participantIdCounter;
    }

    @Override
    public Optional<Participant> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Participant> findByEmail(String email) {
        return Optional.empty();
        //todo: implementar método
    }

    @Override
    public List<Participant> findByName(String name) {
        return (List<Participant>) db.values().stream()
                .filter(participant -> participant.getName().equals(name));
    }

    @Override
    public List<Participant> findByTitle(String title) {
        return (List<Participant>) db.values().stream()
                .filter(participant -> participant.getTitle().equals(title));
    }

    @Override
    public List<Participant> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Participant participant) {
        Integer id = participant.getId();
        if (db.containsKey(id)) {
            db.replace(id, participant);
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
    public boolean delete(Participant participant) {
        return deleteByKey(participant.getId());
    }
}
