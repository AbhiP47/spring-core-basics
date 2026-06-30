package org.abhinav;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*

@ConfigurationProperties lets you bind a whole group of related properties
(from application.properties / application.yml, environment variables, etc.) directly to a Java object,
instead of injecting individual values one by one with @Value.
Why it matters (significance)

Type-safe configuration — properties are mapped to actual fields with proper types
 (int, boolean, List, Map, nested objects), so mismatches are caught early.
Grouping — instead of 10 separate @Value("${app.x}") injections scattered across classes,
 you get one clean POJO holding all related settings.
Validation support — works with @Validated and JSR-303 annotations (@NotNull, @Min, etc.)
to validate config at startup.
IDE support — Spring Boot can generate metadata so your IDE autocompletes property names in
application.properties.
Relaxed binding — Spring Boot automatically matches my-app.some-value, my-app.someValue, and
 MY_APP_SOMEVALUE to the same field, so it's flexible across property files, env vars, and YAML.
 */
@Component
@ConfigurationProperties("payment-property")
public class PaymentProperties {

    private String type;
    private int retryCount;
    private boolean enabled;
    private int timeout;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}