package com.example.poc.cron_job;

import com.example.poc.model.entity.Customer;
import com.example.poc.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
public class CustomerCronJob {

    private static final Logger log = LoggerFactory.getLogger(CustomerCronJob.class);
    private final CustomerRepository customerRepository;

    public CustomerCronJob(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Scheduled(cron = "0 0 12 1 1/6 ?")
    @Transactional
    public void giveBonus() {
        LocalDate date = LocalDate.now().minusDays(365);
        List<Customer> customers = customerRepository.findAllByHireDateLessThan(date);
        for (Customer customer : customers) {
            customer.setBonus(customer.getBonus() + 100d);
            log.info("The bonus is set {}", customer.getBonus());
        }
    }
}
