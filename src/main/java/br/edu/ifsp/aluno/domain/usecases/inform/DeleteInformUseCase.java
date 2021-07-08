package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteInformUseCase {
    private InformDAO informDao;

    public DeleteInformUseCase(InformDAO informDao) {
        this.informDao = informDao;
    }

    public boolean delete(Integer id) {
        if (id == 0 || informDao.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Inform not found.");
        }
        return informDao.deleteByKey(id);
    }

    public boolean delete(Inform inform) {
        if (inform == null || informDao.findOne(inform.getId()).isEmpty()) {
            throw new EntityNotFoundException("Inform not found.");
        }
        return informDao.delete(inform);
    }
}
