package az.online.shop.service;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import az.online.shop.dao.PersonalInfoRepository;
import az.online.shop.dto.PersonalInfoReadDto;
import az.online.shop.mapper.PersonalInfoMapper;
import az.online.shop.util.HibernateUtil;
import az.online.shop.util.TestDataImporter;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(PER_CLASS)
public class PersonalInfoTest {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private PersonalInfoService personalInfoService;
    private PersonalInfoRepository personalInfoRepository;
    private PersonalInfoMapper personalInfoMapper;

    @BeforeAll
    void init() {
        personalInfoMapper = new PersonalInfoMapper();
        personalInfoRepository = new PersonalInfoRepository(sessionFactory.openSession());
        personalInfoService = new PersonalInfoService(personalInfoRepository, personalInfoMapper);
        TestDataImporter.importData(sessionFactory);
    }

    @Test
    void getByIdIfPersonalInfoExist() {
        Optional<PersonalInfoReadDto> actualResult = personalInfoService.getById(1);
        Assertions.assertThat(actualResult).isPresent();
    }

    @Test
    void getByIdIfPersonalInfoNotExist() {
        Optional<PersonalInfoReadDto> actualResult = personalInfoService.getById(Integer.MAX_VALUE);
        Assertions.assertThat(actualResult).isEmpty();
    }
}