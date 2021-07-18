package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.ConnectionFactory;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutesStatus;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.group.FindGroupUseCase;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.MeetingMinutesDAO;
import static br.edu.ifsp.aluno.aplication.main.Main.findGroupUseCase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteMeetingMinutesDAO implements MeetingMinutesDAO {
     @Override
    public Integer insert(MeetingMinutes meetingMinutes) {
         String sql = "INSERT INTO meeting_minutes (logo, identifier, local, title, creation_date, closing_date, id_group, status) values (?, ?, ?, ?, ?, ?, ?, ?)";

         try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
             stmt.setString(1, meetingMinutes.getLogo());
             setMeetingMinutesIdentifier(meetingMinutes);
             stmt.setString(2, meetingMinutes.getIdentifier());
             stmt.setString(3, meetingMinutes.getLocal());
             stmt.setString(4, meetingMinutes.getTitle());
             stmt.setString(5, meetingMinutes.getCreationDate().toString());
             stmt.setString(6, meetingMinutes.getClosingDate().toString());
             stmt.setInt(7, meetingMinutes.getGroup().getId());
             stmt.setString(8, meetingMinutes.getStatus().toString());
             stmt.execute();

             ResultSet resultSet = stmt.getGeneratedKeys();
             return resultSet.getInt(1);
         }   catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
    }

    @Override
    public Optional<MeetingMinutes> findOne(Integer id) {
        String sql = "SELECT * FROM meeting_minutes WHERE id = ?";
        MeetingMinutes meeting_minutes = null;

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                meeting_minutes = resultSetIntoEntity(rs);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(meeting_minutes);
    }

    private MeetingMinutes resultSetIntoEntity(ResultSet rs) throws SQLException {

        return new MeetingMinutes(
            rs.getInt("id"),
            rs.getString("logo"),
            rs.getString("identifier"),
            rs.getString("local"),
            rs.getString("title"),
            LocalDate.parse(rs.getString("creation_date")),
            LocalDate.parse(rs.getString("closing_date")),
            MeetingMinutesStatus.toEnum(rs.getString("status")),
            findGroupUseCase.findOne(rs.getInt("id_group")).get()
        );
    }

    @Override
    public List<MeetingMinutes> findByTitle(String title) {
        String sql = "SELECT * FROM meeting_minutes WHERE title LIKE ?";
        List<MeetingMinutes> meetingMinutesList = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MeetingMinutes meetingMinutes = resultSetIntoEntity(rs);
                meetingMinutesList.add(meetingMinutes);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingMinutesList;
    }

    @Override
    public List<MeetingMinutes> findByGroup(Group group) {
        String sql = "SELECT * FROM meeting_minutes WHERE id_group = ?";
        List<MeetingMinutes> meetingMinutesList = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, group.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MeetingMinutes meetingMinutes = resultSetIntoEntity(rs);
                meetingMinutesList.add(meetingMinutes);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingMinutesList;
    }

    @Override
    public List<MeetingMinutes> findByParticipant(Participant participant) {
        // todo: voltar depois
        return null;
    }

    @Override
    public List<MeetingMinutes> findByIdentifier(String identifier) {
        String sql = "SELECT * FROM meeting_minutes WHERE identifier LIKE ?";
        List<MeetingMinutes> meetingMinutesList = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, identifier);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MeetingMinutes meetingMinutes = resultSetIntoEntity(rs);
                meetingMinutesList.add(meetingMinutes);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingMinutesList;
    }

    @Override
    public List<MeetingMinutes> findAll() {
        String sql = "SELECT * FROM meeting_minutes";
        List<MeetingMinutes> meetingMinutesList = new ArrayList<>();

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MeetingMinutes meetingMinutes = resultSetIntoEntity(rs);
                meetingMinutesList.add(meetingMinutes);
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingMinutesList;
    }

    @Override
    public boolean update(MeetingMinutes meetingMinutes) {
        String sql = "UPDATE meeting_minutes SET logo = ?, local = ?, title = ?, closing_date = ?, status = ? WHERE id = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, meetingMinutes.getLogo());
            stmt.setString(2, meetingMinutes.getLocal());
            stmt.setString(3, meetingMinutes.getTitle());
            stmt.setString(4, meetingMinutes.getClosingDate().toString());
            stmt.setString(5, meetingMinutes.getStatus().toString());
            stmt.setInt(6, meetingMinutes.getId());
            stmt.execute();
            return true;
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        String sql = "DELETE FROM meeting_minutes WHERE id = ?";
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
    public boolean delete(MeetingMinutes meetingMinutes) {
        if (meetingMinutes == null || meetingMinutes.getId() == null) {
            throw new IllegalArgumentException("Meeting minutes or meeting minutes ID must not be null.");
        }
        return deleteByKey(meetingMinutes.getId());
    }

    @Override
    public String setMeetingMinutesIdentifier(MeetingMinutes meetingMinutes) {
        String year = String.valueOf(meetingMinutes.getCreationDate().getYear());
        String sql = "SELECT count(*) FROM meeting_minutes WHERE id_group = ? AND creation_date LIKE ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            int meetingMinutesCounter = 0;
            stmt.setInt(1, meetingMinutes.getGroup().getId());
            stmt.setString(2, "%" + year + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                meetingMinutesCounter = rs.getInt(1);
            }
            meetingMinutes.setIdentifier(String.valueOf(meetingMinutesCounter + 1) + "/" + year);
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
