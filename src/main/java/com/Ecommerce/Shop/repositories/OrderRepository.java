package  com.Ecommerce.Shop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;



import com.Ecommerce.Shop.entities.Order;
import com.Ecommerce.Shop.entities.User;




import java.util.List;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByUser(User user);
}