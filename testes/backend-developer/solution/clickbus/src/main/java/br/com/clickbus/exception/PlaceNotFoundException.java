package br.com.clickbus.exception;

public class PlaceNotFoundException extends Exception{
    public PlaceNotFoundException(String error) {
        super(error);
    }
}
