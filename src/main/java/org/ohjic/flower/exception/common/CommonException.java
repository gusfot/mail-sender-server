package org.ohjic.flower.exception.common;

public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ResponseCode responseCode = ResponseCode.UNKOWN;

	public CommonException(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}
}
