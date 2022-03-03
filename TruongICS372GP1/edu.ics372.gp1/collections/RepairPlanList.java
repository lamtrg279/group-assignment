package collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.RepairPlan;

public class RepairPlanList implements Iterable<RepairPlan>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<RepairPlan> repairPlans = new ArrayList<>();
	private static RepairPlanList repairPlanList;

	private RepairPlanList() {
	}

	public static RepairPlanList getInstance() {
		if (repairPlanList == null) {
			repairPlanList = new RepairPlanList();
		}
		return repairPlanList;
	}

	public boolean addRepairPlan(RepairPlan repairPlan) {
		repairPlans.add(repairPlan);
		return true;
	}

	@Override
	public Iterator<RepairPlan> iterator() {
		return repairPlans.iterator();
	}
}
