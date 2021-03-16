package com.smart.gateway.config;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



import java.io.UnsupportedEncodingException;
import java.net.URI;

import java.util.List;
import java.util.Map;


@Slf4j
@Configuration
public class AccessFilter implements  Ordered ,GlobalFilter{

    static String[] shiteList={"/user/login","/","/user/register"};

    /**
     * 优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        RequestPath path = exchange.getRequest().getPath();

        //白名单
//        if(Arrays.stream(shiteList).anyMatch(item->{
//            return path.value().contains(item);
//        }));

        ServerHttpRequest request = exchange.getRequest();

        MultiValueMap<String, String> map = request.getQueryParams();

                for(Map.Entry<String, List<String>> m :map.entrySet()){
            log.info("参数:"+m.getKey()+"值为："+m.getValue());
        }

//        HttpServletRequest req= (HttpServletRequest) request;
//        Map<String, String[]> map = req.getParameterMap();
//        for(Map.Entry<String, String[]> m :map.entrySet()){
//            log.info("参数:"+m.getKey()+"值为："+m.getValue()[0]);
//        }


        String method = request.getMethodValue();
        URI uri = request.getURI();
        log.info("请求的地址是:"+uri.getPath());
        String contentType = request.getHeaders().getFirst("Content-Type");
        if ("POST".equals(method)) {
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        try {
                            String bodyString = new String(bytes, "utf-8");
                            log.info("请求参数为:"+bodyString.toString());//打印请求参数
                            log.info("请求参数为"+JSON.toJSON(bodyString));
                            exchange.getAttributes().put("POST_BODY", bodyString);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        DataBufferUtils.release(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                            DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                            return Mono.just(buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        return chain.filter(exchange.mutate().request(mutatedRequest)
                                .build());
                    });
        }
        return chain.filter(exchange);
    }
}
