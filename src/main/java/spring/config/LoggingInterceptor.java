package spring.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    // 요청 시작 시간 저장
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        System.out.println("==== 요청 시작 ====");
        System.out.println("요청 URI: " + request.getRequestURI());
        System.out.println("HTTP 메서드: " + request.getMethod());
        System.out.println("요청 파라미터: " + getParameters(request));
        return true; // true를 반환하면 다음으로 진행
    }

    // 컨트롤러 처리 후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("컨트롤러 처리 완료");
    }

    // 요청 완료 후 (뷰 렌더링 이후)
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        System.out.println("==== 요청 종료 ====");
        System.out.println("응답 상태 코드: " + response.getStatus());
        System.out.println("처리 시간: " + executeTime + "ms");
        if (ex != null) {
            System.out.println("예외 발생: " + ex.getMessage());
        }
    }

    // 요청 파라미터 로깅
    private String getParameters(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder params = new StringBuilder();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            params.append(paramName).append("=").append(paramValue).append(", ");
        }
        return params.length() > 0 ? params.substring(0, params.length() - 2) : "없음";
    }
}