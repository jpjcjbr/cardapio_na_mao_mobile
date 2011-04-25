package br.com.jplr.exception;

public class UsuarioIncorretoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioIncorretoException()
	{
		
	}
	
	public UsuarioIncorretoException(Exception e)
	{
		super(e);
	}

}
