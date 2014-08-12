package org.rgn.akka.ch03.futures.messages;

public class Order implements scala.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8099893299967970533L;
	Integer userId;
	Integer orderNo;
	Double amount;
	Integer noOfItems;

	public Order(Integer orderNo, Double amount, Integer noOfItems) {
		super();
		this.orderNo = orderNo;
		this.amount = amount;
		this.noOfItems = noOfItems;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", orderNo=" + orderNo + ", amount="
				+ amount + ", noOfItems=" + noOfItems + "]";
	}

}
