package com.example.challengeJava;

import com.example.challengeJava.domain.*;
import com.example.challengeJava.exception.MarcaNoEncontradaException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ChallengeJavaApplication {

	private static final BigDecimal MAX = BigDecimal.valueOf(1000);
	public static final float AMEX_MULT = 0.1f;
	public static final float NARA_MULT = 0.5f;
	public static final float TASA_MINIMA = 0.3f;
	public static final float TASA_MAXIMA = 5;


	public static void main(String[] args) {
		SpringApplication.run(ChallengeJavaApplication.class, args);

		TarjetaCredito t1 = new TarjetaCredito(Marca.VISA,"1234123123123",new CardHolder("Pepe","Trueno"), LocalDate.now().minusYears(1));
		TarjetaCredito t2 = new TarjetaCredito(Marca.VISA,"1234123123123",new CardHolder("Pepe","Trueno"), LocalDate.now().minusYears(1));
		TarjetaCredito t3 = new TarjetaCredito(Marca.AMEX,"3234123123123",new CardHolder("Maria","Y"), LocalDate.now());

		imprimirInformacion(t1);
		imprimirInformacion(t2);
		imprimirInformacion(t3);

		Operacion op1 = new Operacion(new BigDecimal(1200));
		Operacion op2 = new Operacion(new BigDecimal(1000));
		Operacion op3 = new Operacion(new BigDecimal(200));

		validarOperacion(op1);
		validarOperacion(op2);
		validarOperacion(op3);

		validarTarjeta(t1);
		validarTarjeta(t2);
		validarTarjeta(t3);

		sonIguales(t1,t2);
		sonIguales(t2,t3);
		sonIguales(t3,t1);

		informarTasa(Marca.VISA, BigDecimal.valueOf(1200));

}
	private static void imprimirInformacion(TarjetaCredito tarjetaCredito){
		System.out.println(tarjetaCredito.toString());
	}
	private static void validarOperacion(Operacion operacion){
		System.out.printf("Operacion: %s", (operacion.getMonto().compareTo(MAX)==-1)?"Valida\n": "No valida\n");
	}
	private static void validarTarjeta(TarjetaCredito tarjetaCredito){
		System.out.printf("Tarjeta %s", (tarjetaCredito.getVencimiento().compareTo(LocalDate.now())>0)?"Valida\n": "No valida\n");
	}
	private static void sonIguales(TarjetaCredito t1, TarjetaCredito t2){
		System.out.printf("Las tarjetas son %s", t1.equals(t2)?"iguales\n":"distintas\n");
	}
	private static void informarTasa(Marca marca, BigDecimal importe){
		float tasa;
		switch (marca){
			case AMEX: tasa = (float) (LocalDate.now().getMonthValue() * AMEX_MULT); break;
			case NARA: tasa = (float) (LocalDate.now().getDayOfMonth() * NARA_MULT); break;
			case VISA: tasa = LocalDate.now().getYear()%100 / LocalDate.now().getMonthValue(); break;
			default: throw new MarcaNoEncontradaException(marca.name());
		}
		if (tasa < TASA_MINIMA){
			tasa = TASA_MINIMA;
		}else if (tasa > TASA_MAXIMA){
			tasa = TASA_MAXIMA;
		}
		System.out.printf("Tasa %.1f ",tasa);
	}
}


