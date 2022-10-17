package az.online.shop.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import az.online.shop.dto.OrderReadDto;
import az.online.shop.entity.Order;
import az.online.shop.util.TestDataImporter;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(PER_CLASS)
class OrderReadMapperTest {

    private OrderReadMapper orderReadMapper;

    @BeforeAll
    void init() {
        orderReadMapper = new OrderReadMapper();
    }

    @Test
    void mapEntityToDto() {
        Order expectedResult = TestDataImporter.getClevelandOrders().get(0);
        OrderReadDto actualResult = orderReadMapper.mapFrom(expectedResult);

        assertThat(actualResult.closingDate()).isEqualTo(expectedResult.getClosingDate());
        assertThat(actualResult.registrationDate()).isEqualTo(expectedResult.getRegistrationDate());
    }

    @Test
    void mapEntitiesToDtos(){
        List<Order> expectedResults = TestDataImporter.getIsobelleOrders();
        List<OrderReadDto> actualResults = orderReadMapper.mapFrom(expectedResults);

        for (int i = 0; i < actualResults.size(); i++) {
            assertThat(actualResults.get(i).closingDate()).isEqualTo(expectedResults.get(i).getClosingDate());
            assertThat(actualResults.get(i).registrationDate()).isEqualTo(expectedResults.get(i).getRegistrationDate());

        }
    }
}