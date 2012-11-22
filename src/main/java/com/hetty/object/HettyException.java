package com.hetty.object;


/**
 * Wrapper for protocol exceptions thrown in the proxy.
 */
public class HettyException extends RuntimeException {
	
	private static final long serialVersionUID = -6857960002342511240L;
	private Throwable rootCause;

	/**
	 * Zero-arg constructor.
	 */
	public HettyException() {
	}

	/**
	 * Create the exception.
	 */
	public HettyException(String message) {
		super(message);
	}

	/**
	 * Create the exception.
	 */
	public HettyException(String message, Throwable rootCause) {
		super(message);

		this.rootCause = rootCause;
	}

	/**
	 * Create the exception.
	 */
	public HettyException(Throwable rootCause) {
		super(String.valueOf(rootCause));

		this.rootCause = rootCause;
	}

	/**
	 * Returns the underlying cause.
	 */
	public Throwable getRootCause() {
		return this.rootCause;
	}

	/**
	 * Returns the underlying cause.
	 */
	public Throwable getCause() {
		return getRootCause();
	}
}
