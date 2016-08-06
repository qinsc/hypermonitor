/**
 * 
 */
package hyper.momitor.model;

/**
 * @author qinscx
 *
 */
public class HostGroup {
	private String groupId;
	private String groupName;
	private String groupDesc;

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the groupDesc
	 */
	public String getGroupDesc() {
		return groupDesc;
	}

	/**
	 * @param groupDesc
	 *            the groupDesc to set
	 */
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HostGroup [" + (groupId != null ? "groupId=" + groupId + ", " : "")
				+ (groupName != null ? "groupName=" + groupName + ", " : "")
				+ (groupDesc != null ? "groupDesc=" + groupDesc : "") + "]";
	}

}
