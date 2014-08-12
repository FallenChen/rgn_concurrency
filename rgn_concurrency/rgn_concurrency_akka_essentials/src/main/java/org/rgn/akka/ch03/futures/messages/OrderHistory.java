package org.rgn.akka.ch03.futures.messages;

public class OrderHistory {

	Address address;
	Order order;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderHistory(Address address, Order order) {
		super();
		this.address = address;
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderHistory [address=" + address + ", order=" + order + "]";
	}

}
