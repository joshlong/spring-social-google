package org.springframework.social.google.api.mirror;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Models <A href="https://developers.google.com/glass/v1/reference/timeline/attachments">timeline attachments</A>. The
 * attachment resource is: <code>https://www.googleapis.com/mirror/v1/timeline/{itemId}/attachments/{attachmentId}.</code>.
 * Resources look like this:
 * <pre>
 * {@code
 *    {
 *                      "id": string,
 *             "contentType": string,
 *              "contentUrl": string,
 *     "isProcessingContent": boolean
 *    }
 * }
 * </pre>
 * <p/>
 * TODO this should probably be a subtype of {@link TimelineItem}.
 *
 * @author Josh Long
 * @since 1.1.0
 */
@JsonIgnoreProperties (ignoreUnknown=true)
public class Attachment extends MirrorApiEntity {
	private String contentType, contentUrl;
	private boolean processingContent;

	protected Attachment() {
		super();
	}


	protected Attachment(String id, String contentUrl, String contentType, boolean processingContent) {
		super(id);
		this.contentType = contentType;
		this.contentUrl = contentUrl;
		this.processingContent = processingContent;
	}

	public String getContentType() {
		return contentType;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public boolean isProcessingContent() {
		return processingContent;
	}

}
