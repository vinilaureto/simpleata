package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.usecases.participant.ParticipantDAO;
import br.edu.ifsp.aluno.domain.usecases.participant.ParticipantInputRequestValidator;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateInformUseCase {
    private InformDAO informDAO;

    public UpdateInformUseCase(InformDAO informDAO) {
        this.informDAO = informDAO;
    }

    public boolean update(Inform inform) {
        Validator<Inform> validator = new InformInputRequestValidator();
        Notification notification = validator.validate(inform);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        Integer id = inform.getId();
        if (informDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Inform not found.");
        }

        return informDAO.update(inform);
    }
}
