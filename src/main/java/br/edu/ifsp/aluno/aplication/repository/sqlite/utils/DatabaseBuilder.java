package br.edu.ifsp.aluno.aplication.repository.sqlite.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() { return !Files.exists(Paths.get("simpleAtaDB.db")); }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createParticipantTable());
            statement.addBatch(createGroupTable());
            statement.addBatch(createPartcipantGroupsTable());
            statement.addBatch(createMeetingMinutesTable());
            statement.addBatch(createInformTable());
            statement.addBatch(createScheduleTable());
            statement.addBatch(createCommentTable());
            statement.addBatch(createVotingTable());
            statement.addBatch(createVoteTable());

            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createParticipantTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE participant (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\tname TEXT NOT NULL,");
        builder.append("\temail TEXT NOT NULL,\n");
        builder.append("\ttitle TEXT\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }

    private String createGroupTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE groups (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\tname TEXT NOT NULL\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }

    private String createPartcipantGroupsTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE partcipant_groups (\n");
        builder.append("\tid_participant INTEGER NOT NULL,\n");
        builder.append("\tid_groups INTEGER NOT NULL,\n");
        builder.append("\tCONSTRAINT participant_grou PRIMARY KEY (id_participant, id_groups),\n");
        builder.append("\tCONSTRAINT id_participant_fk FOREIGN KEY (id_participant) REFERENCES participant(id),\n");
        builder.append("\tCONSTRAINT id_groups_fk FOREIGN KEY (id_groups) REFERENCES groups(id)\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }

    private String createMeetingMinutesTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE meeting_minutes (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\tlogo TEXT,\n");
        builder.append("\tidentifier TEXT,\n");
        builder.append("\tlocal TEXT,\n");
        builder.append("\ttitle TEXT NOT NULL,\n");
        builder.append("\tcreation_date TEXT,\n");
        builder.append("\tclosing_date TEXT,\n");
        builder.append("\tid_group INTEGER,\n");
        builder.append("\tstatus TEXT,\n");
        builder.append("\tCONSTRAINT meeting_minutes_group_fk FOREIGN KEY (id_group) REFERENCES groups(id)\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }

    private String createInformTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE inform (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\tdescription TEXT,\n");
        builder.append("\tid_meeting_minutes INTEGER NOT NULL,\n");
        builder.append("\tCONSTRAINT inform_meeting_minutes_fk FOREIGN KEY (id_meeting_minutes) REFERENCES meeting_minutes(id)\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }

    private String createScheduleTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE schedule (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\ttopic TEXT,\n");
        builder.append("\tid_meeting_minutes INTEGER NOT NULL,\n");
        builder.append("\tCONSTRAINT schedule_meeting_minutes_fk FOREIGN KEY (id_meeting_minutes) REFERENCES meeting_minutes(id)\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }

    private String createCommentTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE comment (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\tmessage TEXT NOT NULL,\n");
        builder.append("\tid_participant INTEGER,\n");
        builder.append("\tid_schedule INTEGER,\n");
        builder.append("\tCONSTRAINT comment_participant_fk FOREIGN KEY (id_participant) REFERENCES participant(id),\n");
        builder.append("\tCONSTRAINT comment_schedule_fk FOREIGN KEY (id_schedule) REFERENCES schedule(id)\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }
    private String createVotingTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE voting (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\tvote_result TEXT,\n");
        builder.append("\tid_schedule INTEGER NOT NULL,\n");
        builder.append("\tCONSTRAINT voting_schedule_fk FOREIGN KEY (id_schedule) REFERENCES schedule(id)\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }
    private String createVoteTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE vote (\n");
        builder.append("\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("\tvalue TEXT,\n");
        builder.append("\tid_participant INTEGER,\n");
        builder.append("\tid_voting INTEGER,\n");
        builder.append("\tCONSTRAINT vote_participant_fk FOREIGN KEY (id_participant) REFERENCES participant(id),\n");
        builder.append("\tCONSTRAINT vote_voting_fk FOREIGN KEY (id_voting) REFERENCES voting(id)\n");
        builder.append(");\n");

        System.out.println(builder.toString());

        return builder.toString();
    }

}
