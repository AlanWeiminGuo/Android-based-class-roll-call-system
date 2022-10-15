package com.bs.course.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 使用Gson来转换json数据
 */
public class GsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	private Gson gson = new GsonBuilder()
			.serializeNulls()		//null值属性也需要序列化
			.setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期转换
			.create();

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public GsonHttpMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET));
	}

	/**
	 * 读JSON数据
	 */
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		try {
			Object a = gson.fromJson(convertStreamToString(inputMessage.getBody()), clazz);
			return a;
		} catch (JsonSyntaxException e) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
		}
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * 写JSON数据
	 */
	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String json = gson.toJson(t);
		outputMessage.getBody().write(json.getBytes());
	}

	/**
	 * 流转换成字符串
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

}
