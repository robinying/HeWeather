package com.yubin.heweather.network;

import android.text.TextUtils;
import android.util.Log;

import com.yubin.heweather.utils.XLog;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class LoggerInterceptor implements Interceptor {
    private boolean showResponse;
    private String tag;

    public LoggerInterceptor(String s) {
        this(s, false);
    }

    public LoggerInterceptor(String tag, boolean showResponse) {
        if (TextUtils.isEmpty(tag)) {
            tag = "OkHttpUtils";
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    private String bodyToString(Request request) {
        try {
            Request build = request.newBuilder().build();
            Buffer buffer = new Buffer();
            build.body().writeTo((BufferedSink) buffer);
            return buffer.readUtf8();
        } catch (IOException ex) {
            return "something error when show requestBody.";
        }
    }

    private boolean isText(MediaType mediaType) {
        return (mediaType.type() != null && mediaType.type().equals("text")) || (mediaType.subtype() != null && (mediaType.subtype().equals("json") || mediaType.subtype().equals("xml") || mediaType.subtype().equals("html") || mediaType.subtype().equals("webviewhtml") || mediaType.subtype().equals("javascript")));
    }

    private void logForRequest(Request request) {
        try {
            String string = request.url().toString();
            Headers headers = request.headers();
            String method = request.method();
            XLog.d(tag, "-------------------------request----------------------------");
            XLog.d(tag, "method : " + method);
            XLog.d(tag, "url : " + string);
            if (headers != null && headers.size() > 0) {
                XLog.d(tag, "headers : " + headers.toString());
            }
            RequestBody body = request.body();
            if (body != null) {
                if ("post".equalsIgnoreCase(method)) {
                    StringBuilder sb = new StringBuilder();
                    if (request.body() instanceof FormBody) {
                        FormBody formBody = (FormBody) request.body();
                        for (int i = 0; i < formBody.size(); i++) {
                            sb.append(formBody.encodedName(i) + "=" + formBody.encodedValue(i) + "&");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        XLog.d(tag, "RequestParams:{" + sb.toString() + "}");
                    }
                } else {
                    MediaType contentType = body.contentType();
                    if (contentType != null) {
                        XLog.d(tag, "requestBody's contentType : " + contentType.toString());
                        if (isText(contentType)) {
                            XLog.d(tag, "requestBody's content : " + bodyToString(request));
                            return;
                        }
                        XLog.d(tag, "requestBody's content :  maybe [file part] , too large too print , ignored!");
                    }
                }

            }
        } catch (Exception ex) {
        }
    }

    private Response logForResponse(Response response) {
        try {
            Log.d(tag, "-------------------------response-------------------------");
            Response build = response.newBuilder().build();
            XLog.d(tag, "url : " + build.request().url());
            XLog.d(tag, "code : " + build.code());
            XLog.d(tag, "protocol : " + build.protocol());
            if (!TextUtils.isEmpty((CharSequence) build.message())) {
                XLog.d(tag, "message : " + build.message());
            }
            if (showResponse) {
                ResponseBody body = build.body();
                if (body != null) {
                    MediaType contentType = body.contentType();
                    if (contentType != null) {
                        Log.d(tag, "responseBody's contentType : " + contentType.toString());
                        if (isText(contentType)) {
                            String string = body.string();
                            XLog.d(tag, "responseBody's content : " + string);
                            return response.newBuilder().body(ResponseBody.create(contentType, string)).build();
                        }
                        XLog.d(tag, "responseBody's content :  maybe [file part] , too large too print , ignored!");
                        return response;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return response;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        return logForResponse(chain.proceed(request));
    }
}
