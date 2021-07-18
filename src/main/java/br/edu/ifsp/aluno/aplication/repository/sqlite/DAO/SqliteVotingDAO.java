package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.voting.VoteResult;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.voting.VotingDAO;
import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteVotingDAO implements VotingDAO {
    @Override
    public Integer insert(Voting voting) {
        String sql = "INSERT INTO voting (vote_result, id_schedule) values (?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, voting.getResult().toString());
            stmt.setInt(2, voting.getSchedule().getId());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            return resultSet.getInt(1);
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Voting> findOne(Integer id) {
        String sql = "SELECT * FROM voting WHERE id = ?";
        Voting voting = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                voting = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(voting);
    }

    private Voting resultSetIntoEntity(ResultSet rs) throws SQLException {
        return new Voting(
            rs.getInt("id"),
            VoteResult.toEnun(rs.getString("value")),
            findScheduleUseCase.findOne(rs.getInt("id_schedule")).get()
        );
    }

    @Override
    public List<Voting> findAll() {
        String sql = "SELECT * FROM participant";
        List<Voting> votings = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Voting vote = resultSetIntoEntity(rs);
                votings.add(vote);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return votings;
    }

    @Override
    public boolean update(Voting voting) {
        String sql = "UPDATE voting SET vote_result = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, voting.getResult().toString());
            stmt.setInt(2, voting.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM voting WHERE id = ?";
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
    public boolean delete(Voting voting) {
        if (voting == null || voting.getId() == null) {
            throw new IllegalArgumentException("Voting or voting ID must not be null.");
        }
        return deleteByKey(voting.getId());
    }
}
