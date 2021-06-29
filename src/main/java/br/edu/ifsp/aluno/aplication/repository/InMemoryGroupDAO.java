package br.edu.ifsp.aluno.aplication.repository;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.group.GroupDAO;

import java.util.*;

public class InMemoryGroupDAO implements GroupDAO {

    private static final Map<String, Group> db = new LinkedHashMap<>();


    @Override
    public String insert(Group group) {
        String name = group.getName();
        db.put(name, group);
        return name;
    }

    @Override
    public Optional<Group> findOne(String name) {
        if (db.containsKey(name)) {
            return Optional.of(db.get(name));
        }
        return Optional.empty();
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Group group) {
        String name = group.getName();
        if (db.containsKey(name)) {
            db.replace(name, group);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String email) {
        if (db.containsKey(email)) {
            db.remove(email);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Group group) {
        return deleteByKey(group.getName());
    }
}
