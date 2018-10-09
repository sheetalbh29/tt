package candidate;

import java.util.Comparator;

class SortOnCurSalary implements Comparator<JobApplication> {

	@Override
	public int compare(JobApplication o1, JobApplication o2) {
		if (o1.getCurrentSalary() == o2.getCurrentSalary())
			return 0;
		else if (o1.getCurrentSalary() > o2.getCurrentSalary())
			return 1;
		else
			return -1;
	}

}
