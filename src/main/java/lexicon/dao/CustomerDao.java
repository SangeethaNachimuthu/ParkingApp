package lexicon.dao;

import lexicon.model.Customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerDao {

    Customer persist(Customer customer);
    Optional<Customer> findById(Integer id);
    Collection<Customer> findAll();
    void remove(Integer id);
}
