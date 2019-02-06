package burger.mvc.vo;

import java.io.Serializable;
import java.sql.Date;

public class Burger implements Serializable{

	private static final long serialVersionUID = 4576318866611729491L;
	
	private int menuNo;
	private String menuName;
	private int price;
	private int orderNo;
	private int orderQty;
	private String customerName;
	private String phone;
	private Date orderDate;
	private char orderShop;
	private double point;
	private int stockNo;
	private String stockName;
	private int stockQty;
	
	public Burger() {}
	public Burger(int menuNo, String menuName) {
		this.menuNo = menuNo;
		this.menuName = menuName;
	}
	public Burger(int menuNo, String menuName, int price, int orderNo, int orderQty, String customerName, String phone,
			Date orderDate, char orderShop, double point, int stockNo, String stockName, int stockQty) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.price = price;
		this.orderNo = orderNo;
		this.orderQty = orderQty;
		this.customerName = customerName;
		this.phone = phone;
		this.orderDate = orderDate;
		this.orderShop = orderShop;
		this.point = point;
		this.stockNo = stockNo;
		this.stockName = stockName;
		this.stockQty = stockQty;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public char getOrderShop() {
		return orderShop;
	}

	public void setOrderShop(char orderShop) {
		this.orderShop = orderShop;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public int getStockNo() {
		return stockNo;
	}

	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return menuNo + ", " + menuName + ", " + price + ", " + orderNo + ", " + orderQty + ", " + customerName + ", "
				+ phone + ", " + orderDate + ", " + orderShop + ", " + point + ", " + stockNo + ", " + stockName + ", "
				+ stockQty;
	}
	
	
	
}
