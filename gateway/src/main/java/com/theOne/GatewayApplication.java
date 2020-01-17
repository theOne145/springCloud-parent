package com.theOne;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }

    class zuul extends ZuulFilter {

        @Override
        public String filterType() {
            return null;
        }

        @Override
        public int filterOrder() {
            return 0;
        }

        @Override
        public boolean shouldFilter() {
            return false;
        }

        @Override
        public Object run() throws ZuulException {
            return null;
        }
    }
}
