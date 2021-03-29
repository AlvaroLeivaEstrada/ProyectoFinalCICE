package com.leiva.util;

public final class Utilitie {
	
	public static int getAleatorio(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

}
