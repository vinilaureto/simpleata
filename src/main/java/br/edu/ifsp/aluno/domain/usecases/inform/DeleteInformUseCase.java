package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteInformUseCase {
    private InformDAO informDao;

    public DeleteInformUseCase(InformDAO informDao) {
        this.informDao = DeleteInformUseCase.this.informDao;
    }

    public boolean delete(String title) {
        if (title == null || informDao.findOne(title).isEmpty()) {
            throw new EntityNotFoundException("Inform not found.");
        }
        return informDao.deleteByKey(title);
    }

    public boolean delete(Inform inform) {
        if (inform == null || informDao.findOne(inform.getTitle()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }
        return informDao.delete(inform);
    }
}
