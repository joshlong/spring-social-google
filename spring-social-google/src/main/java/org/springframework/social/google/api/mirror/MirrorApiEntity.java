package org.springframework.social.google.api.mirror;

import org.springframework.social.google.api.ApiEntity;

/**
 *
 * base type for Google Glass Mirror entities.
 *
 * @author Josh Long
 *
 */
public class MirrorApiEntity extends ApiEntity {
	private String kind;

	protected MirrorApiEntity() {
		super();
	}

	protected MirrorApiEntity(String id) {
		super(id);
	}

	public MirrorApiEntity(String id, String kind) {
		super(id);
		this.kind = kind;
	}

	public String getKind() {
		return this.kind;
	}

}
