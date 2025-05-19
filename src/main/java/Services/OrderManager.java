package main.java.Services;

import main.java.entities.Order;
import main.java.entities.orderStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderManager {
    //list of orders
    //creates, close, fetches orders
        Map<Integer, Order> orderStore = new HashMap<>();
        int orderIdSequence = 1;

        public Order createOrder(int userId, int bookId){
            Order order = new Order(orderIdSequence, userId, bookId, orderStatus.BORROWED, LocalDate.now(), LocalDate.now().plusDays(30));
            orderStore.put(orderIdSequence, order);
            orderIdSequence+=1;
            System.out.println("New order added successfully!");
            return order;
        }

        public Order closeOrder(int orderId){
            Order order = orderStore.get(orderId);
            if(order == null || order.getStatus()!=orderStatus.BORROWED){
                System.out.println("No order found");
                return null;
            }
            order.setStatus(orderStatus.SUBMITTED);
            System.out.println("order details updated successfully!");
            return order;
        }

        public void fetchAllOrders(){
            orderStore.values().forEach(System.out::println);
        }

        public Set<Order> fetchUserSpecificOrders(int userId){
            return orderStore.values().stream()
                    .filter(order -> order.getUserId()==userId)
                    .collect(Collectors.toSet());
        }
}
