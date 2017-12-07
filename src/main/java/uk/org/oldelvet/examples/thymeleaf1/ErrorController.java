package uk.org.oldelvet.examples.thymeleaf1;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(NotFoundException e,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("errorpage");
        if (e.isForceCharset()) {
            // Not sure if this is a sensible idea but just testing to see if
            // it has an effect
            response.setContentType("text/html;charset=ISO-8859-1");
        }
        return mav;
    }

    @ExceptionHandler({JsonException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView handleJsonException(JsonException e,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("errorjson.json");
        if (e.isForceCharset()) {
            // Not sure if this is a sensible idea but just testing to see if
            // it has an effect
            response.setContentType("application/json;charset=ISO-8859-1");
        }
        return mav;
    }
}
