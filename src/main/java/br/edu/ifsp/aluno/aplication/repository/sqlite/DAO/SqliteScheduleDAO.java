package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.schedule.ScheduleDAO;
import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteScheduleDAO implements ScheduleDAO {
    @Override
    public Integer insert(Schedule schedule) {
        String sql = "INSERT INTO schedule (topic, id_meeting_minutes) values (?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, schedule.getTopic());
            stmt.setInt(2, schedule.getMeetingMinutes().getId());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            return resultSet.getInt(1);
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Schedule> findOne(Integer id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        Schedule schedule = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                schedule = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(schedule);
    }

    private Schedule resultSetIntoEntity(ResultSet rs) throws SQLException {
        return new Schedule(
                rs.getInt("id"),
                rs.getString("topic"),
                findMeetingMinutesUseCase.findOne(rs.getInt("id_meeting_minutes")).get()
        );
    }

    @Override
    public List<Schedule> findAll() {
        String sql = "SELECT * FROM schedule";
        List<Schedule> schedules = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Schedule schedule = resultSetIntoEntity(rs);
                schedules.add(schedule);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public boolean update(Schedule schedule) {
        String sql = "UPDATE schedule SET topic = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, schedule.getTopic());
            stmt.setInt(2, schedule.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Schedule schedule) {
        if (schedule == null || schedule.getId() == null) {
            throw new IllegalArgumentException("Schedule or schedule ID must not be null.");
        }
        return deleteByKey(schedule.getId());
    }

    @Override
    public List<Schedule> findByMeetingMinutes(MeetingMinutes meetingMinutes) {
        String sql = "SELECT * FROM schedule WHERE id_meeting_minutes = ?";
        List<Schedule> schedules = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, meetingMinutes.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Schedule schedule = resultSetIntoEntity(rs);
                schedules.add(schedule);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
}
