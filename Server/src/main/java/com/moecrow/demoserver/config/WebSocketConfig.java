package com.moecrow.demoserver.config;

import com.moecrow.demoserver.handler.SocketHandler;
import com.moecrow.demoserver.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfig
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/22
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/app")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
