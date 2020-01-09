package com.message.pojo;

import java.util.List;

public class TextMessage {

	private String ErrorCode;
	private String ErrorMessage;
	private String JobId;
	private List<Data> MessageData;

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public String getJobId() {
		return JobId;
	}

	public void setJobId(String jobId) {
		JobId = jobId;
	}

	public List<Data> getMessageData() {
		return MessageData;
	}

	public void setMessageData(List<Data> messageData) {
		MessageData = messageData;
	}

}
