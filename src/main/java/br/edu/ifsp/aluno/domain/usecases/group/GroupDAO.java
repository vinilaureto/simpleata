package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.Optional;

public interface GroupDAO extends DAO<Group, Integer> {

    Optional<Group> findByName(String name);

}
