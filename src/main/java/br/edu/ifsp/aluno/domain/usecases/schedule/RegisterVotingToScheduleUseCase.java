package br.edu.ifsp.aluno.domain.usecases.schedule;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.voting.FindVotingUseCase;

public class RegisterVotingToScheduleUseCase {
    private FindVotingUseCase findVotingUseCase;
    private FindScheduleUseCase findScheduleUseCase;
    private UpdateScheduleUseCase updateScheduleUseCase;

    public RegisterVotingToScheduleUseCase(FindVotingUseCase findVotingUseCase, FindScheduleUseCase findScheduleUseCase, UpdateScheduleUseCase updateScheduleUseCase) {
        this.findVotingUseCase = findVotingUseCase;
        this.findScheduleUseCase = findScheduleUseCase;
        this.updateScheduleUseCase = updateScheduleUseCase;
    }

    public boolean registerVotingToUseCase(Voting voting, Schedule schedule) {
        if (findVotingUseCase.findOne(voting.getId()).isEmpty()) {
            throw new EntityNotFoundException("Voting not found");
        }

        if (findScheduleUseCase.findOne(schedule.getId()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found");
        }

        schedule.setVoting(voting);
        return updateScheduleUseCase.update(schedule);
    }
}
