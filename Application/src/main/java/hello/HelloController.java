package hello;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class HelloController {
    
    @GetMapping("/addition/values")
    @ResponseBody
    public static String addition(@RequestParam List<Double> numbers) {
// loglar ekle
    	Double result = BigDecimal.ZERO.doubleValue();
    	
    	for(Double i : numbers) {
    		result = result + i;
    	}
    	
    	return result.toString();
    	
    }
    
    @GetMapping("/substraction/values")
    @ResponseBody
    public static String substraction(@RequestParam List<Double> numbers) {
    	
    	Double result = numbers.get(0);
    	
    	for(Double i : numbers) {
    		result = result - i;
    	}
    	
    	result = result + numbers.get(0);
        	
    	return result.toString();
    	
    }
    
    @GetMapping("/multiplication/values")
    @ResponseBody
    public static String multiplication(@RequestParam List<Double> numbers) {
    	
    	Double result = BigDecimal.ONE.doubleValue();
    	
    	for(Double i : numbers) {
    		result = result * i;
    	}
    	
    	return result.toString();
    	
    }

    @GetMapping("/division/values")
    @ResponseBody
    public static String division(@RequestParam List<Double> numbers) {
    	
    	Double result = numbers.get(0);
    	numbers = numbers.subList(1, numbers.size());
    	
    	for(Double i : numbers) {
    		result = result / i;
    	}
    	
    	return result.toString();
    	
    }

}