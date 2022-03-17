package collections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entities.RepairPlan;

/**
 * Maintains a list of RepairPlan objects. It is used by both Customer and
 * Appliance
 */
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

	/**
	 * Add a RepairPlan object to the list
	 * 
	 * @param repairPlan the RepairPlan object
	 * @return true
	 */
	public boolean addRepairPlan(RepairPlan repairPlan) {
		repairPlans.add(repairPlan);
		return true;
	}

	/**
	 * Remove a RepairPlan object from the list
	 * 
	 * @param repairPlan the RepairPlan object
	 * @return true
	 */
	public boolean removeRepairPlan(RepairPlan repairPlan) {
		for (int i = 0; i < repairPlans.size(); i++) {
			if (repairPlans.get(i).getAppliance().getId().equals(repairPlan.getAppliance().getId())
					&& repairPlans.get(i).getCustomer().getId().equals(repairPlan.getCustomer().getId())) {
				repairPlans.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<RepairPlan> iterator() {
		return repairPlans.iterator();
	}

	@Override
	public String toString() {
		return repairPlans.toString();
	}

}
