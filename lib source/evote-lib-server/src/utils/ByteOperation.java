package utils;

import java.nio.ByteBuffer;

public class ByteOperation {
	public static ByteBuffer getIntByteBuffer(int a) {
		return ByteBuffer.allocate(4).putInt(a);
	}
}
