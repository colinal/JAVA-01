package cn.colinal.homework_03;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

public class MyRequestFilterImpl implements MyRequestFilter{
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        System.out.println("filter doing----------->");
        fullRequest.setMethod(HttpMethod.CONNECT);
        System.out.println("filter done------------>");
    }
}
