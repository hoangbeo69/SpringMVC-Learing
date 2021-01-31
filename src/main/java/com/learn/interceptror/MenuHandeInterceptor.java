package com.learn.interceptror;

import com.learn.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuHandeInterceptor implements HandlerInterceptor {
    @Autowired
    private HomeService homeService;

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return true/false
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //Thực hiện kiểm tra các điều kiện hoặc có thể chạy trước để thiết lập thêm các biến kháo vào dũ liệu
        //Trong hiện tại thì chỉ thực hiện add thêm model menu vào request trước khi controller xử lý request để lấy dữ liệu trước khi
        //xử lý các bước tiếp theo trong controller
        httpServletRequest.setAttribute("menu", homeService.loadMenu());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
