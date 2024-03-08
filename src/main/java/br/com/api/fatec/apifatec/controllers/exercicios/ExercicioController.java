package br.com.api.fatec.apifatec.controllers.exercicios;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {
	
	@RequestMapping("/teste")
	String home() {
		return "hello world";
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
	
}
