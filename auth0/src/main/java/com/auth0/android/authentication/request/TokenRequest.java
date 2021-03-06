package com.auth0.android.authentication.request;

import com.auth0.android.Auth0Exception;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.request.ParameterizableRequest;
import com.auth0.android.request.Request;
import com.auth0.android.result.Credentials;

import java.util.Map;

/**
 * Auth Request to obtain tokens using OAuth2 {@literal /oauth/token} method
 */
@SuppressWarnings("WeakerAccess")
public class TokenRequest implements Request<Credentials, AuthenticationException> {

    private static final String OAUTH_CODE_VERIFIER_KEY = "code_verifier";

    private final ParameterizableRequest<Credentials, AuthenticationException> request;

    public TokenRequest(ParameterizableRequest<Credentials, AuthenticationException> request) {
        this.request = request;
    }

    /**
     * Adds additional parameters to the request.
     *
     * @param parameters as a non-null dictionary
     * @return itself
     */
    public TokenRequest addParameters(Map<String, Object> parameters) {
        request.addParameters(parameters);
        return this;
    }

    /**
     * Add a header to the request, e.g. "Authorization"
     *
     * @param name  of the header
     * @param value of the header
     * @return itself
     */
    public TokenRequest addHeader(String name, String value) {
        request.addHeader(name, value);
        return this;
    }

    /**
     * Adds the code verifier to the request (Public Clients)
     *
     * @param codeVerifier the code verifier used to generate the challenge sent to /authorize.
     * @return itself
     */
    @SuppressWarnings("WeakerAccess")
    public TokenRequest setCodeVerifier(String codeVerifier) {
        this.request.addParameter(OAUTH_CODE_VERIFIER_KEY, codeVerifier);
        return this;
    }

    @Override
    public void start(BaseCallback<Credentials, AuthenticationException> callback) {
        request.start(callback);
    }

    @Override
    public Credentials execute() throws Auth0Exception {
        return request.execute();
    }
}
