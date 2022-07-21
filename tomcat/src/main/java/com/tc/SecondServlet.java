package com.tc;

/**
 * @Description
 * @Author tc
 * @Date 2022/7/21 10:51
 **/
public class SecondServlet extends GPServlet{
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("this is second servlet");
    }
}
