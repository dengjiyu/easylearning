package top.easylearning.util;

import java.util.UUID;

public class UUIDTools {

	public UUIDTools() {
		// TODO Auto-generated constructor stub
	}

	//UUID：生产全球唯一的ID
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").substring(0, 6);
	}
}