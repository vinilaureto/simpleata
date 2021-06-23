package br.edu.ifsp.aluno.aplication.main;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;

public class Main {
    public static void main(String[] args) {
        Participant participant1 = new Participant("João", "joão@gmail.com", "Dr");
        Participant participant2 = new Participant("João2", "joão@gmail.com", "Dr");
        Participant participant3 = new Participant("João3", "joão@gmail.com", "Dr");

        Group group = new Group("Grupo legal");
        group.addParticipant(participant1);
        group.addParticipant(participant2);
        group.addParticipant(participant3);

        System.out.println(group.toString());

        Comment comment = new Comment(participant1, "comentario");
        Comment comment2 = new Comment("comentario");
    }
}
