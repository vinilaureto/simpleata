package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.comment.FindCommentUseCase;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class RegisterCommentToScheduleUseCase {
    private FindCommentUseCase findCommentUseCase;
    private FindScheduleUseCase findScheduleUseCase;
    private UpdateScheduleUseCase updateScheduleUseCase;

    public RegisterCommentToScheduleUseCase(FindCommentUseCase findCommentUseCase, FindScheduleUseCase findScheduleUseCase, UpdateScheduleUseCase updateScheduleUseCase) {
        this.findCommentUseCase = findCommentUseCase;
        this.findScheduleUseCase = findScheduleUseCase;
        this.updateScheduleUseCase = updateScheduleUseCase;
    }

    public boolean registerCommentToSchedule(Comment comment, Schedule schedule) {
        if (findCommentUseCase.findOne(comment.getId()).isEmpty()) {
            throw new EntityNotFoundException("Comment not found");
        }

        if (findScheduleUseCase.findOne(schedule.getId()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found");
        }

        schedule.addComment(comment);
        return updateScheduleUseCase.update(schedule);
    }
}
