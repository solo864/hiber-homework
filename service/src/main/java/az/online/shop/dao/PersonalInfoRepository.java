package az.online.shop.dao;

import az.online.shop.entity.PersonalInfo;
import javax.persistence.EntityManager;

public class PersonalInfoRepository extends RepositoryBase<Integer, PersonalInfo> {

    public PersonalInfoRepository(EntityManager entityManager) {
        super(PersonalInfo.class, entityManager);
    }

}