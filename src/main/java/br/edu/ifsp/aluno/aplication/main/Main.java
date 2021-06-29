package br.edu.ifsp.aluno.aplication.main;

import br.edu.ifsp.aluno.aplication.repository.InMemoryGroupDAO;
import br.edu.ifsp.aluno.aplication.repository.InMemoryParticipantDAO;
import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.group.*;
import br.edu.ifsp.aluno.domain.usecases.participant.*;

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



    }
}
