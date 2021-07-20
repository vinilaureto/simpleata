package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.schedule.ScheduleDAO;

import java.util.*;

public class InMemoryScheduleDAO implements ScheduleDAO {
    private static final Map<Integer, Schedule> db = new LinkedHashMap<>();
    private static int scheduleIdCounter;

    @Override
    public Integer insert(Schedule schedule) {
        scheduleIdCounter++;
        schedule.setId(scheduleIdCounter);
        db.put(scheduleIdCounter, schedule);
        return scheduleIdCounter;
    }

    @Override
    public Optional<Schedule> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Schedule schedule) {
        Integer id = schedule.getId();
        if (db.containsKey(id)) {
            db.replace(id, schedule);
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
    public boolean delete(Schedule schedule) {
        return deleteByKey(schedule.getId());
    }

    @Override
    public List<Schedule> findByMeetingMinutes(MeetingMinutes meetingMinutes) {
        return null;
    }
}
