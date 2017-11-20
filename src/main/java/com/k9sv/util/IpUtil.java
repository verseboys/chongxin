package com.k9sv.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

/**
 * @author michael <br>
 *         blog: http://sjsky.iteye.com <br>
 *         mail: sjsky007@gmail.com
 */
public class IpUtil {

    private final static int INADDRSZ = 4;

    public static String getIp(HttpServletRequest request){
    	String ip = request.getHeader("x-forwarded-for");  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("WL-Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getRemoteAddr();   
        }  
                
        return ip;
    }
    /**
     * 把IP地址转化为字节数组
     * @param ipAddr
     * @return byte[]
     */
    public static byte[] ipToBytesByInet(String ipAddr) {
        try {
            return InetAddress.getByName(ipAddr).getAddress();
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * 把IP地址转化为int
     * @param ipAddr
     * @return int
     */
    public static byte[] ipToBytesByReg(String ipAddr) {
        byte[] ret = new byte[4];
        try {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
            return ret;
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }

    }

    /**
     * 字节数组转化为IP
     * @param bytes
     * @return int
     */
    public static String bytesToIp(byte[] bytes) {
        return new StringBuffer().append(bytes[0] & 0xFF).append('.').append(
                bytes[1] & 0xFF).append('.').append(bytes[2] & 0xFF)
                .append('.').append(bytes[3] & 0xFF).toString();
    }

    /**
     * 根据位运算把 byte[] -> int
     * @param bytes
     * @return int
     */
    public static int bytesToInt(byte[] bytes) {
        int addr = bytes[3] & 0xFF;
        addr |= ((bytes[2] << 8) & 0xFF00);
        addr |= ((bytes[1] << 16) & 0xFF0000);
        addr |= ((bytes[0] << 24) & 0xFF000000);
        return addr;
    }

    /**
     * 把IP地址转化为int
     * @param ipAddr
     * @return int
     */
    public static int ipToInt(String ipAddr) {
        try {
            return bytesToInt(ipToBytesByInet(ipAddr));
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * ipInt -> byte[]
     * @param ipInt
     * @return byte[]
     */
    public static byte[] intToBytes(int ipInt) {
        byte[] ipAddr = new byte[INADDRSZ];
        ipAddr[0] = (byte) ((ipInt >>> 24) & 0xFF);
        ipAddr[1] = (byte) ((ipInt >>> 16) & 0xFF);
        ipAddr[2] = (byte) ((ipInt >>> 8) & 0xFF);
        ipAddr[3] = (byte) (ipInt & 0xFF);
        return ipAddr;
    }

    /**
     * 把int->ip地址
     * @param ipInt
     * @return String
     */
    public static String intToIp(int ipInt) {
        return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
                .append((ipInt >> 16) & 0xff).append('.').append(
                        (ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff))
                .toString();
    }

    /**
     * 把192.168.1.1/24 转化为int数组范围
     * @param ipAndMask
     * @return int[]
     */
    public static int[] getIPIntScope(String ipAndMask) {

        String[] ipArr = ipAndMask.split("/");
        if (ipArr.length != 2) {
            throw new IllegalArgumentException("invalid ipAndMask with: "
                    + ipAndMask);
        }
        int netMask = Integer.valueOf(ipArr[1].trim());
        if (netMask < 0 || netMask > 31) {
            throw new IllegalArgumentException("invalid ipAndMask with: "
                    + ipAndMask);
        }
        int ipInt = IpUtil.ipToInt(ipArr[0]);
        int netIP = ipInt & (0xFFFFFFFF << (32 - netMask));
        int hostScope = (0xFFFFFFFF >>> netMask);
        return new int[] { netIP, netIP + hostScope };

    }

    /**
     * 把192.168.1.1/24 转化为IP数组范围
     * @param ipAndMask
     * @return String[]
     */
    public static String[] getIPAddrScope(String ipAndMask) {
        int[] ipIntArr = IpUtil.getIPIntScope(ipAndMask);
        return new String[] { IpUtil.intToIp(ipIntArr[0]),
                IpUtil.intToIp(ipIntArr[0]) };
    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     * @param ipAddr ipAddr
     * @param mask mask
     * @return int[]
     */
    public static int[] getIPIntScope(String ipAddr, String mask) {

        int ipInt;
        int netMaskInt = 0, ipcount = 0;
        try {
            ipInt = IpUtil.ipToInt(ipAddr);
            if (null == mask || "".equals(mask)) {
                return new int[] { ipInt, ipInt };
            }
            netMaskInt = IpUtil.ipToInt(mask);
            ipcount = IpUtil.ipToInt("255.255.255.255") - netMaskInt;
            int netIP = ipInt & netMaskInt;
            int hostScope = netIP + ipcount;
            return new int[] { netIP, hostScope };
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid ip scope express  ip:"
                    + ipAddr + "  mask:" + mask);
        }

    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     * @param ipAddr ipAddr
     * @param mask mask
     * @return String[]
     */
    public static String[] getIPStrScope(String ipAddr, String mask) {
        int[] ipIntArr = IpUtil.getIPIntScope(ipAddr, mask);
        return new String[] { IpUtil.intToIp(ipIntArr[0]),
                IpUtil.intToIp(ipIntArr[0]) };
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String ipAddr = "124.23.31.255";
        
        int ipInt = IpUtil.ipToInt(ipAddr);

        System.out.println("IP: " + ipAddr + "  --> int: " + ipInt);
        /**

        byte[] bytearr = IpUtil.ipToBytesByInet(ipAddr);

        StringBuffer byteStr = new StringBuffer();

        for (byte b : bytearr) {
            if (byteStr.length() == 0) {
                byteStr.append(b);
            } else {
                byteStr.append("," + b);
            }
        }
        System.out.println("IP: " + ipAddr + " ByInet --> byte[]: [ " + byteStr
                + " ]");

        bytearr = IpUtil.ipToBytesByReg(ipAddr);
        byteStr = new StringBuffer();

        for (byte b : bytearr) {
            if (byteStr.length() == 0) {
                byteStr.append(b);
            } else {
                byteStr.append("," + b);
            }
        }
        System.out.println("IP: " + ipAddr + " ByReg  --> byte[]: [ " + byteStr
                + " ]");

        System.out.println("byte[]: " + byteStr + " --> IP: "
                + IpUtil.bytesToIp(bytearr));

        int ipInt = IpUtil.ipToInt(ipAddr);

        System.out.println("IP: " + ipAddr + "  --> int: " + ipInt);

        System.out.println("int: " + ipInt + " --> IP: "
                + IpUtil.intToIp(ipInt));

        String ipAndMask = "192.168.1.1/24";

        int[] ipscope = IpUtil.getIPIntScope(ipAndMask);
        System.out.println(ipAndMask + " --> int地址段：[ " + ipscope[0] + ","
                + ipscope[1] + " ]");

        System.out.println(ipAndMask + " --> IP 地址段：[ "
                + IpUtil.intToIp(ipscope[0]) + ","
                + IpUtil.intToIp(ipscope[1]) + " ]");

        String ipAddr1 = "192.168.1.1", ipMask1 = "255.255.255.0";

        int[] ipscope1 = IpUtil.getIPIntScope(ipAddr1, ipMask1);
        System.out.println(ipAddr1 + " , " + ipMask1 + "  --> int地址段 ：[ "
                + ipscope1[0] + "," + ipscope1[1] + " ]");

        System.out.println(ipAddr1 + " , " + ipMask1 + "  --> IP地址段 ：[ "
                + IpUtil.intToIp(ipscope1[0]) + ","
                + IpUtil.intToIp(ipscope1[1]) + " ]");
                
                **/

    }
}
