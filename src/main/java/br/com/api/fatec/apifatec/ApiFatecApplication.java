package br.com.api.fatec.apifatec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.websocket.server.PathParam;

@RestController
@SpringBootApplication
public class ApiFatecApplication {
	
	@RequestMapping("/")
	String home() {
		return "hello world";
	}
	
	@RequestMapping("/hello")
	String home2() {
		return "hello world 2";
	}
	
	@RequestMapping("/numero")
	Integer numero() {
		return 2;
	}
	
	@RequestMapping("/numero/{num}")
	Integer numero2(@PathVariable Integer num) {
		return num;
	}
	
	@RequestMapping(value = "/idade-com-tipo-string/{paramIdade}", method = RequestMethod.GET)
	String getIdadeComParametro(@PathVariable String paramIdade) {
		try {
			int idade = Integer.parseInt(paramIdade);
			
			if (idade < 0) {
				throw new NumberFormatException();
			}
			

            if (idade < 12) {
                return "Crianca";
            } else if (idade <= 18) {
                return "Adolescente";
            } else if (idade <= 60) {
                return "Adulto";
            } else {
                return "Idoso";
            }
			
		} catch (NumberFormatException e) {
			return "idade invalida";
		}
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/idade-com-parametro-tipo-integer/{paramIdade}", method = RequestMethod.GET)
    String getIdadeComParametroTipoInteger(@PathVariable Integer paramIdade) {
        try {
            // Tenta converter a string para um BigInteger
            int idade = paramIdade;
            if (idade < 0){
                throw new NumberFormatException();
            }

            if (idade < 12) {
                return "Crianca";
            } else if (idade <= 18) {
                return "Adolescente";
            } else if (idade <= 60) {
                return "Adulto";
            } else {
                return "Idoso";
            }
        } catch (NumberFormatException e) {
            // Se a conversão falhar, significa que a string não é um número válido
            return "idade invalida";
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return "idade invalida";
    }
    
	public static void main(String[] args) {
		SpringApplication.run(ApiFatecApplication.class, args);
	}

}
