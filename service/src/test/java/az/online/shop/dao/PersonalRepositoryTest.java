package az.online.shop.dao;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import az.online.shop.entity.PersonalInfo;
import az.online.shop.util.HibernateUtil;
import az.online.shop.util.TestDataImporter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@RequiredArgsConstructor
@TestInstance(PER_CLASS)
class PersonalRepositoryTest {


    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final PersonalInfoRepository personalInfoRepository = new PersonalInfoRepository(sessionFactory.openSession());

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }


    @Test
    void getByIdIfPersonalInfoExist() {
        Optional<PersonalInfo> actualResult = personalInfoRepository.findById(1);
        Assertions.assertThat(actualResult).isPresent();
    }

    @Test
    void getByIdIfPersonalInfoIsNotExist() {
        Optional<PersonalInfo> actualResult = personalInfoRepository.findById(Integer.MAX_VALUE);
        Assertions.assertThat(actualResult).isNotPresent();
    }
}
