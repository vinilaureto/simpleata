package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateInformUseCase {
    public boolean associateInform(MeetingMinutes meetingMinutes, Inform inform) {
        if (meetingMinutes == null) {
            throw new EntityNotFoundException("Meeting minutes not found");
        }

        if (inform == null) {
            throw new EntityNotFoundException("Inform not found");
        }

        Validator<Inform> validator = new InformInputRequestValidator();
        Notification notification = validator.validate(inform);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        meetingMinutes.addInform(inform);
        return true;
    }
}
