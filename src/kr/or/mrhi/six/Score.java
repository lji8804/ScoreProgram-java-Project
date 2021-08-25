package kr.or.mrhi.six;

import java.util.Objects;

public class Score {

	private String StudentNum;
	private String StudentName;
	private int midtermTest;
	private int finalTest;
	private int attendance;
	private int totalScore;
	private int average;
	private String grade;
	
	public Score(String studentNum, String studentName, int midtermTest, int finalTest, int attendance, int totalScore,
			int average, String grade) {
		StudentNum = studentNum;
		StudentName = studentName;
		this.midtermTest = midtermTest;
		this.finalTest = finalTest;
		this.attendance = attendance;
		this.totalScore = totalScore;
		this.average = average;
		this.grade = grade;
	}

	public Score(String studentNum, String studentName, int midtermTest, int finalTest, int attendance) {
		StudentNum = studentNum;
		StudentName = studentName;
		this.midtermTest = midtermTest;
		this.finalTest = finalTest;
		this.attendance = attendance;
	}
	
	public String getStudentNum() {
		return StudentNum;
	}
	public void setStudentNum(String studentNum) {
		StudentNum = studentNum;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public int getMidtermTest() {
		return midtermTest;
	}
	public void setMidtermTest(int midtermTest) {
		this.midtermTest = midtermTest;
	}
	public int getFinalTest() {
		return finalTest;
	}
	public void setFinalTest(int finalTest) {
		this.finalTest = finalTest;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public int getTotal_score() {
		return totalScore;
	}
	public void setTotal_score(int total_score) {
		this.totalScore = total_score;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Score) {
			Score score = (Score)obj;
			return this.getStudentNum() == score.getStudentNum();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getStudentNum());
	}

	@Override
	public String toString() {
		String midStr = String.format("%3d", midtermTest);
		String finalStr = String.format("%3d", finalTest);
		String totalStr = String.format("%3d", totalScore);	
		String attendanceStr = String.format("%3d", attendance);	
		String avgStr = String.format("%3d", average);
		return  StudentNum + "  " + StudentName + "  " +  midStr
				+ "  " + finalStr + "  " + attendanceStr + "  " + totalStr
				+ "  " + avgStr + "  " + grade;
	}
}
