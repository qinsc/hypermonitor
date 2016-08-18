package hyper.momitor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP地址帮助类
 *
 * @author qinsc (mailto:qinsc@primeton.com)
 */

public class IPHelper {
	/**
	 * 检查ip是否合法，ip中带掩码, 如: 192.168.2.14/24
	 * 
	 * @param ipMask
	 * @return
	 */
	public static boolean isIPMask(String ipMask) {
		return parseIPMask(ipMask) != null;
	}

	/**
	 * 检查ip是否合法，ip中不带掩码, 如: 192.168.2.14
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isIP(String ip) {
		final String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(ip);
		boolean ipAddress = mat.find();
		return ipAddress;
	}
	
	/**
	 * 检查mask是否合法。mask 格式可以为数字，如24，，也可以为类似ip地址的格式，如：255.255.255.0
	 * @param mask
	 * @return
	 */
	public boolean isMask(String mask) {
		return parseMask(mask) != 0;
	}
	
	/**
	 * 获取两个地址之间所有的ip地址 
	 * @param startIPMask
	 * @param endIPMask
	 * @return
	 */
	public List<String> getIpsInRange(String startIPMask, String endIPMask){
		List<String> ips = new ArrayList<>();
		IPMask startIM = parseIPMask(startIPMask);
		IPMask endIM = parseIPMask(startIPMask);
		
		// 两者必须在同一网段
		if (startIM.mask != endIM.mask) {
			return null;
		}
		
		
		
		return ips;
	}

	private static int parseMask(String mask) {
		if (mask.indexOf(".") != -1) {
			String binaryString = toBinaryString(mask);
			// 检查拼出来的二进制字符串中，1与0是否连续重复，不连续重复的话，掩码不合法
			int len = binaryString.length();
			char c = 'a';
			int changeTimes = 0, oneCounts = 0;
			for (int i = 0; i < len; i++) {
				char cc  = binaryString.charAt(i);
				if ( cc != c) {
					c = cc; 
					changeTimes ++;
				}
				
				// 记录连续1的个数
				if (cc == '1') {
					oneCounts ++;
				}
				
				// 最多可以允许两次变化，第一次为由'a'变为'1'，第二次由'1'变成0，超过次数了，这个掩码就不合法了 
				if (changeTimes == 3) {
					return 0;
				}
			}
			return oneCounts;
		}
		
		try {
			int m = Integer.parseInt(mask);
			if (m > 1 && m < 32) {
				return m;
			}
		} catch (NumberFormatException e) {
		}
		return 0;
	}
	
	private static String toBinaryString(String ipOrMask) {
		String[] infos = ipOrMask.split("\\.");
		if (infos.length != 4) {
			return null;
		}
		
		// 将 255.255.255.0 格式的掩码转换成4段长度为8的二进制字符串，并拼起来
		StringBuffer buf = new StringBuffer();
		for (String info:infos) {
			String v = to8ByteBinaryString(info);
			if (v == null) {
				return null;
			}
			buf.append(v);
		}
		return buf.toString();
	}
	
	private static String to8ByteBinaryString(String value) {
		int m = -1;
		try {
			m = Integer.parseInt(value);
		} catch (NumberFormatException e) {
		}
		if (m >= 0 && m <= 255) {
			StringBuffer buf = new StringBuffer();
			String v = Integer.toBinaryString(m);
			int needZeros = 8 - v.length();
			for (int i = 0; i < needZeros; i++) {
				buf.append('0');
			}
			return buf.append(v).toString();
		}
		return null;
	}
	

	/**
	 * 
	 * @param ipAddr
	 * @return
	 */
	private static IPMask parseIPMask(String ipAddr) {
		// 是否为 ip/mask
		String[] infos = ipAddr.split("/");
		if (infos.length != 2) {
			return null;
		}
		
		// ip 是否合法
		if (!isIP(infos[0])) {
			return null;
		}
		
		// mask 是否合法
		int mask = parseMask(infos[1]);
		if (mask == 0) {
			return null;
		}
		return new IPMask(infos[0], mask);
	}

	/**
	 * 静态内部类
	 * 
	 * @author qinsc (mailto:qinsc@primeton.com)
	 */
	public static class IPMask {
		String ip;
		int mask;

		private IPMask(String ip, int mask) {
			this.ip = ip;
			this.mask = mask;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(parseMask("23")); 
		System.out.println(parseMask("255.255.252.0")); 
		System.out.println(parseMask("255.255.0.0")); 
	}
}
