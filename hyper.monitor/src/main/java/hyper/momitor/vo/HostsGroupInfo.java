package hyper.momitor.vo;

import java.util.List;

public class HostsGroupInfo {
	private List<String> hostIds;
	private String groupId;

	/**
	 * @return Returns the hostIds.
	 */
	public List<String> getHostIds() {
		return hostIds;
	}

	/**
	 * @param hostIds
	 *            The hostIds to set.
	 */
	public void setHostIds(List<String> hostIds) {
		this.hostIds = hostIds;
	}

	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}