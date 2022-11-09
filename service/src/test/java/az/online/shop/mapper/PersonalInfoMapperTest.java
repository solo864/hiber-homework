package az.online.shop.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import az.online.shop.dto.PersonalInfoReadDto;
import az.online.shop.util.TestDataImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(PER_CLASS)
class PersonalInfoMapperTest {

    private PersonalInfoMapper personalInfoMapper;

    @BeforeAll
    void init() {
        personalInfoMapper = new PersonalInfoMapper();
    }


    @Test
    void mapEntityToDto() {
        var expectedResult = TestDataImporter.getClevelandPersonalInfo();
        PersonalInfoReadDto actualResult = personalInfoMapper.mapFrom(expectedResult);

        Assertions.assertAll(
                () -> assertThat(actualResult.gender()).isEqualTo(expectedResult.getGender()),
                () -> assertThat(actualResult.address()).isEqualTo(expectedResult.getAddress()),
                () -> assertThat(actualResult.phoneNumber()).isEqualTo(expectedResult.getPhoneNumber())
        );
    }
}