package com.example.poc.service;

import com.example.poc.mapper.CustomerMapper;
import com.example.poc.model.dto.CustomerDto;
import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.poc.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerServiceImpl customerService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_a_list_getAllCustomers_returns_list() {
        when(customerMapper.convertToDto(any(Customer.class))).thenReturn(createTestCustomerDTO());
        when(customerRepository.findAll()).thenReturn(testCustomerList());
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        assertNotNull(customerDtoList);
        assertEquals(3, customerDtoList.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void given_an_empty_list_getAllCustomers_returns_an_error() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        Throwable exception =
                assertThrows(NullPointerException.class,
                        () -> customerService.getAllCustomers());
        assertEquals(exception.getMessage(), "Null objects cannot be mapped");
    }

    @Test
    public void given_an_existing_id_findById_returns_a_customer() {
        Integer id = 2;
        when(customerMapper.convertToDto(any(Customer.class))).thenReturn(createTestCustomerDTO());
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(createTestCustomer()));
        assertEquals(id, customerService.getCustomerById(id).getId());
        verify(customerRepository, times(1)).findById(anyInt());
    }

    @Test
    public void given_a_non_existing_id_findById_returns_an_error() {
        Integer id = 1;
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());
        Throwable exception =
                assertThrows(EntityNotFoundException.class,
                        () -> customerService.getCustomerById(id));
        assertEquals(exception.getMessage(), "The customer does not exist in our database");
    }

    @Test
    public void given_a_customer_in_db_addCustomer_returns_a_customer() {
        when(customerMapper.convertToEntity(any(CustomerDto.class))).thenReturn(createTestCustomer());
        when(customerMapper.convertToDto(any(Customer.class))).thenReturn(createTestCustomerDTO());
        when(customerRepository.save(any(Customer.class))).thenReturn(createTestCustomer());
        CustomerDto expectedCustomerDto = customerService.addNewCustomer(createTestCustomerDTO());
        assertNotNull(expectedCustomerDto);
        verify(customerRepository, times(1)).save(any());
    }

    @Test
    public void given_a_customerDto_updateCustomer() {
        Integer id = 2;
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(createTestCustomer()));
        when(customerMapper.convertToDto(any(Customer.class))).thenReturn(createTestCustomerDTO());
        when(customerRepository.save(any(Customer.class))).thenReturn(createTestCustomer());
        CustomerDto expectedCustomerDto = customerService.updateCustomer(createTestCustomerDTO());
        assertNotNull(expectedCustomerDto);
        assertEquals(id, expectedCustomerDto.getId());
        verify(customerRepository, times(1)).findById(anyInt());
        verify(customerRepository, times(1)).save(any());
    }

    @Test
    public void given_a_non_existing_id_updateCustomer_returns_an_error() {
        CustomerDto customerDto = testCustomerDtoTemplate(10,"Andrei", "Tigan", "andrei.tigan@accesa.eu", LocalDate.parse("1999-01-06"),
                "Oradea", LocalDate.parse("2021-04-03"), 300d);
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());
        Throwable exception =
                assertThrows(EntityNotFoundException.class,
                        () -> customerService.updateCustomer(customerDto));
        assertEquals(exception.getMessage(), "The customer does not exist in our database");
    }

    @Test
    public void given_an_existing_id_deleteCustomerById_delete_customer() {
        Integer id =2;
        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(createTestCustomer()));
        customerService.deleteCustomerById(id);
        verify(customerRepository, times(1)).findById(anyInt());
        verify(customerRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void given_a_non_existing_id_deleteCustomerById_returns_an_error() {
        Integer id = 10;
        when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());
        Throwable exception =
                assertThrows(EntityNotFoundException.class,
                        () -> customerService.deleteCustomerById(id));
        assertEquals(exception.getMessage(), "The customer does not exist in our database");
    }
}