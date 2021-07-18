package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.comment.CommentDAO;
import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteCommentDAO implements CommentDAO {
    @Override
    public Integer insert(Comment comment) {
        String sql = "INSERT INTO comment (message, id_participant, id_schedule) values (?, ?, ?)";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, comment.getMessage());
            stmt.setInt(2, comment.getParticipant().getId());
            stmt.setInt(3, comment.getSchedule().getId());
            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            return resultSet.getInt(1);
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> findBySchedule(Schedule schedule) {
        String sql = "SELECT * FROM comment WHERE id_schedule = ?";
        List<Comment> comments = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, schedule.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comment comment = resultSetIntoEntity(rs);
                comments.add(comment);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    private Comment resultSetIntoEntity(ResultSet rs) throws SQLException {
        return new Comment(
                rs.getInt("id"),
                findParticipantUseCase.findOne(rs.getInt("id_participant")).get(),
                rs.getString("message"),
                findScheduleUseCase.findOne(rs.getInt("id_schedule")).get()
        );
    }

    @Override
    public Optional<Comment> findOne(Integer id) {
        String sql = "SELECT * FROM comment WHERE id = ?";
        Comment comment = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                comment = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comment> findAll() {
        String sql = "SELECT * FROM comment";
        List<Comment> comments = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Comment comment = resultSetIntoEntity(rs);
                comments.add(comment);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public boolean update(Comment comment) {
        String sql = "UPDATE comment SET message = ?, id_participant = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, comment.getMessage());
            stmt.setInt(2, comment.getParticipant().getId());
            stmt.setInt(3, comment.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM comment WHERE id = ?";
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
    public boolean delete(Comment comment) {
        if (comment == null || comment.getId() == null) {
            throw new IllegalArgumentException("Comment or comment ID must not be null.");
        }
        return deleteByKey(comment.getId());
    }
}
