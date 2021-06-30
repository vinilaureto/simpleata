package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindInformUseCase {
    private InformDAO informDAO;

    public FindInformUseCase(InformDAO informDAO) {
        this.informDAO = informDAO;
    }

    public Optional<Inform> findOne(Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("Inform's ID can not be 0.");
        }
        return informDAO.findOne(id);
    }

    public List<Inform> findAll() {
        return informDAO.findAll();
    }
}
