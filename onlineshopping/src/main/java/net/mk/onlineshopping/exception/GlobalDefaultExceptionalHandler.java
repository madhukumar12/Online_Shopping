package net.mk.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionalHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The page is not constructed");
		mv.addObject("errorDescription", "The page your are looking for is not avaible now");
		mv.addObject("title", "404 Error Page");
		
		
		return mv;
		
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product Not Availabe");
		mv.addObject("errorDescription", "The product  your are lookin is not avaible right now");
		mv.addObject("title", "Product unavialble");
		
		
		return mv;
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerExceptoion(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contact your Administrator!");
		
		// only for debugging application
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		
		mv.addObject("errorDescription", sw.toString());
		mv.addObject("title", "Error");
		
		
		return mv;
		
	}
}
