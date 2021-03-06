package br.edu.ifsp.aluno.aplication.main;

import br.edu.ifsp.aluno.aplication.repository.sqlite.DAO.*;
import br.edu.ifsp.aluno.aplication.repository.sqlite.utils.DatabaseBuilder;
import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.usecases.comment.*;
import br.edu.ifsp.aluno.domain.usecases.group.*;
import br.edu.ifsp.aluno.domain.usecases.inform.*;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.*;
import br.edu.ifsp.aluno.domain.usecases.participant.*;
import br.edu.ifsp.aluno.domain.usecases.schedule.*;
import br.edu.ifsp.aluno.domain.usecases.vote.*;
import br.edu.ifsp.aluno.domain.usecases.voting.*;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        configureInjection();
        setupDatabase();
        WindowLoader.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    public static CreateParticipantUseCase createParticipantUseCase;
    public static FindParticipantUseCase findParticipantUseCase;
    public static DeleteParticipantUseCase deleteParticipantUseCase;
    public static UpdateParticipantUseCase updateParticipantUseCase;

    public static CreateGroupUseCase createGroupUseCase;
    public static FindGroupUseCase findGroupUseCase;
    public static DeleteGroupUseCase deleteGroupUseCase;
    public static UpdateGroupUseCase updateGroupUseCase;
    public static AddParticipantToGroupUseCase addParticipantToGroupUseCase;
    public static RemoveParticipantFromGroupUseCase removeParticipantFromGroupUseCase;
    public static RegisterMeetingMinutesToGroup registerMeetingMinutesToGroup;

    public static CreateMeetingMinutesUseCase createMeetingMinutesUseCase;
    public static FindMeetingMinutesUseCase findMeetingMinutesUseCase;
    public static DeleteMeetingMinuteUseCase deleteMeetingMinuteUseCase;
    public static UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;
    public static ChangeStatusMeetingMinutesUseCase changeStatusMeetingMinutesUseCase;
    public static IncludeInformToMeetingMinutesUseCase includeInformToMeetingMinutesUseCase;
    public static IncludeLogoToMeetingMinuteUseCase includeLogoToMeetingMinuteUseCase;
    public static IncludeScheduleToMeetingMinutesUseCase includeScheduleToMeetingMinutesUseCase;

    public static CreateInformUseCase createInformUseCase;
    public static DeleteInformUseCase deleteInformUseCase;
    public static FindInformUseCase findInformUseCase;
    public static UpdateInformUseCase updateInformUseCase;

    public static CreateScheduleUseCase createScheduleUseCase;
    public static FindScheduleUseCase findScheduleUseCase;
    public static DeleteScheduleUseCase deleteScheduleUseCase;
    public static UpdateScheduleUseCase updateScheduleUseCase;
    public static RegisterVotingToScheduleUseCase registerVotingToScheduleUseCase;
    public static RegisterCommentToScheduleUseCase registerCommentToScheduleUseCase;

    public static CreateCommentUseCase createCommentUseCase;
    public static UpdateCommentUseCase updateCommentUseCase;
    public static DeleteCommentUseCase deleteCommentUseCase;
    public static FindCommentUseCase findCommentUseCase;

    public static CreateVotingUseCase createVotingUseCase;
    public static UpdateVotingUseCase updateVotingUseCase;
    public static DeleteVotingUseCase deleteVotingUseCase;
    public static FindVotingUseCase findVotingUseCase;
    public static RegisterVoteToVoting registerVoteToVoting;
    public static UnregisterVoteToVoting unregisterVoteToVoting;

    public static CreateVoteUseCase createVoteUseCase;
    public static UpdateVoteUseCase updateVoteUseCase;
    public static FindVoteUseCase findVoteUseCase;
    public static DeleteVoteUseCase deleteVoteUseCase;

    // Testes feitos na mem??ria

//    TESTES COM PARTICIPANTES
//        Participant participant1 = new Participant("Jo??o", "joao@gmail.com", "Dr");
//        Participant participant2 = new Participant("Jo??o2", "joao2@gmail.com", "Dr");
//        Participant participant3 = new Participant("Jo??o3", "joao3@gmail.com", "Dr");
//        createParticipantUseCase.insert(participant1); // ok
//        createParticipantUseCase.insert(participant2); // ok
//        createParticipantUseCase.insert(participant3); // ok

//        System.out.println(findParticipantUseCase.findOne("joao@gmail.com").toString()); // ok

//        deleteParticipantUseCase.delete(participant2); // ok
//        participant3.setName("JULIO");
//        updateParticipantUseCase.update(participant3); // ok
////        System.out.println(findParticipantUseCase.findAll().toString()); // ok
//
//
//        // TESTES COM GRUPOS
//        Group group1 = new Group("Grupo legal");
//        createGroupUseCase.insert(group1); // ok
//
////        System.out.println(findGroupUseCase.findOne(1).toString()); // ok
//        addParticipantToGroupUseCase.addParticipantToGroup(participant1, group1); // ok
//        //addParticipantToGroupUseCase.addParticipantToGroup(participant2, group1); // ok - dispara a exce????o certa
//        addParticipantToGroupUseCase.addParticipantToGroup(participant3, group1); // ok
//
////        System.out.println("Todos participantes do grupo 1:" + findParticipantUseCase.findAll());
//
////        removeParticipantFromGroupUseCase.removeParticipantFromGroup(participant3, group1); // ok
////        System.out.println(findGroupUseCase.findOne(1).toString()); // ok
//
//        Group group2 = new Group("Grupo bacana");
//        createGroupUseCase.insert(group2);
//        addParticipantToGroupUseCase.addParticipantToGroup(participant1, group2);
////        System.out.println(findGroupUseCase.findAll());
//
//        // TESTES COM ATA
//        MeetingMinutes meetingMinutes1 = new MeetingMinutes();
//
//        meetingMinutes1.setGroup(group1);
//        meetingMinutes1.setTitle("G1Meeting1");
//        meetingMinutes1.setCreationDate(LocalDate.now());
//        createMeetingMinutesUseCase.insert(meetingMinutes1); // ok
//        MeetingMinutes meetingMinutes2 = new MeetingMinutes();
//        meetingMinutes2.setGroup(group2);
//        meetingMinutes2.setTitle("G2Meeting1");
//        meetingMinutes2.setCreationDate(LocalDate.now());
//        createMeetingMinutesUseCase.insert(meetingMinutes2);
//        MeetingMinutes meetingMinutes3 = new MeetingMinutes();
//        meetingMinutes3.setGroup(group2);
//        meetingMinutes3.setTitle("G2Meeting2");
//        meetingMinutes3.setCreationDate(LocalDate.now());
//        createMeetingMinutesUseCase.insert(meetingMinutes3);
//        //meetingMinutes1.setIdentifier("2021/01");
//        includeLogoToMeetingMinuteUseCase.includeLogo("Logo bonito", meetingMinutes1); // ok
////        System.out.println(findMeetingMinutesUseCase.findOne(1).toString()); // ok
////        System.out.println("Todas atas G2: " + findMeetingMinutesUseCase.findByGroup(group2));
//
////        System.out.println("Atas do participante 1: " + findMeetingMinutesUseCase.findByParticipant(participant1));
//
//
//        // registerMeetingMinutesToGroup.registerMeetingMinutesToGroup(meetingMinutes1, group1); //
//        //System.out.println(findGroupUseCase.findOne("Grupo legal"));
//
//        // TESTES COM CASOS DE USOS DOS COMENT??RIOS OK
////        Comment commentError1 = null;
////        createCommentUseCase.insert(commentError1);
////        Comment commentError2 = new Comment(participant3, "");
////        createCommentUseCase.insert(commentError2);
//        Comment comment1 = new Comment(participant1, "Coment??rio do participante 1.");
//        createCommentUseCase.insert(comment1);
////        System.out.println(findCommentUseCase.findOne(1));
////        System.out.println(findCommentUseCase.findOne(2));
//        Comment comment2 = new Comment(participant2, "Coment??rio do participante 2.");
//        createCommentUseCase.insert(comment2);
//        comment1.setMessage("Mensagem atualizada.");
//        updateCommentUseCase.update(comment1);
////        System.out.println(findCommentUseCase.findAll());
//        deleteCommentUseCase.delete(comment1);
//        deleteCommentUseCase.delete(2);
////        System.out.println(findCommentUseCase.findAll());
//
//        // TESTES COM CASOS DE USOS DOS INFORMES OK
////        Inform informError1 = null;
////        createInformUseCase.insert(informError1);
////        Inform informError2 = new Inform("", "Descri????o");
////        createInformUseCase.insert(informError2);
////        Inform informError3 = new Inform("T??tulo", "");
////        createInformUseCase.insert(informError3);
//        Inform inform1 = new Inform("Descri????o 1.");
//        createInformUseCase.insert(inform1);
////        System.out.println(findInformUseCase.findOne(1));
////        System.out.println(findInformUseCase.findOne(2));
//        Inform inform2 = new Inform("Descri????o 2.");
//        createInformUseCase.insert(inform2);
//        inform1.setDescription("Novo T??tulo");
//        updateInformUseCase.update(inform1);
////        System.out.println(findInformUseCase.findAll());
//        deleteInformUseCase.delete(1);
//        deleteInformUseCase.delete(inform2);
////        System.out.println(findInformUseCase.findAll());
//
////        TESTES COM CASOS DE USO DE PAUTAS
////        Schedule scheduleError1 = null;
////        createScheduleUseCase.insert(scheduleError1);
////        Schedule scheduleError2 = new Schedule("", "Descri????o");
////        createScheduleUseCase.insert(scheduleError2);
////        Schedule scheduleError3 = new Schedule("T??pico", "");
////        createScheduleUseCase.insert(scheduleError3);
//        Schedule schedule1 = new Schedule();
//        schedule1.setTopic("Pauta 1");
//        createScheduleUseCase.insert(schedule1);
////        System.out.println(findScheduleUseCase.findOne(1));
////        System.out.println(findScheduleUseCase.findOne(2));
//        Schedule schedule2 = new Schedule();
//        schedule2.setTopic("Pauta 2");
//        createScheduleUseCase.insert(schedule2);
//        schedule1.setTopic("Nova descrica????o da Pauta1");
//        updateScheduleUseCase.update(schedule1);
////        System.out.println(findScheduleUseCase.findAll());
//        deleteScheduleUseCase.delete(schedule1);
////        System.out.println(findScheduleUseCase.findAll());
//
//        // Teste com o contexto
//        ApplicationContext applicationContext = ApplicationContext.getInstance();
//        applicationContext.setCurrentGroup(group1);


    private static void configureInjection() {
        ParticipantDAO participantDAO = new SqliteParticipantDAO();
        createParticipantUseCase = new CreateParticipantUseCase(participantDAO);
        updateParticipantUseCase = new UpdateParticipantUseCase(participantDAO);
        findParticipantUseCase = new FindParticipantUseCase(participantDAO);
        deleteParticipantUseCase = new DeleteParticipantUseCase(participantDAO);

        GroupDAO groupDAO = new SqliteGroupDAO();
        createGroupUseCase = new CreateGroupUseCase(groupDAO);
        updateGroupUseCase = new UpdateGroupUseCase(groupDAO);
        findGroupUseCase = new FindGroupUseCase(groupDAO);
        deleteGroupUseCase = new DeleteGroupUseCase(groupDAO);
        addParticipantToGroupUseCase = new AddParticipantToGroupUseCase(
                groupDAO,
                findGroupUseCase,
                findParticipantUseCase);
        removeParticipantFromGroupUseCase = new RemoveParticipantFromGroupUseCase(
                findGroupUseCase,
                findParticipantUseCase,
                groupDAO);
        registerMeetingMinutesToGroup = new RegisterMeetingMinutesToGroup(
                findMeetingMinutesUseCase,
                findGroupUseCase,
                updateGroupUseCase);

        MeetingMinutesDAO meetingMinutesDAO = new SqliteMeetingMinutesDAO();
        createMeetingMinutesUseCase = new CreateMeetingMinutesUseCase(meetingMinutesDAO);
        findMeetingMinutesUseCase = new FindMeetingMinutesUseCase(meetingMinutesDAO);
        deleteMeetingMinuteUseCase = new DeleteMeetingMinuteUseCase(meetingMinutesDAO);
        updateMeetingMinutesUseCase = new UpdateMeetingMinutesUseCase(meetingMinutesDAO);
        includeInformToMeetingMinutesUseCase = new IncludeInformToMeetingMinutesUseCase(
                findInformUseCase,
                findMeetingMinutesUseCase,
                updateMeetingMinutesUseCase);
        includeLogoToMeetingMinuteUseCase = new IncludeLogoToMeetingMinuteUseCase(
                updateMeetingMinutesUseCase
        );
        includeScheduleToMeetingMinutesUseCase = new IncludeScheduleToMeetingMinutesUseCase(
                findScheduleUseCase,
                findMeetingMinutesUseCase,
                updateMeetingMinutesUseCase
        );

        InformDAO informDAO = new SqliteInformDAO();
        createInformUseCase = new CreateInformUseCase(informDAO);
        deleteInformUseCase = new DeleteInformUseCase(informDAO);
        findInformUseCase = new FindInformUseCase(informDAO);
        updateInformUseCase = new UpdateInformUseCase(informDAO);

        ScheduleDAO scheduleDAO = new SqliteScheduleDAO();
        createScheduleUseCase = new CreateScheduleUseCase(scheduleDAO);
        deleteScheduleUseCase = new DeleteScheduleUseCase(scheduleDAO);
        findScheduleUseCase = new FindScheduleUseCase(scheduleDAO);
        updateScheduleUseCase = new UpdateScheduleUseCase(scheduleDAO);
        registerVotingToScheduleUseCase = new RegisterVotingToScheduleUseCase(
                findVotingUseCase,
                findScheduleUseCase,
                updateScheduleUseCase
        );
        registerCommentToScheduleUseCase = new RegisterCommentToScheduleUseCase(
                findCommentUseCase,
                findScheduleUseCase,
                updateScheduleUseCase
        );

        CommentDAO commentDAO = new SqliteCommentDAO();
        createCommentUseCase = new CreateCommentUseCase(commentDAO);
        updateCommentUseCase = new UpdateCommentUseCase(commentDAO);
        deleteCommentUseCase = new DeleteCommentUseCase(commentDAO);
        findCommentUseCase = new FindCommentUseCase(commentDAO);


        VoteDAO voteDAO = new SqliteVoteDAO();
        createVoteUseCase = new CreateVoteUseCase(voteDAO);
        updateVoteUseCase = new UpdateVoteUseCase(voteDAO);
        findVoteUseCase = new FindVoteUseCase(voteDAO);
        deleteVoteUseCase = new DeleteVoteUseCase(voteDAO);

        VotingDAO votingDAO = new SqliteVotingDAO();
        createVotingUseCase = new CreateVotingUseCase(votingDAO);
        updateVotingUseCase = new UpdateVotingUseCase(votingDAO);
        deleteVotingUseCase = new DeleteVotingUseCase(votingDAO);
        findVotingUseCase = new FindVotingUseCase(votingDAO);
        registerVoteToVoting = new RegisterVoteToVoting(
                votingDAO,
                voteDAO
        );
        unregisterVoteToVoting = new UnregisterVoteToVoting(
                votingDAO,
                voteDAO
        );
    }

}
