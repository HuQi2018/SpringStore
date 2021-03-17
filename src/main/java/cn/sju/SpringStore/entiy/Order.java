package cn.sju.SpringStore.entiy;

import java.util.Date;

public class Order extends BaseEntity{
	private Integer id;//订单id
	private String orderNo;//订单号
	private Integer userId;//用户id
	private Long payment;//实际支付金额
	private Integer paymentType;//支付类型 0：支付宝、1：微信、2：网银、3：货到付款
	private Long postage;//运费
	private String name;//收件人
	private String address;//具体地址
	private String phone;//联系电话
	//订单状态，0：取消、10：未付款、20：已付款、30：已发货、40：交易成功、50：交易关闭
	private String status;
	private Date paymentTime;//支付时间
	private Date sendTime;//发货时间
	private Date endTime;//交易完成时间
	private Date closeTime;//交易关闭时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getPayment() {
		return payment;
	}
	public void setPayment(Long payment) {
		this.payment = payment;
	}
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	public Long getPostage() {
		return postage;
	}
	public void setPostage(Long postage) {
		this.postage = postage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNo=" + orderNo + ", userId=" + userId + ", payment=" + payment
				+ ", paymentType=" + paymentType + ", postage=" + postage + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", status=" + status + ", paymentTime=" + paymentTime + ", sendTime=" + sendTime
				+ ", endTime=" + endTime + ", closeTime=" + closeTime + "]";
	}
	
	
}
