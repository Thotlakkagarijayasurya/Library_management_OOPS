package Services;

import entities.Order;
import entities.orderStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderManager {
    //list of orders
    //borrow or return orders
        Map<Integer, Order> orderStore = new HashMap<>();
        int orderIdSequence = 1;

        public Order borroworder(int userId, int orderId){
            Order order = new Order(orderIdSequence++, userId, orderId, orderStatus.BORROWED, LocalDate.now(), LocalDate.now().plusDays(30));
            orderStore.put(orderIdSequence -1, order);
            System.out.println("New order added successfully!");
            return order;
        }

        public Order updateOrder(int orderId, orderStatus status){
            Order order = orderStore.get(orderId);
            if(status!=null) {
                order.setStatus(status);
            }
            System.out.println("order details updated successfully!");
            return order;
        }

        public Set<Order> fetchAllorders(){
            return (Set<Order>) orderStore.values();
        }
}
