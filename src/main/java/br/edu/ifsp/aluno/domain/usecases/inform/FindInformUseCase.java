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

    public Optional<Inform> findOne(String title) {
        if (Validator.isNullOrEmpty(title)) {
            throw new IllegalArgumentException("Inform can not be null.");
        }
        return informDAO.findOne(title);
    }

    public List<Inform> findAll() {
        return informDAO.findAll();
    }
}
