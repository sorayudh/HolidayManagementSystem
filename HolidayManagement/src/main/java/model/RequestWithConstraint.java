package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the request_with_constraint database table.
 * 
 */
@Entity
@Table(name="request_with_constraint")
@NamedQuery(name="RequestWithConstraint.findAll", query="SELECT r FROM RequestWithConstraint r")
public class RequestWithConstraint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_with_constraint_id")
	private int requestWithConstraintId;

	//bi-directional many-to-one association to Constraint
	@ManyToOne
	@JoinColumn(name="contraint_id")
	private Constraint constraint;

	//bi-directional many-to-one association to HolidayRequest
	@ManyToOne
	@JoinColumn(name="holiday_request_id")
	private HolidayRequest holidayRequest;

	public RequestWithConstraint() {
	}

	public int getRequestWithConstraintId() {
		return this.requestWithConstraintId;
	}

	public void setRequestWithConstraintId(int requestWithConstraintId) {
		this.requestWithConstraintId = requestWithConstraintId;
	}

	public Constraint getConstraint() {
		return this.constraint;
	}

	public void setConstraint(Constraint constraint) {
		this.constraint = constraint;
	}

	public HolidayRequest getHolidayRequest() {
		return this.holidayRequest;
	}

	public void setHolidayRequest(HolidayRequest holidayRequest) {
		this.holidayRequest = holidayRequest;
	}

}