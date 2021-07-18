package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.inform.InformDAO;

import java.util.List;
import java.util.Optional;

public class SqliteInformDAO implements InformDAO {
    @Override
    public List<Inform> findByMeetingMinutes(MeetingMinutes meetingMinutes) {
        return null;
    }

    @Override
    public Integer insert(Inform type) {
        return null;
    }

    @Override
    public Optional<Inform> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Inform> findAll() {
        return null;
    }

    @Override
    public boolean update(Inform type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Inform type) {
        return false;
    }
}
