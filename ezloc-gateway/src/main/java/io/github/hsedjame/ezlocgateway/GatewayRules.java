package io.github.hsedjame.ezlocgateway;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties("ezloc.gateway")
public class GatewayRules {

    private Map<String, Microservice> rules;

    public Map<String, Microservice> getRules() {
        return rules;
    }

    public void setRules(Map<String, Microservice> rules) {
        this.rules = rules;
    }

    static class Microservice {
        private String prefixPath;
        private String host;
        private String port;

        public String getPrefixPath() {
            return prefixPath;
        }

        public void setPrefixPath(String prefixPath) {
            this.prefixPath = prefixPath;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }
}
