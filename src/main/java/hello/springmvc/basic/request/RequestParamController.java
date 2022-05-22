package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");

    }

    // RestController가 아닌 그냥 Controller를 사용할 경우 return 값이 String이면 view 리졸빙됨
    // 그냥 문자 자체를 반환하고 싶다면 ResponseBody를 사용해주어야 함
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            // required 속성 값이 true일 경우 해당 값은 반드시 들어와야 함
            // 디폴트 설정이 true
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        // 기본형인 int에는 null 값이 들어갈 수 없음 0으로 초기화라도 해주어야함
        // 하지만 파라미터에 값이 안들어 올 경우 null 값이 들어가야 함
        // 따라서 이 상황에서는 기본형인 int가 아닌 객체인 Integer를 사용해야 한다
        log.info("username={}, age={}", username, age);
        return "ok";

    }


    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            // defaultValue 속성 값을 지정해주면 해당 값이 티폴트 값으로 들어감
            // 그럼 null값이 들어갈 경우가 없기 때문에 기본형을 사용해도 됨
            // "" 빈 값이 들어와도 디폴트 값으로 처리해줌
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());

        return "ok";

    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());

        return "ok";

    }



}
