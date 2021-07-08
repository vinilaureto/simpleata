package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateInformUseCase {
    private InformDAO informDAO;

    public CreateInformUseCase(InformDAO informDAO) {
        this.informDAO = informDAO;
    }

    public Integer insert(Inform inform) {
        Validator<Inform> validator = new InformInputRequestValidator();
        Notification notification = validator.validate(inform);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        return informDAO.insert(inform);
    }
}
