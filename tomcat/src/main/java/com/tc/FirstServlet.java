package com.tc;

/**
 * @Description
 * @Author tc
 * @Date 2022/7/21 10:49
 **/
public class FirstServlet extends GPServlet {
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("this is first servlet");
    }
}
