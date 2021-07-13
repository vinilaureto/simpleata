package br.edu.ifsp.aluno.aplication.main;

import br.edu.ifsp.aluno.aplication.repository.inMemory.InMemoryMeetingMinutesDAO;
import br.edu.ifsp.aluno.aplication.repository.inMemory.InMemoryParticipantDAO;
import br.edu.ifsp.aluno.aplication.view.WindowLoader;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.comment.CreateCommentUseCase;
import br.edu.ifsp.aluno.domain.usecases.comment.DeleteCommentUseCase;
import br.edu.ifsp.aluno.domain.usecases.comment.FindCommentUseCase;
import br.edu.ifsp.aluno.domain.usecases.comment.UpdateCommentUseCase;
import br.edu.ifsp.aluno.domain.usecases.group.*;
import br.edu.ifsp.aluno.domain.usecases.inform.CreateInformUseCase;
import br.edu.ifsp.aluno.domain.usecases.inform.DeleteInformUseCase;
import br.edu.ifsp.aluno.domain.usecases.inform.FindInformUseCase;
import br.edu.ifsp.aluno.domain.usecases.inform.UpdateInformUseCase;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.*;
import br.edu.ifsp.aluno.domain.usecases.participant.*;
import br.edu.ifsp.aluno.domain.usecases.schedule.*;
import br.edu.ifsp.aluno.domain.usecases.vote.CreateVoteUseCase;
import br.edu.ifsp.aluno.domain.usecases.vote.DeleteVoteUseCase;
import br.edu.ifsp.aluno.domain.usecases.vote.FindVoteUseCase;
import br.edu.ifsp.aluno.domain.usecases.vote.UpdateVoteUseCase;
import br.edu.ifsp.aluno.domain.usecases.voting.*;

import java.time.LocalDate;

public class Main {
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

    public static CreateVoteUseCase createVoteUseCase;
    public static UpdateVoteUseCase updateVoteUseCase;
    public static FindVoteUseCase findVoteUseCase;
    public static DeleteVoteUseCase deleteVoteUseCase;

    public static void main(String[] args) {
        configureDependencies();
        populateFakeDatabase();
        WindowLoader.main(args);
    }

    private static void populateFakeDatabase() {
        Participant participant1 = new Participant("João da Silva", "joao@gmail.com", "Professor(a)");
        createParticipantUseCase.insert(participant1);
        Participant participant2 = new Participant("Maria da Silva", "maria@gmail.com", "Doutor(a)");
        createParticipantUseCase.insert(participant2);
    }

    private static void configureDependencies() {
        MeetingMinutesDAO meetingMinutesDAO = new InMemoryMeetingMinutesDAO();
        findMeetingMinutesUseCase = new FindMeetingMinutesUseCase(meetingMinutesDAO);

        ParticipantDAO participantDAO = new InMemoryParticipantDAO();
        createParticipantUseCase = new CreateParticipantUseCase(participantDAO);
        findParticipantUseCase = new FindParticipantUseCase(participantDAO);
        updateParticipantUseCase = new UpdateParticipantUseCase(participantDAO);
        deleteParticipantUseCase = new DeleteParticipantUseCase(participantDAO);
    }

















//    private static CreateParticipantUseCase createParticipantUseCase;
//    private static FindParticipantUseCase findParticipantUseCase;
//    private static DeleteParticipantUseCase deleteParticipantUseCase;
//    private static UpdateParticipantUseCase updateParticipantUseCase;
//
//    private static CreateGroupUseCase createGroupUseCase;
//    private static FindGroupUseCase findGroupUseCase;
//    private static DeleteGroupUseCase deleteGroupUseCase;
//    private static UpdateGroupUseCase updateGroupUseCase;
//    private static AddParticipantToGroupUseCase addParticipantToGroupUseCase;
//    private static RemoveParticipantFromGroupUseCase removeParticipantFromGroupUseCase;
//    private static RegisterMeetingMinutesToGroup registerMeetingMinutesToGroup;
//
//    private static CreateMeetingMinutesUseCase createMeetingMinutesUseCase;
//    private static FindMeetingMinutesUseCase findMeetingMinutesUseCase;
//    private static DeleteMeetingMinuteUseCase deleteMeetingMinuteUseCase;
//    private static UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;
//    private static ChangeStatusMeetingMinutesUseCase changeStatusMeetingMinutesUseCase;
//    private static IncludeInformToMeetingMinutesUseCase includeInformToMeetingMinutesUseCase;
//    private static IncludeLogoToMeetingMinuteUseCase includeLogoToMeetingMinuteUseCase;
//    private static IncludeScheduleToMeetingMinutesUseCase includeScheduleToMeetingMinutesUseCase;
//
//    private static CreateInformUseCase createInformUseCase;
//    private static DeleteInformUseCase deleteInformUseCase;
//    private static FindInformUseCase findInformUseCase;
//    private static UpdateInformUseCase updateInformUseCase;
//
//    private static CreateScheduleUseCase createScheduleUseCase;
//    private static FindScheduleUseCase findScheduleUseCase;
//    private static DeleteScheduleUseCase deleteScheduleUseCase;
//    private static UpdateScheduleUseCase updateScheduleUseCase;
//    private static RegisterVotingToScheduleUseCase registerVotingToScheduleUseCase;
//    private static RegisterCommentToScheduleUseCase registerCommentToScheduleUseCase;
//
//    private static CreateCommentUseCase createCommentUseCase;
//    private static UpdateCommentUseCase updateCommentUseCase;
//    private static DeleteCommentUseCase deleteCommentUseCase;
//    private static FindCommentUseCase findCommentUseCase;
//
//    private static CreateVotingUseCase createVotingUseCase;
//    private static UpdateVotingUseCase updateVotingUseCase;
//    private static DeleteVotingUseCase deleteVotingUseCase;
//    private static FindVotingUseCase findVotingUseCase;
//    private static RegisterVoteToVoting registerVoteToVoting;
//
//    private static CreateVoteUseCase createVoteUseCase;
//    private static UpdateVoteUseCase updateVoteUseCase;
//    private static FindVoteUseCase findVoteUseCase;
//    private static DeleteVoteUseCase deleteVoteUseCase;




//    public static void main(String[] args) {
//        configureInjection();
//



        //configureInjection();

        /*// TESTES COM PARTICIPANTES
        Participant participant1 = new Participant("João", "joao@gmail.com", "Dr");
        Participant participant2 = new Participant("João2", "joao2@gmail.com", "Dr");
        Participant participant3 = new Participant("João3", "joao3@gmail.com", "Dr");
        createParticipantUseCase.insert(participant1); // ok
        createParticipantUseCase.insert(participant2); // ok
        createParticipantUseCase.insert(participant3); // ok

//        System.out.println(findParticipantUseCase.findOne("joao@gmail.com").toString()); // ok

        deleteParticipantUseCase.delete(participant2); // ok
        participant3.setName("JULIO");
        updateParticipantUseCase.update(participant3); // ok
//        System.out.println(findParticipantUseCase.findAll().toString()); // ok*/


        // TESTES COM GRUPOS
        /*Group group1 = new Group("Grupo legal");
        createGroupUseCase.insert(group1); // ok

        System.out.println(findGroupUseCase.findOne("Grupo legal").toString()); // ok
        addParticipantToGroupUseCase.addParticipantToGroup(participant1, group1); // ok
        //addParticipantToGroupUseCase.addParticipantToGroup(participant2, group1); // ok - dispara a exceção certa
        addParticipantToGroupUseCase.addParticipantToGroup(participant3, group1); // ok

        System.out.println("Todos participantes do grupo 1:" + findParticipantUseCase.findAll());

        removeParticipantFromGroupUseCase.removeParticipantFromGroup(participant3, group1); // ok
        System.out.println(findGroupUseCase.findOne("Grupo legal").toString()); // ok

        Group group2 = new Group("Grupo bacana");
        createGroupUseCase.insert(group2);
        addParticipantToGroupUseCase.addParticipantToGroup(participant1, group2);
        System.out.println(findGroupUseCase.findAll());*/

        // TESTES COM ATA
        /*MeetingMinutes meetingMinutes1 = new MeetingMinutes();

        meetingMinutes1.setGroup(group1);
        meetingMinutes1.setTitle("G1Meeting1");
        meetingMinutes1.setCreationDate(LocalDate.now());
        createMeetingMinutesUseCase.insert(meetingMinutes1); // ok
        MeetingMinutes meetingMinutes2 = new MeetingMinutes();
        meetingMinutes2.setGroup(group2);
        meetingMinutes2.setTitle("G2Meeting1");
        meetingMinutes2.setCreationDate(LocalDate.now());
        createMeetingMinutesUseCase.insert(meetingMinutes2);
        MeetingMinutes meetingMinutes3 = new MeetingMinutes();
        meetingMinutes3.setGroup(group2);
        meetingMinutes3.setTitle("G2Meeting2");
        meetingMinutes3.setCreationDate(LocalDate.now());
        createMeetingMinutesUseCase.insert(meetingMinutes3);
        //meetingMinutes1.setIdentifier("2021/01");
        includeLogoToMeetingMinuteUseCase.includeLogo("Logo bonito", meetingMinutes1); // ok
        System.out.println(findMeetingMinutesUseCase.findOne("G1Meeting1").toString()); // ok
        System.out.println("Todas atas G2: " + findMeetingMinutesUseCase.findByGroup(group2));

        System.out.println("Atas do participante 1: " + findMeetingMinutesUseCase.findByParticipant(participant1));*/


        // registerMeetingMinutesToGroup.registerMeetingMinutesToGroup(meetingMinutes1, group1); //
        //System.out.println(findGroupUseCase.findOne("Grupo legal"));

        // TESTES COM CASOS DE USOS DOS COMENTÁRIOS OK
//        Comment commentError1 = null;
//        createCommentUseCase.insert(commentError1);
//        Comment commentError2 = new Comment(participant3, "");
//        createCommentUseCase.insert(commentError2);
        /*Comment comment1 = new Comment(participant1, "Comentário do participante 1.");
        createCommentUseCase.insert(comment1);
        System.out.println(findCommentUseCase.findOne(1));
        System.out.println(findCommentUseCase.findOne(2));
        Comment comment2 = new Comment(participant2, "Comentário do participante 2.");
        createCommentUseCase.insert(comment2);
        comment1.setMessage("Mensagem atualizada.");
        updateCommentUseCase.update(comment1);
        System.out.println(findCommentUseCase.findAll());
        deleteCommentUseCase.delete(comment1);
        deleteCommentUseCase.delete(2);
        System.out.println(findCommentUseCase.findAll());*/

        // TESTES COM CASOS DE USOS DOS INFORMES OK
//        Inform informError1 = null;
//        createInformUseCase.insert(informError1);
//        Inform informError2 = new Inform("", "Descrição");
//        createInformUseCase.insert(informError2);
//        Inform informError3 = new Inform("Título", "");
//        createInformUseCase.insert(informError3);
        /*Inform inform1 = new Inform("Título 1", "Descrição 1.");
        createInformUseCase.insert(inform1);
        System.out.println(findInformUseCase.findOne(1));
        System.out.println(findInformUseCase.findOne(2));
        Inform inform2 = new Inform("Título 2", "Descrição 2.");
        createInformUseCase.insert(inform2);
        inform1.setTitle("Novo Título");
        updateInformUseCase.update(inform1);
        System.out.println(findInformUseCase.findAll());
        deleteInformUseCase.delete(1);
        deleteInformUseCase.delete(inform2);
        System.out.println(findInformUseCase.findAll());*/

//        TESTES COM CASOS DE USO DE PAUTAS
//        Schedule scheduleError1 = null;
//        createScheduleUseCase.insert(scheduleError1);
//        Schedule scheduleError2 = new Schedule("", "Descrição");
//        createScheduleUseCase.insert(scheduleError2);
//        Schedule scheduleError3 = new Schedule("Tópico", "");
//        createScheduleUseCase.insert(scheduleError3);
//        Schedule schedule1 = new Schedule("Pauta1", "Descrição da Pauta1");
//        createScheduleUseCase.insert(schedule1);
//        System.out.println(findScheduleUseCase.findOne("Pauta1"));
//        System.out.println(findScheduleUseCase.findOne("Pauta2"));
//        Schedule schedule2 = new Schedule("Pauta2", "Descrição da Pauta2");
//        createScheduleUseCase.insert(schedule2);
//        schedule1.setDescription("Nova descricação da Pauta1");
//        updateScheduleUseCase.update(schedule1);
//        System.out.println(findScheduleUseCase.findAll());
//        deleteScheduleUseCase.delete(schedule1);
//        System.out.println(findScheduleUseCase.findAll());


//    }

//    private static void configureInjection() {
//        ParticipantDAO participantDAO = new InMemoryParticipantDAO();
//        createParticipantUseCase = new CreateParticipantUseCase(participantDAO);
//        updateParticipantUseCase = new UpdateParticipantUseCase(participantDAO);
//        findParticipantUseCase = new FindParticipantUseCase(participantDAO);
//        deleteParticipantUseCase = new DeleteParticipantUseCase(participantDAO);
//
//        GroupDAO groupDAO = new InMemoryGroupDAO();
//        createGroupUseCase = new CreateGroupUseCase(groupDAO);
//        updateGroupUseCase = new UpdateGroupUseCase(groupDAO);
//        findGroupUseCase = new FindGroupUseCase(groupDAO);
//        deleteGroupUseCase = new DeleteGroupUseCase(groupDAO);
//        addParticipantToGroupUseCase = new AddParticipantToGroupUseCase(
//                updateGroupUseCase,
//                findGroupUseCase,
//                findParticipantUseCase);
//        removeParticipantFromGroupUseCase = new RemoveParticipantFromGroupUseCase(
//                findGroupUseCase,
//                findParticipantUseCase,
//                updateGroupUseCase);
//        registerMeetingMinutesToGroup = new RegisterMeetingMinutesToGroup(
//                findMeetingMinutesUseCase,
//                findGroupUseCase,
//                updateGroupUseCase);
//
//        MeetingMinutesDAO meetingMinutesDAO = new InMemoryMeetingMinutesDAO();
//        createMeetingMinutesUseCase = new CreateMeetingMinutesUseCase(meetingMinutesDAO);
//        findMeetingMinutesUseCase = new FindMeetingMinutesUseCase(meetingMinutesDAO);
//        deleteMeetingMinuteUseCase = new DeleteMeetingMinuteUseCase(meetingMinutesDAO);
//        updateMeetingMinutesUseCase = new UpdateMeetingMinutesUseCase(meetingMinutesDAO);
//        includeInformToMeetingMinutesUseCase = new IncludeInformToMeetingMinutesUseCase(
//                findInformUseCase,
//                findMeetingMinutesUseCase,
//                updateMeetingMinutesUseCase);
//        includeLogoToMeetingMinuteUseCase = new IncludeLogoToMeetingMinuteUseCase(
//                updateMeetingMinutesUseCase
//        );
//        includeScheduleToMeetingMinutesUseCase = new IncludeScheduleToMeetingMinutesUseCase(
//                findScheduleUseCase,
//                findMeetingMinutesUseCase,
//                updateMeetingMinutesUseCase
//        );
//
//        InformDAO informDAO = new InMemoryInformDAO();
//        createInformUseCase = new CreateInformUseCase(informDAO);
//        deleteInformUseCase = new DeleteInformUseCase(informDAO);
//        findInformUseCase = new FindInformUseCase(informDAO);
//        updateInformUseCase = new UpdateInformUseCase(informDAO);
//
//        ScheduleDAO scheduleDAO = new InMemoryScheduleDAO();
//        createScheduleUseCase = new CreateScheduleUseCase(scheduleDAO);
//        deleteScheduleUseCase = new DeleteScheduleUseCase(scheduleDAO);
//        findScheduleUseCase = new FindScheduleUseCase(scheduleDAO);
//        updateScheduleUseCase = new UpdateScheduleUseCase(scheduleDAO);
//        registerVotingToScheduleUseCase = new RegisterVotingToScheduleUseCase(
//                findVotingUseCase,
//                findScheduleUseCase,
//                updateScheduleUseCase
//        );
//        registerCommentToScheduleUseCase = new RegisterCommentToScheduleUseCase(
//                findCommentUseCase,
//                findScheduleUseCase,
//                updateScheduleUseCase
//        );
//
//        CommentDAO commentDAO = new InMemoryCommentDAO();
//        createCommentUseCase = new CreateCommentUseCase(commentDAO);
//        updateCommentUseCase = new UpdateCommentUseCase(commentDAO);
//        deleteCommentUseCase = new DeleteCommentUseCase(commentDAO);
//        findCommentUseCase = new FindCommentUseCase(commentDAO);
//
//        VotingDAO votingDAO = new InMemoryVotingDAO();
//        createVotingUseCase = new CreateVotingUseCase(votingDAO);
//        updateVotingUseCase = new UpdateVotingUseCase(votingDAO);
//        deleteVotingUseCase = new DeleteVotingUseCase(votingDAO);
//        findVotingUseCase = new FindVotingUseCase(votingDAO);
//        registerVoteToVoting = new RegisterVoteToVoting(
//                findVoteUseCase,
//                findVotingUseCase,
//                updateVotingUseCase
//        );
//
//        VoteDAO voteDAO = new InMemoryVoteDAO();
//        createVoteUseCase = new CreateVoteUseCase(voteDAO);
//        updateVoteUseCase = new UpdateVoteUseCase(voteDAO);
//        findVoteUseCase = new FindVoteUseCase(voteDAO);
//        deleteVoteUseCase = new DeleteVoteUseCase(voteDAO);
//    }

//    private static void configureInjection() {
//
//    }
}
