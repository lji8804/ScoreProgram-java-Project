package kr.or.mrhi.six;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBController {

	public static int insertScoreTbl(Score score) {
		String insertQuery = "CALL insertScore(?,?,?,?,?)";
		int count = 0;
		try(Connection connection = DBUtility.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);) {
			preparedStatement.setString(1, score.getStudentNum());
			preparedStatement.setString(2, score.getStudentName());
			preparedStatement.setInt(3, score.getMidtermTest());
			preparedStatement.setInt(4, score.getFinalTest());
			preparedStatement.setInt(5, score.getAttendance());
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return count;
	}

	public static ArrayList<Score> readScoreTbl() {
		String selectQuery = "select * from scoretbl";
		ArrayList<Score> list = new ArrayList<Score>();
		list = executeSelectQuery(list,selectQuery);
		return list;
	}
	
	private static ArrayList<Score> executeSelectQuery(ArrayList<Score> list, String selectQuery) {
		try(Connection connection = DBUtility.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);	) {
				ResultSet resultSet = preparedStatement.executeQuery();
				if (!resultSet.isBeforeFirst()) {
					return list;
				}
				while (resultSet.next()) {
					String StudentNum = resultSet.getString(1);
					String StudentName = resultSet.getString(2);
					int midtermTest = resultSet.getInt(3);
					int finalTest = resultSet.getInt(4);
					int attendance = resultSet.getInt(5);
					int totalScore = resultSet.getInt(6);
					int average = resultSet.getInt(7);
					String grade = resultSet.getString(8);
					Score score = new Score(StudentNum, StudentName, midtermTest, finalTest, attendance, totalScore,
							average, grade);
					list.add(score);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		return list;
	}

	public static int deleteScoreTbl(String StudentNum) {
		String deleteQuery = "delete from scoretbl where student_num like ?";
		ResultSet resultSet = null;
		int count = 0;

		try(Connection connection = DBUtility.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);) {
			preparedStatement.setString(1, StudentNum);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return count;
	}

	public static boolean updateScoreTbl(String updateStudentNum, int updateMidterm, int updateFinal,
			int updateAttendance) {
		String updateQuery = "select updatetbl(?,?,?,?);";
		ResultSet resultSet = null;

		try(Connection connection = DBUtility.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);) {
			preparedStatement.setString(1, updateStudentNum);
			preparedStatement.setInt(2, updateMidterm);
			preparedStatement.setInt(3, updateFinal);
			preparedStatement.setInt(4, updateAttendance);
			ResultSet updateResultSet = preparedStatement.executeQuery();
			if (updateResultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public static ArrayList<Score> searchScoreTbl(int searchNumber, String searchStr) {
		String searchQuery = null;
		ArrayList<Score> searchList = new ArrayList<Score>();
		switch (searchNumber) {
		case 1:
			searchQuery = "select * from scoretbl where Student_num like ?";
			break;
		case 2:
			searchQuery = "select * from scoretbl where Student_name like ?";
			break;
		}
		try(Connection connection = DBUtility.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);) {
			preparedStatement.setString(1, searchStr);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				return searchList;
			}

			while (resultSet.next()) {
				String StudentNum = resultSet.getString(1);
				String StudentName = resultSet.getString(2);
				int midtermTest = resultSet.getInt(3);
				int finalTest = resultSet.getInt(4);
				int attendance = resultSet.getInt(5);
				int totalScore = resultSet.getInt(6);
				int average = resultSet.getInt(7);
				String grade = resultSet.getString(8);
				Score score = new Score(StudentNum, StudentName, midtermTest, finalTest, attendance, totalScore,
						average, grade);
				searchList.add(score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return searchList;
	}

	public static ArrayList<Score> sortScoreTbl(int sortNumber) {
		String sortQuery = null;
		ArrayList<Score> sortList = new ArrayList<Score>();
		switch (sortNumber) {
		case 1:
			sortQuery = "select * from scoretbl order by student_num";
			break;
		case 2:
			sortQuery = "select * from scoretbl order by student_name";
			break;
		case 3:
			sortQuery = "select * from scoretbl order by total_score desc";
			break;
		}
		try(Connection connection = DBUtility.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sortQuery);) {
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				return sortList;
			}

			while (resultSet.next()) {
				String StudentNum = resultSet.getString(1);
				String StudentName = resultSet.getString(2);
				int midtermTest = resultSet.getInt(3);
				int finalTest = resultSet.getInt(4);
				int attendance = resultSet.getInt(5);
				int totalScore = resultSet.getInt(6);
				int average = resultSet.getInt(7);
				String grade = resultSet.getString(8);
				Score score = new Score(StudentNum, StudentName, midtermTest, finalTest, attendance, totalScore,
						average, grade);
				sortList.add(score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sortList;
	}
}
