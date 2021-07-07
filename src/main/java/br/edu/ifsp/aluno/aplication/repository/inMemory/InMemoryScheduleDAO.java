package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.schedule.ScheduleDAO;

import java.util.*;

public class InMemoryScheduleDAO implements ScheduleDAO {
    private static final Map<String, Schedule> db = new LinkedHashMap<>();

    @Override
    public String insert(Schedule schedule) {
        String topic = schedule.getTopic();
        db.put(topic, schedule);
        return topic;
    }

    @Override
    public Optional<Schedule> findOne(String topic) {
        if (db.containsKey(topic)) {
            return Optional.of(db.get(topic));
        }
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Schedule schedule) {
        String topic = schedule.getTopic();
        if (db.containsKey(topic)) {
            db.replace(topic, schedule);
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
    public boolean delete(Schedule schedule) {
        return deleteByKey(schedule.getTopic());
    }
}
