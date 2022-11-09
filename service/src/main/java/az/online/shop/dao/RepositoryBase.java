package az.online.shop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E> implements Dao<K, E> {

    private final Class<E> clazz;
    @Getter
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        entityManager.remove(id);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id, Map<String, Object> properties) {
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
    }


    @Override
    public List<E> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteria = criteriaBuilder.createQuery(clazz);
        Root<E> clazz = criteria.from(this.clazz);
        criteria.select(clazz);

        return entityManager.createQuery(criteria)
                .getResultList();
    }
}