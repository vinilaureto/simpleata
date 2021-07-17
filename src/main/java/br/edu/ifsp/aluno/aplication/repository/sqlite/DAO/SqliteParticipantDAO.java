package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.participant.ParticipantDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteParticipantDAO implements ParticipantDAO {
    @Override
    public Integer insert(Participant participant) {
        String sql = "INSERT INTO participant (name, email, title) values (?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getEmail());
            stmt.setString(3, participant.getTitle());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            return resultSet.getInt(1);
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Participant> findByEmail(String email) {
        String sql = "SELECT * FROM participant WHERE email = ?";
        Participant participant = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                participant = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(participant);
    }

    private Participant resultSetIntoEntity(ResultSet rs) throws SQLException {
        return new Participant(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getString("email"),
          rs.getString("title")
        );
    }

    @Override
    public List<Participant> findByName(String name) {
        String sql = "SELECT * FROM participant WHERE name = ?";
        List<Participant> participants = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Participant participant = resultSetIntoEntity(rs);
                participants.add(participant);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    @Override
    public List<Participant> findByTitle(String title) {
        String sql = "SELECT * FROM participant WHERE title = ?";
        List<Participant> participants = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Participant participant = resultSetIntoEntity(rs);
                participants.add(participant);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    @Override
    public Optional<Participant> findOne(Integer id) {
        String sql = "SELECT * FROM participant WHERE id = ?";
        Participant participant = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                participant = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(participant);
    }

    @Override
    public List<Participant> findAll() {
        String sql = "SELECT * FROM participant";
        List<Participant> participants = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Participant participant = resultSetIntoEntity(rs);
                participants.add(participant);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    @Override
    public boolean update(Participant participant) {
        String sql = "UPDATE participant SET name = ?, email = ?, title = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getEmail());
            stmt.setString(3, participant.getTitle());
            stmt.setInt(4, participant.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM participant WHERE id = ?";
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
    public boolean delete(Participant participant) {
        if (participant == null || participant.getId() == null) {
            throw new IllegalArgumentException("Participant or participant ID must not be null.");
        }
        return deleteByKey(participant.getId());
    }


}
