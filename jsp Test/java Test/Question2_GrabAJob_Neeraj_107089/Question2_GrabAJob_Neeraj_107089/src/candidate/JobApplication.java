package candidate;

class JobApplication implements Comparable<JobApplication> {
	private String username;
	private int yearsOfExperience;
	private int currentSalary;
	private int excpectedSalary;

	public JobApplication(String username, int yearsOfExperience, int currentSalary, int excpectedSalary) {
		super();
		this.username = username;
		this.yearsOfExperience = yearsOfExperience;
		this.currentSalary = currentSalary;
		this.excpectedSalary = excpectedSalary;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public int getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(int currentSalary) {
		this.currentSalary = currentSalary;
	}

	public int getExcpectedSalary() {
		return excpectedSalary;
	}

	public void setExcpectedSalary(int excpectedSalary) {
		this.excpectedSalary = excpectedSalary;
	}

	@Override
	public String toString() {
		return "JobApplication [username=" + username + ", yearsOfExperience=" + yearsOfExperience + ", currentSalary="
				+ currentSalary + ", excpectedSalary=" + excpectedSalary + "]";
	}

	@Override
	public int compareTo(JobApplication o) {
		if (this.getYearsOfExperience() > o.getYearsOfExperience())
			return 1;
		else if (this.getYearsOfExperience() == o.getYearsOfExperience())
			return 0;
		else
			return -1;
	}

}
