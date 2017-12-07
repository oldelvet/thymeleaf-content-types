package uk.org.oldelvet.examples.thymeleaf1;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {

    /** Simple text page. */
    @RequestMapping("/")
    public String home() {
        return "This is home";
    }

    /** Simple text/html thymeleaf mapping. */
    @RequestMapping("/simple")
    public String simple() {
        return "simple";
    }

    /** Simple text/html thymeleaf mapping via model and view. */
    @RequestMapping("/simple2")
    public ModelAndView simple2() {
        return new ModelAndView("simple");
    }

    /** Simple json response body. */
    @RequestMapping("/simple3.json")
    @ResponseBody
    public SomeBody simple3() {
        return new SomeBody("abc");
    }

    /** Not found exception when requesting a response body. */
    @RequestMapping("/simple4")
    @ResponseBody
    public SomeBody simple4() {
        throw new NotFoundException(false);
    }

    /** Not found exception when requesting a response body with html extension. */
    @RequestMapping("/simple5.html")
    @ResponseBody
    public SomeBody simple5() {
        throw new NotFoundException(false);
    }

    /**
     * Various responses including found, not found with thymeleaf html response and
     * not found with thymeleaf json response via ControllerAdvice annotated error handlers.
     */
    @RequestMapping("/{epath}/simple6.json")
    @ResponseBody
    public SomeBody simple6(@PathVariable String epath) {
        if ("404html".equals(epath)) {
            throw new NotFoundException(false);
        } else if ("404htmlcs".equals(epath)) {
            throw new NotFoundException(true);
        } else if ("404json".equals(epath)) {
            throw new NotFoundException(false);
        } else if ("404jsoncs".equals(epath)) {
            throw new NotFoundException(true);
        }
        return new SomeBody("abc");
    }

    /** Simple JSON mapping using thymeleaf template. */
    @RequestMapping("/simple7.json")
    public String simple7() {
        return "errorjson.json";
    }

    /** Similar to testcase 6 but without the .json extension. */
    @RequestMapping("/{epath}/simple8")
    @ResponseBody
    public SomeBody simple8(@PathVariable String epath) {
        if ("404html".equals(epath)) {
            throw new NotFoundException(false);
        } else if ("404htmlcs".equals(epath)) {
            throw new NotFoundException(true);
        } else if ("404json".equals(epath)) {
            throw new NotFoundException(false);
        } else if ("404jsoncs".equals(epath)) {
            throw new NotFoundException(true);
        }
        return new SomeBody("abc");
    }

    public static class SomeBody {
        private String somevalue;
        
        public SomeBody(String sv) {
            this.somevalue = sv;
        }

        /**
         * @return the somevalue
         */
        public String getSomevalue() {
            return somevalue;
        }

        /**
         * @param somevalue the somevalue to set
         */
        public void setSomevalue(String somevalue) {
            this.somevalue = somevalue;
        }
    }
}
