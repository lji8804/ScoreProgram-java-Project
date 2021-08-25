package kr.or.mrhi.six;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DBView {
	public static final int INSERT = 1;
	public static final int SELECT = 2;
	public static final int SEARCH = 3;
	public static final int SORTDB = 4;
	public static final int UPDATE = 5;
	public static final int DELETE = 6;
	public static final int FINISH = 7;
	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		boolean flag = false;
		int selectNumber = 0;

		while (!flag) {
			selectNumber = displayMainMenu();
			switch (selectNumber) {
			case INSERT:
				scoreInsert();
				break;
			case SELECT:
				scoreSelect();
				break;
			case SEARCH:
				scoreSearch();
				break;
			case SORTDB:
				scoreSort();
				break;
			case UPDATE:
				scoreUpdate();
				break;
			case DELETE:
				scoreDelete();
				break;
			case FINISH:
				flag = true;
				break;
			default:
				System.out.println("�������� �ʴ� ��ȣ�Դϴ�");
			}
		} // end of while
		System.out.println("���α׷� ����");
	}

	private static void printData(ArrayList<Score> list) {
		if (list.size() == 0) {
			System.out.println("������ �����Ͱ� �����ϴ�");
			return;
		}
		for (Score tempScore : list) {
			System.out.println(tempScore);
		}
	}
	
	private static String checkStudentNum() {
		String StudentNum = null;
		while (true) {
			System.out.print("�й��� �Է��ϼ���(���� 8�ڸ�): ");
			StudentNum = scan.nextLine();

			if (!StudentNum.matches("^[0-9]{8}$")) {
				System.out.println("�й� ���Ŀ� �°� �Է��ϼ���");
				continue;
			}

			if (!duplicateCheck(StudentNum)) {
				return StudentNum;
			} else {
				System.out.println("�����Ͱ� �������� �ʽ��ϴ�");
			}
		} // end of while
	}
	
	private static void scoreDelete() {
		System.out.println("������ �й��� �Է��ϼ���");
		String deleteStudentNum = checkStudentNum();
		int deleteCount = DBController.deleteScoreTbl(deleteStudentNum);
		if (deleteCount != 0) {
			System.out.println("���� �Ϸ�");
		} else {
			System.out.println("���� ����");
		}
	}
	
	private static void scoreUpdate() {
		while (true) {
			System.out.println("������ �й��� �Է��ϼ���");
			String updateStudentNum = checkStudentNum();
			int updateMidterm = definemidtermTest();
			int updateFinal = definefinalTest();
			int updateAttendance = defineAttendance();
			boolean updateNotify = DBController.updateScoreTbl(updateStudentNum, updateMidterm, updateFinal,
					updateAttendance);
			if (updateNotify == true) {
				System.out.println("���� �Ϸ�");
			} else {
				System.out.println("���� ����");
			}
			break;
		}
	}

	private static void scoreSort() {
		boolean sortFlag = false;
		ArrayList<Score> sortList = new ArrayList<Score>();
		while (!sortFlag) {
			int sortNumber = displaySortMenu();
			if (sortNumber == 4) {
				sortFlag = true;
				break;
			} else {
				sortList = DBController.sortScoreTbl(sortNumber);
				printData(sortList);
			}
		}
	}

	private static void scoreSelect() {
		ArrayList<Score> list = DBController.readScoreTbl();
		printData(list);
	}

	private static void scoreInsert() {
		String StudentNum = defineStudentNum();
		String StudentName = defineStudentName();
		int midtermTest = definemidtermTest();
		int finalTest = definefinalTest();
		int attendance = defineAttendance();
		Score score = new Score(StudentNum, StudentName, midtermTest, finalTest, attendance);
		int count = DBController.insertScoreTbl(score);
		if (count == 1) {
			System.out.println("�Է� �Ϸ�");
		} else {
			System.out.println("�Է� ����");
		}
	}

	private static void scoreSearch() {
		String searchStr = null;
		ArrayList<Score> searchList = new ArrayList<Score>();
		boolean flag = false;
		while (!flag) {
			int searchNumber = displaySearchMenu();
			switch (searchNumber) {
			case 1:
				searchStr = checkStudentNum();
				break;
			case 2:
				searchStr = defineStudentName();
				break;
			case 3:
				return;
			}
			searchList = DBController.searchScoreTbl(searchNumber, searchStr);
			printData(searchList);
		}
	}

	private static int defineAttendance() {
		int attendance = 0;
		while (true) {
			System.out.print("�⼮ ������ �Է��ϼ���: ");
			try {
				attendance = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("�Է� ���Ŀ� �°� �Է��ϼ���");
				continue;
			}
			if (attendance > 10 || attendance < 0) {
				System.out.println("���� ���� �����Դϴ� �ٽ� �Է��ϼ���");
				continue;
			}
			break;
		}
		return attendance;
	}

	private static int definefinalTest() {
		int finalTest = 0;
		while (true) {
			System.out.print("�⸻��� ������ �Է��ϼ���: ");
			try {
				finalTest = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("�Է� ���Ŀ� �°� �Է��ϼ���");
				continue;
			}
			if (finalTest > 100 || finalTest < 0) {
				System.out.println("���� ���� �����Դϴ� �ٽ� �Է��ϼ���");
				continue;
			}
			break;
		}
		return finalTest;
	}

	private static int definemidtermTest() {
		int midtermTest = 0;
		while (true) {
			System.out.print("�߰���� ������ �Է��ϼ���: ");
			try {
				midtermTest = Integer.parseInt(scan.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("�Է� ���Ŀ� �°� �Է��ϼ���");
				continue;
			}
			if (midtermTest > 100 || midtermTest < 0) {
				System.out.println("���� ���� �����Դϴ� �ٽ� �Է��ϼ���");
				continue;
			}
			break;
		}
		return midtermTest;
	}

	private static String defineStudentName() {
		String StudentName = null;
		while (true) {
			System.out.print("�̸��� �Է��ϼ���: ");
			StudentName = scan.nextLine();
			if (!StudentName.matches("^[\\D]{2,5}$")) {
				System.out.println("�̸� ���Ŀ� �°� �Է��ϼ���");
				continue;
			} else {
				break;
			}
		}
		return StudentName;
	}

	private static String defineStudentNum() {
		String StudentNum = null;
		while (true) {
			System.out.print("�й��� �Է��ϼ���: ");
			StudentNum = scan.nextLine();

			if (!StudentNum.matches("^[0-9]{8}$")) {
				System.out.println("�й� ���Ŀ� �°� �Է��ϼ���");
				continue;
			}

			if (duplicateCheck(StudentNum)) {
				break;
			} else {
				System.out.println("�ߺ����Դϴ�");
			}
		} // end of while
		return StudentNum;
	}

	private static boolean duplicateCheck(String StudentNum) {
		ArrayList<Score> list = DBController.searchScoreTbl(1 , StudentNum);
		if(list == null) {
			return true;
		}else {
			return false;
		}
	}

	private static int displayMainMenu() {
		String mainStr = null;
		int mainNumber = 0;
		while (true) {
			System.out.println("==================================================");
			System.out.println("=1. ���� 2. ��� 3. �˻� 4. ���� 5. ���� 6. ���� 7. ����=");
			System.out.println("==================================================");
			System.out.print("��ȣ���� >>  ");
			mainStr = enterNumber();
			mainNumber = checkFormat(mainStr, 7);
			if(mainNumber == 0) continue;
			return mainNumber;
		}
	}
	
	private static int displaySearchMenu() {
		String selectStr = null;
		int selectNumber = 0;
		while (true) {
			System.out.println("==================================================");
			System.out.println("======== 1. �й� �˻� 2. �̸� �˻� 3. ���θ޴��� =========");
			System.out.println("==================================================");
			System.out.print("��ȣ���� >>  ");
			selectStr = enterNumber();
			selectNumber = checkFormat(selectStr, 3);
			if(selectNumber == 0) continue;
			return selectNumber;
		}
	}
	
	private static int displaySortMenu() {
		String sortStr = null;
		int sortNumber = 0;
		while (true) {
			System.out.println("==============================================================");
			System.out.println("======== 1. �й� ���� 2. �̸� ���� 3. ������ ���� 4. ���θ޴���========");
			System.out.println("==============================================================");
			System.out.print("��ȣ���� >>  ");
			sortStr = enterNumber();
			sortNumber = checkFormat(sortStr, 4);
			if(sortNumber == 0) continue;
			return sortNumber;
		}
	}
	
	private static String enterNumber() {
		String enterStr = null;
		enterStr = scan.nextLine();
		return enterStr;
	}

	private static int checkFormat(String inputNumberStr, int lastIndex) {
		int selectNumber = 0;
		if(inputNumberStr.matches("[1-" + lastIndex + "]")) {
			selectNumber = Integer.parseInt(inputNumberStr);
			return selectNumber;
		} else {
			System.out.println("�Է°��� �߸� �Ǿ����ϴ�. �ٽ� �Է��ϼ���");
			return 0;
		}
	}
}


