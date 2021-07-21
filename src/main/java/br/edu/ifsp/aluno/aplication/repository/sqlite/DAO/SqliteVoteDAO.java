package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.vote.VoteValue;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.vote.VoteDAO;
import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteVoteDAO implements VoteDAO {
    @Override
    public Integer insert(Vote vote) {
        if (vote.getParticipant() == null) {
            String sql = "INSERT INTO vote (value, id_voting) values (?, ?)";

            try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
                stmt.setString(1, vote.getValue().toString());
                stmt.setInt(2, vote.getVoting().getId());
                stmt.execute();
                ResultSet resultSet = stmt.getGeneratedKeys();
                return resultSet.getInt(1);
            }   catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "INSERT INTO vote (value, id_participant, id_voting) values (?, ?, ?)";

            try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
                stmt.setString(1, vote.getValue().toString());
                stmt.setInt(2, vote.getParticipant().getId());
                stmt.setInt(3, vote.getVoting().getId());
                stmt.execute();
                ResultSet resultSet = stmt.getGeneratedKeys();
                return resultSet.getInt(1);
            }   catch (SQLException e) {
                e.printStackTrace();
            }
        }

//        String sql = "INSERT INTO vote (value, id_participant, id_voting) values (?, ?, ?)";
//
//        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
//            stmt.setString(1, vote.getValue().toString());
//            stmt.setInt(2, vote.getParticipant().getId());
//            stmt.setInt(3, vote.getVoting().getId());
//            stmt.execute();
//            ResultSet resultSet = stmt.getGeneratedKeys();
//            return resultSet.getInt(1);
//        }   catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public Optional<Vote> findOne(Integer id) {
        String sql = "SELECT * FROM vote WHERE id = ?";
        Vote vote = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                vote = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(vote);
    }

    private Vote resultSetIntoEntity(ResultSet rs) throws SQLException {
//        MUDEI
        Participant participant;
        if (findParticipantUseCase.findOne(rs.getInt("id_participant")).isEmpty()) {
            participant = null;
        } else {
            participant = findParticipantUseCase.findOne(rs.getInt("id_participant")).get();
        }

//        SE PRECISAR
        Voting voting;
        if (findVotingUseCase.findOne(rs.getInt("id_voting")).isEmpty()) {
            voting = null;
        } else {
            voting = findVotingUseCase.findOne(rs.getInt("id_voting")).get();
        }

        return new Vote(
                rs.getInt("id"),
                participant,
                VoteValue.toEnun(rs.getString("value")),
                voting
        );
    }

    @Override
    public List<Vote> findAll() {
        String sql = "SELECT * FROM vote";
        List<Vote> votes = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vote vote = resultSetIntoEntity(rs);
                votes.add(vote);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return votes;
    }

    @Override
    public boolean update(Vote vote) {
        String sql = "UPDATE vote SET value = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, vote.getValue().toString());
            stmt.setInt(2, vote.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM vote WHERE id = ?";
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
    public boolean delete(Vote vote) {
        if (vote == null || vote.getId() == null) {
            throw new IllegalArgumentException("Vote or vote ID must not be null.");
        }
        return deleteByKey(vote.getId());
    }

    @Override
    public List<Vote> findByVoting(Voting voting) {
        String sql = "SELECT * FROM vote WHERE id_voting = ?";
        List<Vote> votes = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, voting.getId());
            stmt.execute();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vote vote = resultSetIntoEntity(rs);
                votes.add(vote);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return votes;
    }
}
