package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private int orderId;
    private int userId;
    private int bookId;
    private orderStatus status;
    private LocalDate issuedDate;
    private LocalDate dueDate;

    public Order(int id, int userId, int bookId, orderStatus status, LocalDate issuedDate, LocalDate dueDate) {
        this.orderId = id;
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
        this.issuedDate = issuedDate;
        this.dueDate = dueDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public orderStatus getStatus() {
        return status;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", borrowDate=" + issuedDate +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
