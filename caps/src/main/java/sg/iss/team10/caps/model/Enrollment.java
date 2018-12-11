package sg.iss.team10.caps.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;


/**
 * The persistent class for the enrollment database table.
 * 
 */
@Entity
@NamedQuery(name="Enrollment.findAll", query="SELECT e FROM Enrollment e")
public class Enrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int enrollmentId;

	private int courseId;

	private float score;

	private int studentId;
	@ManyToOne
	@JoinColumn(name= "courseId")
	private Course course;
		
	@ManyToOne
	@JoinColumn(name= "studentId")
	private Student student;
	public Enrollment() {
	}

	public int getEnrollmentId() {
		return this.enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
