package candidate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import com.jdbcconnector.Connector;

public class ManageCandidate {
	Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int choice;
		ManageCandidate obj = new ManageCandidate();
		Connection con = Connector.getCon();
		while (true) {
			System.out.println("Select option \n1.Search candidate on primary skills\n"
					+ "2.Search candidate on primary skills and difference between exp vs curr salary in more then 30%\n"
					+ "3.Exit");
			choice = Integer.parseInt(in.nextLine());
			switch (choice) {
			case 1:
				obj.searchOnSkill(con);
				break;
			case 2:
				obj.searchOnSkillCondition(con);
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	public void searchOnSkill(Connection con) {
		String name;
		int choice;
		int exp, curSal, expSal;
		TreeSet<JobApplication> jobs = new TreeSet<>();
		System.out.println("Enter the skill");
		String skill = in.nextLine().trim().toLowerCase();
		String query = "select * from job_application where username in(select username from skills where skill='"
				+ skill + "')";
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			if (rs == null) {
				System.out.println("no records found");
				return;
			}
			while (rs.next()) {
				name = rs.getString(1);
				exp = rs.getInt(2);
				curSal = rs.getInt(3);
				expSal = rs.getInt(4);
				jobs.add(new JobApplication(name, exp, curSal, expSal));
			}
			while (true) {
				System.out
						.println("Select option sort on 1.Years of Experience \t 2.Sort on current salary \t 3.Exit ");
				choice = Integer.parseInt(in.nextLine());
				switch (choice) {
				case 1:
					// Collections.sort(jobs);
					for (JobApplication j : jobs) {
						System.out.println(j);
					}
					break;
				case 2:
					// Collections.sort(jobs, new SortOnCurSalary());
					TreeSet<JobApplication> jobs1 = new TreeSet<JobApplication>(new SortOnCurSalary());
					jobs1.addAll(jobs);
					System.out.println(jobs1);
					break;
				case 3:
					return;
				default:
					System.out.println("Invalid Option");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void searchOnSkillCondition(Connection con) {
		System.out.println("Enter the skill");
		String skill = in.nextLine().trim().toLowerCase();
		String query = "select * from job_application where abs((expectedSalary-currentSalary))>(((30*expectedsalary)/100)) and username in(select username from skills where skill='"
				+ skill + "')";
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			if (rs == null) {
				System.out.println("no records found");
				return;
			}
			while (rs.next()) {
				System.out.println("\nName              : " + rs.getString(1));
				System.out.println("Years of Experience : " + rs.getInt(2));
				System.out.println("Current Salary      : " + rs.getInt(3));
				System.out.println("Expected Salary     : " + rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
