package dev.meirong.demos.webflux_app.infra.netty;

import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.netty.channel.MicrometerChannelMetricsRecorder;

@Configuration
public class NettyConfig {

    @Bean
    public NettyServerCustomizer nettyServerCustomizer() {
        return httpServer -> httpServer.metrics(true,
                () -> new MicrometerChannelMetricsRecorder("MY_SERVICE", "MY_SERVICE"))
                .accessLog(true);
    }
}
