package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the request_status database table.
 * 
 */
@Entity
@Table(name="request_status")
@NamedQuery(name="RequestStatus.findAll", query="SELECT r FROM RequestStatus r")
public class RequestStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_status_id")
	private int requestStatusId;

	private String status;

	//bi-directional many-to-one association to HolidayRequest
	@OneToMany(mappedBy="requestStatus")
	private List<HolidayRequest> holidayRequests;

	public RequestStatus() {
	}

	public int getRequestStatusId() {
		return this.requestStatusId;
	}

	public void setRequestStatusId(int requestStatusId) {
		this.requestStatusId = requestStatusId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<HolidayRequest> getHolidayRequests() {
		return this.holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
		this.holidayRequests = holidayRequests;
	}

	public HolidayRequest addHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().add(holidayRequest);
		holidayRequest.setRequestStatus(this);

		return holidayRequest;
	}

	public HolidayRequest removeHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().remove(holidayRequest);
		holidayRequest.setRequestStatus(null);

		return holidayRequest;
	}

}