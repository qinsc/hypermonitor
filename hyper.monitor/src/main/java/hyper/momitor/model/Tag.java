/**
 * 
 */
package hyper.momitor.model;

/**
 * @author qinscx
 *
 */
public class Tag {
	private String tagId;
	private String tagKey;
	private String tagDesc;

	private String tagValue;
	private String ownerId;
	private String relationId;

	/**
	 * @return the tagId
	 */
	public String getTagId() {
		return tagId;
	}

	/**
	 * @param tagId
	 *            the tagId to set
	 */
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	/**
	 * @return the tagKey
	 */
	public String getTagKey() {
		return tagKey;
	}

	/**
	 * @param tagKey
	 *            the tagKey to set
	 */
	public void setTagKey(String tagKey) {
		this.tagKey = tagKey;
	}

	/**
	 * @return the tagDesc
	 */
	public String getTagDesc() {
		return tagDesc;
	}

	/**
	 * @param tagDesc
	 *            the tagDesc to set
	 */
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	/**
	 * @return the tagValue
	 */
	public String getTagValue() {
		return tagValue;
	}

	/**
	 * @param tagValue
	 *            the tagValue to set
	 */
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the relationId
	 */
	public String getRelationId() {
		return relationId;
	}

	/**
	 * @param relationId
	 *            the relationId to set
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tag [" + (tagId != null ? "tagId=" + tagId + ", " : "")
				+ (tagKey != null ? "tagKey=" + tagKey + ", " : "")
				+ (tagDesc != null ? "tagDesc=" + tagDesc + ", " : "")
				+ (tagValue != null ? "tagValue=" + tagValue + ", " : "")
				+ (ownerId != null ? "ownerId=" + ownerId + ", " : "")
				+ (relationId != null ? "relationId=" + relationId : "") + "]";
	}

}
