package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.group.GroupDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteGroupDAO implements GroupDAO {
    @Override
    public Integer insert(Group group) {
        String sql = "INSERT INTO groups (name) VALUES (?)";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, group.getName());
            stmt.execute();
            ResultSet resultSet = stmt.getGeneratedKeys();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Group> findByName(String name) {
        String sql = "SELECT * FROM groups WHERE name LIKE ?";
        Group group = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                group = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(group);
    }

    private Group resultSetIntoEntity(ResultSet rs) throws SQLException {
        SqliteParticipantDAO sqliteParticipantDAO = new SqliteParticipantDAO();
        return new Group(
                rs.getInt("id"),
                rs.getString("name"),
                sqliteParticipantDAO.findParticipantsByGroup(rs.getInt("id"))
        );
    }

    @Override
    public Optional<Group> findOne(Integer id) {
        String sql = "SELECT * FROM groups WHERE id = ?";
        Group group = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                group = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> findAll() {
        String sql = "SELECT * FROM groups";
        List<Group> groups = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Group group = resultSetIntoEntity(rs);
                groups.add(group);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    @Override
    public boolean update(Group group) {
        String sql = "UPDATE groups SET name = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, group.getName());
            stmt.setInt(2, group.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM groups WHERE id = ?";
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
    public boolean delete(Group group) {
        if (group == null || group.getId() == null) {
            throw new IllegalArgumentException("Group or group ID must not be null.");
        }
        return deleteByKey(group.getId());
    }

    @Override
    public boolean addParticipantToGroup(Participant participant, Group group) {
        String sql = "SELECT * FROM partcipant_groups WHERE id_participant = ? AND id_groups = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, participant.getId());
            stmt.setInt(2, group.getId());
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                String sqlInsert = "INSERT INTO partcipant_groups (id_participant, id_groups) VALUES (?, ?)";
                PreparedStatement stmtInsert = ConnectionFactory.createPreparedStatement(sqlInsert);
                stmtInsert.setInt(1, participant.getId());
                stmtInsert.setInt(2, group.getId());
                stmtInsert.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeParticipantFromGroup(Participant participant, Group group) {
        String sql = "SELECT * FROM partcipant_groups WHERE id_participant = ? AND id_groups = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, participant.getId());
            stmt.setInt(2, group.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String sqlRemove = "DELETE FROM partcipant_groups WHERE id_participant = ? AND id_groups = ?";
                PreparedStatement stmtRemove = ConnectionFactory.createPreparedStatement(sqlRemove);
                stmtRemove.setInt(1, participant.getId());
                stmtRemove.setInt(2, group.getId());
                stmtRemove.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
