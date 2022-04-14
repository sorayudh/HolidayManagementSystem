package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the constraints database table.
 * 
 */
@Entity
@Table(name="constraints")
@NamedQuery(name="Constraint.findAll", query="SELECT c FROM Constraint c")
public class Constraint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="constraint_id")
	private int constraintId;

	private String constraint;

	//bi-directional many-to-one association to RequestWithConstraint
	@OneToMany(mappedBy="constraint")
	private List<RequestWithConstraint> requestWithConstraints;

	public Constraint() {
	}

	public int getConstraintId() {
		return this.constraintId;
	}

	public void setConstraintId(int constraintId) {
		this.constraintId = constraintId;
	}

	public String getConstraint() {
		return this.constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public List<RequestWithConstraint> getRequestWithConstraints() {
		return this.requestWithConstraints;
	}

	public void setRequestWithConstraints(List<RequestWithConstraint> requestWithConstraints) {
		this.requestWithConstraints = requestWithConstraints;
	}

	public RequestWithConstraint addRequestWithConstraint(RequestWithConstraint requestWithConstraint) {
		getRequestWithConstraints().add(requestWithConstraint);
		requestWithConstraint.setConstraint(this);

		return requestWithConstraint;
	}

	public RequestWithConstraint removeRequestWithConstraint(RequestWithConstraint requestWithConstraint) {
		getRequestWithConstraints().remove(requestWithConstraint);
		requestWithConstraint.setConstraint(null);

		return requestWithConstraint;
	}

}