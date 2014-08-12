package org.rgn.akka.ch03.futures.messages;

public class Address {
	Integer userId;
	String fullName;
	String address1;
	String address2;

	public Address(Integer userId, String fullName, String address1,
			String address2) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.address1 = address1;
		this.address2 = address2;
	}

	@Override
	public String toString() {
		return "Address [userId=" + userId + ", fullName=" + fullName
				+ ", address1=" + address1 + ", address2=" + address2 + "]";
	}

}
