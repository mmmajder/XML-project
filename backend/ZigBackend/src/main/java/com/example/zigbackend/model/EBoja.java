package com.example.zigbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EBoja {
	BELA,
	CRNA,
	PLAVA,
	ZELENA,
	CRVENA,
	LJUBICASTA,
	ROZE,
	ZUTA,
	BRAON,
	KREM,
	NARANDZASTA,
	SIVA;

	@JsonCreator
	public static EBoja forValue(String value) {
		System.out.println("eee");
		return EBoja.valueOf(value);
	}
}
