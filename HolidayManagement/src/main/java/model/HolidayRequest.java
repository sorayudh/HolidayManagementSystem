package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the holiday_request database table.
 * 
 */
@Entity
@Table(name="holiday_request")
@NamedQuery(name="HolidayRequest.findAll", query="SELECT h FROM HolidayRequest h")
public class HolidayRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="holiday_request_id")
	private int holidayRequestId;

	@Column(name="breaking_constraints")
	private byte breakingConstraints;

	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;

	@Column(name="no_constraint_time")
	private byte noConstraintTime;

	private int priority;

	private String reason;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="request_time")
	private Date requestTime;

	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date toDate;

	@Column(name="total_days")
	private int totalDays;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	//bi-directional many-to-one association to RequestStatus
	@ManyToOne
	@JoinColumn(name="request_status_id")
	private RequestStatus requestStatus;

	//bi-directional many-to-one association to RequestWithConstraint
	@OneToMany(mappedBy="holidayRequest")
	private List<RequestWithConstraint> requestWithConstraints;

	public HolidayRequest() {
	}

	public int getHolidayRequestId() {
		return this.holidayRequestId;
	}

	public void setHolidayRequestId(int holidayRequestId) {
		this.holidayRequestId = holidayRequestId;
	}

	public byte getBreakingConstraints() {
		return this.breakingConstraints;
	}

	public void setBreakingConstraints(byte breakingConstraints) {
		this.breakingConstraints = breakingConstraints;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public byte getNoConstraintTime() {
		return this.noConstraintTime;
	}

	public void setNoConstraintTime(byte noConstraintTime) {
		this.noConstraintTime = noConstraintTime;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getTotalDays() {
		return this.totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public RequestStatus getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public List<RequestWithConstraint> getRequestWithConstraints() {
		return this.requestWithConstraints;
	}

	public void setRequestWithConstraints(List<RequestWithConstraint> requestWithConstraints) {
		this.requestWithConstraints = requestWithConstraints;
	}

	public RequestWithConstraint addRequestWithConstraint(RequestWithConstraint requestWithConstraint) {
		getRequestWithConstraints().add(requestWithConstraint);
		requestWithConstraint.setHolidayRequest(this);

		return requestWithConstraint;
	}

	public RequestWithConstraint removeRequestWithConstraint(RequestWithConstraint requestWithConstraint) {
		getRequestWithConstraints().remove(requestWithConstraint);
		requestWithConstraint.setHolidayRequest(null);

		return requestWithConstraint;
	}

}