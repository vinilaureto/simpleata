package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.inform.InformDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryInformDAO implements InformDAO {
    private static final Map<Integer, Inform> db = new LinkedHashMap<>();
    private static int informIdCounter;

    @Override
    public Integer insert(Inform inform) {
        informIdCounter++;
        inform.setId(informIdCounter);
        db.put(informIdCounter, inform);
        return informIdCounter;
    }

    @Override
    public Optional<Inform> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Inform> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Inform inform) {
        Integer id = inform.getId();
        if (db.containsKey(id)) {
            db.replace(id, inform);
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
    public boolean delete(Inform inform) {
        return deleteByKey(inform.getId());
    }

    @Override
    public List<Inform> findByMeetingMinutes(MeetingMinutes meetingMinutes) {
        List<Inform> informList = findAll();
        List<Inform> informMeetingMinutes;

       return null;

//                db.values().stream().filter(inform -> inform.getId()
//                .equals(meetingMinutes.getInforms().forEach(m -> m.getId())));
    }
}
