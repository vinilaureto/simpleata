package br.edu.ifsp.aluno.aplication.repository;

import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.participant.ParticipantDAO;

import java.util.*;

public class InMemoryParticipantDAO implements ParticipantDAO {

    private static final Map<String, Participant> db = new LinkedHashMap<>();

    @Override
    public String insert(Participant participant) {
        String email = participant.getEmail();
        db.put(email, participant);
        return email;
    }

    @Override
    public Optional<Participant> findOne(String email) {
        if (db.containsKey(email)) {
            return Optional.of(db.get(email));
        }
        return Optional.empty();
    }

    @Override
    public List<Participant> findOneByName(String name) {
        return (List<Participant>) db.values().stream()
                .filter(participant -> participant.getName().equals(name));
    }

    @Override
    public List<Participant> findOneByTitle(String title) {
        return (List<Participant>) db.values().stream()
                .filter(participant -> participant.getTitle().equals(title));
    }

    @Override
    public List<Participant> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Participant participant) {
        String email = participant.getEmail();
        if (db.containsKey(email)) {
            db.replace(email, participant);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String email) {
        if (db.containsKey(email)) {
            db.remove(email);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Participant participant) {
        return deleteByKey(participant.getEmail());
    }
}
