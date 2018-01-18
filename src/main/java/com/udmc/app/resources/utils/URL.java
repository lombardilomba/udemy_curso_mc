package com.udmc.app.resources.utils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Long> decodeLongList(String s) {
		return Arrays.asList(s.split(","))
								.stream()
								.map(x -> Long.parseLong(x))
								.collect(Collectors.toList());
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");	
		} catch (Exception e) {
			return "";
		}
	}
	
}