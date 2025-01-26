package org.unibl.etf.mdp.workwithorders.model;

public class OrderFF {

	private Order order;
	private OrderStatus status;
	public OrderFF() {
		super();
	}
	public OrderFF(Order order, OrderStatus status) {
		super();
		this.order = order;
		this.status = status;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
	
}
