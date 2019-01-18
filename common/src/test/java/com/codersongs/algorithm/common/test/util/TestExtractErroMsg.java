package com.codersongs.algorithm.common.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestExtractErroMsg {
	private static Map<String, String> typeMap = new HashMap<String, String>();
	static {
		typeMap.put("DeviceId", "设备验证");
		typeMap.put("OppoPlainText", "纯文本");
		typeMap.put("Buttons", "按钮");
		typeMap.put("ClickAction", "点击事件");
		typeMap.put("News", "图文");
		typeMap.put("SinglePic", "纯图");
		typeMap.put("Voice", "纯音频");
		typeMap.put("VoiceSmall", "音频小图");
		typeMap.put("VoiceBg", "音频大图");
		typeMap.put("Vedio", "视频");
		typeMap.put("Location", "位置");
		typeMap.put("MiniProgram", "小程序");
		typeMap.put("SmallPicVoice", "音频小图");
		typeMap.put("BgPicVoice", "音频大图");
		typeMap.put("SingleVoice", "纯音频");
		typeMap.put("TextClickAction", "点击事件");
		typeMap.put("Single", "单图文");
	}
	
	public static void main(String[] args) {
//		List<String> readFile = readFile("C:/Users/song/Desktop/tmp/errormsg/oppo_active_origin.txt");
		List<String> readFile = readFile("C:/Users/song/Desktop/tmp/errormsg/ali_hangup_origin.txt");
		String checkPrefix = "function check";
		System.out.println("通用字段");
		for (String line : readFile) {
			if (line.contains(checkPrefix)) {
				if (line.contains("Type")) {
					String type = line.substring(line.indexOf(checkPrefix) + checkPrefix.length(), line.lastIndexOf("Type"));
					if (typeMap.get(type) == null) {
						System.out.println(type);
					}else {
						System.out.println(typeMap.get(type));
					}
				}else {
					String type = line.substring(line.indexOf(checkPrefix) + checkPrefix.length(), line.lastIndexOf("("));
					if (typeMap.get(type) == null) {
						System.out.println(type);
					}else {
						System.out.println(typeMap.get(type));
					}
				}
				
			}
			if (line.contains("$this->errorMsg")) {
				if (line.contains("服务号必填且不能为防骗提醒服务号") || line.contains("获取shopid对应商家信息失败")
						|| line.contains("时间格式不正确")) {
					continue;
				}
				System.out.println("\t" + line.trim().replace("$this->errorMsg = '", "").replace("';", "").replace("$this->errorMsg = \"", "").replace("\";", ""));
			}
		}
	}
	
	public static List<String> readFile(String path){
		List<String> result = new ArrayList<String>();
		File file=new File(path);
		BufferedReader reader=null;
		String temp=null;
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				result.add(temp);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		} finally{
			if(reader!=null){
				try{
					reader.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
