package org.springframework.tronic.util;


public class Base64 {

	public static String encode(String string) {
		return getString(encode(getBinaryBytes(string)));
	}

	public static byte[] encode(byte[] is) {
		int i = is.length;
		StringBuffer stringbuffer = new StringBuffer((i / 3 + 1) * 4);
		for (int i_0_ = 0; i_0_ < i; i_0_++) {
			int i_1_ = is[i_0_] >> 2 & 0x3f;
			stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i_1_));
			i_1_ = is[i_0_] << 4 & 0x3f;
			if (++i_0_ < i)
				i_1_ |= is[i_0_] >> 4 & 0xf;
			stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i_1_));
			if (i_0_ < i) {
				i_1_ = is[i_0_] << 2 & 0x3f;
				if (++i_0_ < i)
					i_1_ |= is[i_0_] >> 6 & 0x3;
				stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i_1_));
			} else {
				i_0_++;
				stringbuffer.append('=');
			}
			if (i_0_ < i) {
				i_1_ = is[i_0_] & 0x3f;
				stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i_1_));
			} else
				stringbuffer.append('=');
		}
		return getBinaryBytes(stringbuffer.toString());
	}

	public static String decode(String string)  throws Exception{
		return getString(decode(getBinaryBytes(string)));
	}

	public static byte[] decode(byte[] is) throws Exception{
		int i = is.length;
		StringBuffer stringbuffer = new StringBuffer(i * 3 / 4);
		for (int i_2_ = 0; i_2_ < i; i_2_++) {
			int i_3_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(is[i_2_]);
			i_2_++;
			int i_4_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(is[i_2_]);
			i_3_ = i_3_ << 2 | i_4_ >> 4 & 0x3;
			stringbuffer.append((char) i_3_);
			if (++i_2_ < i) {
				i_3_ = is[i_2_];
				if (61 == i_3_)
					break;
				i_3_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) i_3_);
				i_4_ = i_4_ << 4 & 0xf0 | i_3_ >> 2 & 0xf;
				stringbuffer.append((char) i_4_);
			}
			if (++i_2_ < i) {
				i_4_ = is[i_2_];
				if (61 == i_4_)
					break;
				i_4_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) i_4_);
				i_3_ = i_3_ << 6 & 0xc0 | i_4_;
				stringbuffer.append((char) i_3_);
			}
		}
		return getBinaryBytes(stringbuffer.toString());
	}

	public static String getString(byte[] is) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; i < is.length; i++)
			stringbuffer.append((char) is[i]);
		return stringbuffer.toString();
	}

	public static byte[] getBinaryBytes(String string) {
		byte[] is = new byte[string.length()];
		for (int i = 0; i < is.length; i++)
			is[i] = (byte) string.charAt(i);
		return is;
	}

	public static void main(String[] strings) {
		
		/*
         * String string; if (strings.length > 0) string = strings[0]; else string = "Now is the time for all good men"; System.out.println("Encoding string [" +
         * string + "]"); string = encode(string); System.out.println("Encoded string [" + string + "]"); string = decode(string); System.out.println("Decoded
         * string [" + string + "]"); System.out.println(); byte[] is = getBinaryBytes(string); System.out.println("Encoding bytes [" + getString(is) + "]"); is =
         * encode(is); System.out.println("Encoded bytes [" + getString(is) + "]"); is = decode(is); System.out.println("Decoded bytes [" + getString(is) +
         * "]"); byte[] is_5_ = new byte[16]; is_5_[0] = (byte) 47; is_5_[1] = (byte) -38; is_5_[2] = (byte) -93; is_5_[3] = (byte) -43; is_5_[4] = (byte) -111;
         * is_5_[5] = (byte) 94; is_5_[6] = (byte) -107; is_5_[7] = (byte) 68; is_5_[8] = (byte) -107; is_5_[9] = (byte) 24; is_5_[10] = (byte) -4; is_5_[11] =
         * (byte) 62; is_5_[12] = (byte) -8; is_5_[13] = (byte) -121; is_5_[14] = (byte) -112; is_5_[15] = (byte) 4; byte[] is_6_ = encode(is_5_); String
         * string_7_ = new String(is_6_); byte[] is_8_ = string_7_.getBytes(); byte[] is_9_ = decode(is_8_); System.out.println(new String(is_6_));
         * System.out.println(string_7_); System.out.println(new String(is_6_)); debug("out", is_9_);
         */
	}

	protected static void debug(String string, byte[] is) {
		System.out.print(string);
		System.out.println(is.length);
		for (int i = 0; i < is.length; i++) {
			if (is[i] > 15 || is[i] < 0)
				System.out.print(Integer.toHexString(is[i] & 0xff) + " ");
			else
				System.out.print("0" + Integer.toHexString(is[i] & 0xff) + " ");
		}
		System.out.println(" ");
	}
}
