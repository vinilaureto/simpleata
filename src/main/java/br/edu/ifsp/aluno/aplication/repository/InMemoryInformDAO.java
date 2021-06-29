package br.edu.ifsp.aluno.aplication.repository;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.usecases.inform.InformDAO;

import java.util.*;

public class InMemoryInformDAO implements InformDAO {
    private static final Map<String, Inform> db = new LinkedHashMap<>();

    @Override
    public String insert(Inform inform) {
        String title = inform.getTitle();
        db.put(title, inform);
        return null;
    }

    @Override
    public Optional<Inform> findOne(String title) {
        if (db.containsKey(title)) {
            return Optional.of(db.get(title));
        }
        return Optional.empty();
    }

    @Override
    public List<Inform> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Inform inform) {
        String title = inform.getTitle();
        if (db.containsKey(title)) {
            db.replace(title, inform);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String title) {
        if (db.containsKey(title)) {
            db.remove(title);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Inform inform) {
        return deleteByKey(inform.getTitle());
    }
}
