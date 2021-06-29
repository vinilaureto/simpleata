package br.edu.ifsp.aluno.aplication.main;

import br.edu.ifsp.aluno.aplication.repository.*;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.comment.*;
import br.edu.ifsp.aluno.domain.usecases.group.*;
import br.edu.ifsp.aluno.domain.usecases.inform.*;
import br.edu.ifsp.aluno.domain.usecases.meetingMinutes.*;
import br.edu.ifsp.aluno.domain.usecases.participant.*;
import br.edu.ifsp.aluno.domain.usecases.schedule.*;
import br.edu.ifsp.aluno.domain.usecases.vote.*;
import br.edu.ifsp.aluno.domain.usecases.voting.*;

public class Main {
    private static CreateParticipantUseCase createParticipantUseCase;
    private static FindParticipantUseCase findParticipantUseCase;
    private static DeleteParticipantUseCase deleteParticipantUseCase;
    private static UpdateParticipantUseCase updateParticipantUseCase;

    private static CreateGroupUseCase createGroupUseCase;
    private static FindGroupUseCase findGroupUseCase;
    private static DeleteGroupUseCase deleteGroupUseCase;
    private static UpdateGroupUseCase updateGroupUseCase;
    private static AddParticipantToGroupUseCase addParticipantToGroupUseCase;
    private static RemoveParticipantFromGroupUseCase removeParticipantFromGroupUseCase;
    private static RegisterMeetingMinutesToGroup registerMeetingMinutesToGroup;

    private static CreateMeetingMinutesUseCase createMeetingMinutesUseCase;
    private static FindMeetingMinutesUseCase findMeetingMinutesUseCase;
    private static DeleteMeetingMinuteUseCase deleteMeetingMinuteUseCase;
    private static UpdateMeetingMinutesUseCase updateMeetingMinutesUseCase;
    private static ChangeStatusMeetingMinutesUseCase changeStatusMeetingMinutesUseCase;
    private static IncludeInformToMeetingMinutesUseCase includeInformToMeetingMinutesUseCase;
    private static IncludeLogoToMeetingMinuteUseCase includeLogoToMeetingMinuteUseCase;
    private static IncludeScheduleToMeetingMinutesUseCase includeScheduleToMeetingMinutesUseCase;

    private static CreateInformUseCase createInformUseCase;
    private static DeleteInformUseCase deleteInformUseCase;
    private static FindInformUseCase findInformUseCase;
    private static UpdateInformUseCase updateInformUseCase;

    private static CreateScheduleUseCase createScheduleUseCase;
    private static FindScheduleUseCase findScheduleUseCase;
    private static DeleteScheduleUseCase deleteScheduleUseCase;
    private static UpdateScheduleUseCase updateScheduleUseCase;
    private static RegisterVotingToScheduleUseCase registerVotingToScheduleUseCase;
    private static RegisterCommentToScheduleUseCase registerCommentToScheduleUseCase;

    private static CreateCommentUseCase createCommentUseCase;
    private static UpdateCommentUseCase updateCommentUseCase;
    private static DeleteCommentUseCase deleteCommentUseCase;
    private static FindCommentUseCase findCommentUseCase;

    private static CreateVotingUseCase createVotingUseCase;
    private static UpdateVotingUseCase updateVotingUseCase;
    private static DeleteVotingUseCase deleteVotingUseCase;
    private static FindVotingUseCase findVotingUseCase;
    private static RegisterVoteToVoting registerVoteToVoting;

    private static CreateVoteUseCase createVoteUseCase;
    private static UpdateVoteUseCase updateVoteUseCase;
    private static FindVoteUseCase findVoteUseCase;
    private static DeleteVoteUseCase deleteVoteUseCase;




    public static void main(String[] args) {
        configureInjection();

        // TESTES COM PARTICIPANTES
        Participant participant1 = new Participant("João", "joao@gmail.com", "Dr");
        Participant participant2 = new Participant("João2", "joao2@gmail.com", "Dr");
        Participant participant3 = new Participant("João3", "joao3@gmail.com", "Dr");
        createParticipantUseCase.insert(participant1); // ok
        createParticipantUseCase.insert(participant2); // ok
        createParticipantUseCase.insert(participant3); // ok

        System.out.println(findParticipantUseCase.findOne("joao@gmail.com").toString()); // ok

        deleteParticipantUseCase.delete(participant2); // ok
        participant3.setName("JULIO");
        updateParticipantUseCase.update(participant3); // ok
        System.out.println(findParticipantUseCase.findAll().toString()); // ok


        // TESTES COM GRUPOS
        Group group1 = new Group("Grupo legal");
        createGroupUseCase.insert(group1); // ok

        System.out.println(findGroupUseCase.findOne("Grupo legal").toString()); // ok
        addParticipantToGroupUseCase.addParticipantToGroup(participant1, group1); // ok
        //addParticipantToGroupUseCase.addParticipantToGroup(participant2, group1); // ok - dispara a excessão certa
        addParticipantToGroupUseCase.addParticipantToGroup(participant3, group1); // ok
        removeParticipantFromGroupUseCase.removeParticipantFromGroup(participant3, group1); // ok
        System.out.println(findGroupUseCase.findOne("Grupo legal").toString()); // ok


        // TESTES COM ATA
        MeetingMinutes meetingMinutes1 = new MeetingMinutes();
        meetingMinutes1.setTitle("Meeting1");
        meetingMinutes1.setIdentifier("2021/01");
        createMeetingMinutesUseCase.insert(meetingMinutes1); // ok
        includeLogoToMeetingMinuteUseCase.includeLogo("Logo bonito", meetingMinutes1); // ok
        System.out.println(findMeetingMinutesUseCase.findOne("Meeting1").toString()); // ok

        // registerMeetingMinutesToGroup.registerMeetingMinutesToGroup(meetingMinutes1, group1); // verificar aqui
        //System.out.println(findGroupUseCase.findOne("Grupo legal"));

        




    }

    private static void configureInjection() {
        ParticipantDAO participantDAO = new InMemoryParticipantDAO();
        createParticipantUseCase = new CreateParticipantUseCase(participantDAO);
        updateParticipantUseCase = new UpdateParticipantUseCase(participantDAO);
        findParticipantUseCase = new FindParticipantUseCase(participantDAO);
        deleteParticipantUseCase = new DeleteParticipantUseCase(participantDAO);

        GroupDAO groupDAO = new InMemoryGroupDAO();
        createGroupUseCase = new CreateGroupUseCase(groupDAO);
        updateGroupUseCase = new UpdateGroupUseCase(groupDAO);
        findGroupUseCase = new FindGroupUseCase(groupDAO);
        deleteGroupUseCase = new DeleteGroupUseCase(groupDAO);
        addParticipantToGroupUseCase = new AddParticipantToGroupUseCase(
                updateGroupUseCase,
                findGroupUseCase,
                findParticipantUseCase);
        removeParticipantFromGroupUseCase = new RemoveParticipantFromGroupUseCase(
                findGroupUseCase,
                findParticipantUseCase,
                updateGroupUseCase);
        registerMeetingMinutesToGroup = new RegisterMeetingMinutesToGroup(
                findMeetingMinutesUseCase,
                findGroupUseCase,
                updateGroupUseCase);

        MeetingMinutesDAO meetingMinutesDAO = new InMemorymeetingMinutesDAO();
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

        InformDAO informDAO = new InMemoryInformDAO();
        createInformUseCase = new CreateInformUseCase(informDAO);
        deleteInformUseCase = new DeleteInformUseCase(informDAO);
        findInformUseCase = new FindInformUseCase(informDAO);
        updateInformUseCase = new UpdateInformUseCase(informDAO);

        ScheduleDAO scheduleDAO = new InMemoryScheduleDAO();
        createScheduleUseCase = new CreateScheduleUseCase(scheduleDAO);
        deleteScheduleUseCase = new DeleteScheduleUseCase(scheduleDAO);
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

        CommentDAO commentDAO = new InMemoryCommentDAO();
        createCommentUseCase = new CreateCommentUseCase(commentDAO);
        updateCommentUseCase = new UpdateCommentUseCase(commentDAO);
        deleteCommentUseCase = new DeleteCommentUseCase(commentDAO);
        findCommentUseCase = new FindCommentUseCase(commentDAO);

        VotingDAO votingDAO = new InMemoryVotingDAO();
        createVotingUseCase = new CreateVotingUseCase(votingDAO);
        updateVotingUseCase = new UpdateVotingUseCase(votingDAO);
        deleteVotingUseCase = new DeleteVotingUseCase(votingDAO);
        findVotingUseCase = new FindVotingUseCase(votingDAO);
        registerVoteToVoting = new RegisterVoteToVoting(
                findVoteUseCase,
                findVotingUseCase,
                updateVotingUseCase
        );

        VoteDAO voteDAO = new InMemoryVoteDAO();
        createVoteUseCase = new CreateVoteUseCase(voteDAO);
        updateVoteUseCase = new UpdateVoteUseCase(voteDAO);
        findVoteUseCase = new FindVoteUseCase(voteDAO);
        deleteVoteUseCase = new DeleteVoteUseCase(voteDAO);
    }
}
