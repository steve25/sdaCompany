package sk.pocsik.service;

import java.util.ArrayList;
import java.util.List;

public class BaseService <T> {
    private final List<T> repository = new ArrayList<>();

    public void create(T entity) {
        repository.add(entity);
    }

    public List<T> getAll() {
        return List.copyOf(repository);
    }

    public T getAt(int seqNo) {
        return repository.isEmpty()? null : repository.get(seqNo);
    }

    public T remove(int index) {
        if (index < 0 || index >= repository.size()) {
            throw new IllegalArgumentException("Invalid entity index: " + index);
        }

        return repository.remove(index);
    }
}
