package az.online.shop.service;

import az.online.shop.dao.CustomerRepository;
import az.online.shop.dto.CustomerCreateDto;
import az.online.shop.dto.CustomerReadDto;
import az.online.shop.entity.Customer;
import az.online.shop.mapper.CustomerCreateMapper;
import az.online.shop.mapper.CustomerReadMapper;
import az.online.shop.model.Status;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerReadMapper customerReadMapper;
    private final CustomerCreateMapper customerCreateMapper;


    public Integer create(CustomerCreateDto customerDto) {
        Customer customer = customerCreateMapper.mapFrom(customerDto);
        return customerRepository.save(customer).getId();
    }

    public List<CustomerReadDto> getAllCustomersByOrderStatus(Status status){
        List<Customer> customers = customerRepository.findAllCustomersByStatus(status);
        return customerReadMapper.mapFrom(customers);
    }

    public Optional<CustomerReadDto> findById(Integer id) {
        Map<String, Object> properties = Map.of(
                GraphSemantic.LOAD.getJpaHintName(),
                customerRepository.getEntityManager().getEntityGraph("WithOrders")
        );
        return customerRepository.findById(id, properties)
                .map(customerReadMapper::mapFrom);
    }

    public boolean delete(Integer id) {
        Optional<Customer> maybeCustomer = customerRepository.findById(id);
        maybeCustomer.ifPresent(customer -> customerRepository.delete(customer.getId()));
        return maybeCustomer.isPresent();
    }
}
