package fr.treeptik.amazoneejb.exceptions;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAOException(String message, Throwable e) {
		super(message, e);
	}
	
}
