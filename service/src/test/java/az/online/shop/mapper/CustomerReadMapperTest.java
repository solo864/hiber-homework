package az.online.shop.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

import az.online.shop.dto.CustomerReadDto;
import az.online.shop.util.TestDataImporter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(Lifecycle.PER_CLASS)
class CustomerReadMapperTest {

    private OrderReadMapper orderReadMapper;
    private CustomerReadMapper customerReadMapper;

    @BeforeAll
    void init() {
        orderReadMapper = new OrderReadMapper();
        customerReadMapper = new CustomerReadMapper(orderReadMapper);
    }

    @Test
    void mapEntityToDto() {
        var expectedResult = TestDataImporter.getCleveland();
        CustomerReadDto actualResult = customerReadMapper.mapFrom(expectedResult);

        Assertions.assertAll((
                ) -> assertThat(actualResult.firstName()).isEqualTo(expectedResult.getFirstName()),
                () -> assertThat(actualResult.birthDate()).isEqualTo(expectedResult.getBirthDate()),
                () -> assertThat(actualResult.role()).isEqualTo(expectedResult.getRole())
        );
    }

    @Test
    void mapEntitiesToDtos() {
        var expectedResults = List.of(TestDataImporter.getCleveland(), TestDataImporter.getIsobelle());
        List<CustomerReadDto> actualResults = customerReadMapper.mapFrom(expectedResults);

        for (int i = 0; i < actualResults.size(); i++) {
            assertThat(actualResults.get(i).firstName()).isEqualTo(expectedResults.get(i).getFirstName());
            assertThat(actualResults.get(i).birthDate()).isEqualTo(expectedResults.get(i).getBirthDate());
            assertThat(actualResults.get(i).role()).isEqualTo(expectedResults.get(i).getRole());
        }
    }
}