package lk.ijse.repository;

import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
