package rxhttp.wrapper.param;

import okhttp3.RequestBody;
import rxhttp.wrapper.callback.ProgressCallback;
import rxhttp.wrapper.progress.ProgressRequestBody;

/**
 * User: ljx
 * Date: 2020-09-07
 * Time: 15:08
 */
public abstract class AbstractBodyParam<P extends AbstractBodyParam<P>> extends AbstractParam<P> {

    //Upload progress callback
    private ProgressCallback callback;

    /**
     * @param url    request url
     * @param method {@link Method#POST}、{@link Method#PUT}、{@link Method#DELETE}、{@link Method#PATCH}
     */
    public AbstractBodyParam(String url, Method method) {
        super(url, method);
    }

    @Override
    public final RequestBody buildRequestBody() {
        RequestBody requestBody = getRequestBody();
        //Wrap RequestBody if callback not null
        return callback != null ? new ProgressRequestBody(requestBody, callback) : requestBody;
    }

    @SuppressWarnings("unchecked")
    public final P setProgressCallback(ProgressCallback callback) {
        this.callback = callback;
        return (P) this;
    }
}
