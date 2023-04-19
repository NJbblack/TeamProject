package com.bb.voyage.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	public static void makeCookie( HttpServletResponse response, 
							String cookieName,
							String cookieValue,
							int cookieAge) {
		Cookie cookie = new Cookie(cookieName,cookieValue);
		cookie.setMaxAge(cookieAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static String readCookie(HttpServletRequest request,String requestCookieName) {
		Cookie cookies[] = request.getCookies();
		String responseCookieValue = null;
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				String cookieName = cookie.getName();
				String cookieValue = cookie.getValue();
				if(cookieName.equals(requestCookieName)) {
					responseCookieValue = cookieValue;
				}
			}
		}
		return responseCookieValue;
	}
	public static void deleteCookie( HttpServletResponse response, 
			String cookieName) {
		makeCookie(response, cookieName, null, 0);
	}
}
