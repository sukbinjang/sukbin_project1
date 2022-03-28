package kr.sukbin_project1;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
    public static String getRequestURLWithQueryString(
            HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        return queryString == null ? requestURL.toString() :
                new StringBuilder(requestURL).append("?").append(queryString)
                        .toString();
    }
}