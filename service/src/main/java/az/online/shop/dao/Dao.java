package az.online.shop.dao;

import static java.util.Collections.emptyMap;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<K extends Serializable, E> {

    E save(E entity);

    void delete(K id);

    void update(E entity);

    default Optional<E> findById(K id) {
        return findById(id, emptyMap());
    }

    Optional<E> findById(K id, Map<String, Object> properties);

    List<E> findAll();
}