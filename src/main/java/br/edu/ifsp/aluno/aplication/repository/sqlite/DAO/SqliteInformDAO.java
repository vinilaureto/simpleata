package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.inform.InformDAO;
import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteInformDAO implements InformDAO {
    @Override
    public Integer insert(Inform inform) {
        String sql = "INSERT INTO inform (description, id_meeting_minutes) values (?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, inform.getDescription());
            stmt.setInt(2, inform.getMeetingMinutes().getId());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            return resultSet.getInt(1);
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inform> findByMeetingMinutes(MeetingMinutes meetingMinutes) {
        String sql = "SELECT * FROM inform WHERE id_meeting_minutes = ?";
        List<Inform> informs = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, meetingMinutes.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Inform inform = resultSetIntoEntity(rs);
                informs.add(inform);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return informs;
    }

    private Inform resultSetIntoEntity(ResultSet rs) throws SQLException {
        return new Inform(
                rs.getInt("id"),
                rs.getString("description"),
                findMeetingMinutesUseCase.findOne(rs.getInt("id_meeting_minutes")).get()
        );
    }

    @Override
    public Optional<Inform> findOne(Integer id) {
        String sql = "SELECT * FROM inform WHERE id = ?";
        Inform inform = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                inform = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(inform);
    }

    @Override
    public List<Inform> findAll() {
        String sql = "SELECT * FROM inform";
        List<Inform> informs = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Inform inform = resultSetIntoEntity(rs);
                informs.add(inform);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return informs;
    }

    @Override
    public boolean update(Inform inform) {
        String sql = "UPDATE inform SET description = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, inform.getDescription());
            stmt.setInt(2, inform.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM inform WHERE id = ?";
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
    public boolean delete(Inform inform) {
        if (inform == null || inform.getId() == null) {
            throw new IllegalArgumentException("Inform or inform ID must not be null.");
        }
        return deleteByKey(inform.getId());
    }
}
