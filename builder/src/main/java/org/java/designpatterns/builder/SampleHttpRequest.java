package org.java.designpatterns.builder;

public class SampleHttpRequest {
    private final String url;
    private final Long timeout;
    private final String method;
    private final String body;
    private final String contentType;

    private SampleHttpRequest(Builder builder) {
        this.url = builder.url;
        this.timeout = builder.timeout;
        this.method = builder.method;
        this.body = builder.body;
        this.contentType = builder.contentType;
    }

    public static class Builder {
        private String url;
        private Long timeout;
        private String method;
        private String body;
        private String contentType;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder timeout(Long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public SampleHttpRequest build() {
            return new SampleHttpRequest(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "SampleHttpRequest[url=" + url + ", timeout=" + timeout + ", method=" + method + ", body=" + body + ", contentType=" + contentType + "]";
    }
}
