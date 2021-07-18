package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.schedule.ScheduleDAO;

import java.util.List;
import java.util.Optional;

public class SqliteScheduleDAO implements ScheduleDAO {
    @Override
    public Integer insert(Schedule type) {
        return null;
    }

    @Override
    public Optional<Schedule> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll() {
        return null;
    }

    @Override
    public boolean update(Schedule type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Schedule type) {
        return false;
    }
}
