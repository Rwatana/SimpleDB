package simpledb.file;

import java.nio.ByteBuffer;
import java.nio.charset.*;

public class Page {
   private ByteBuffer bb;
   public static Charset CHARSET = StandardCharsets.US_ASCII;

   // For creating data buffers
   public Page(int blocksize) {
      bb = ByteBuffer.allocateDirect(blocksize);
   }
   
   // For creating log pages
   public Page(byte[] b) {
      bb = ByteBuffer.wrap(b);
   }

   public int getInt(int offset) {
      return bb.getInt(offset);
   }

   public void setInt(int offset, int n) {
      bb.putInt(offset, n);
      // オフセットとインテジャーを入れている


   }

   public byte[] getBytes(int offset) {
      bb.position(offset);
      int length = bb.getInt();
      byte[] b = new byte[length];
      bb.get(b);
      return b;
   }

   public void setBytes(int offset, byte[] b) {
      bb.position(offset);
      // offset というのはページのどこにデータをおくのかを決定するもの
      // ストリングの長さを決定するという仕様から
      bb.putInt(b.length);

      bb.put(b);
   }
   
   public String getString(int offset) {
      byte[] b = getBytes(offset);
      return new String(b, CHARSET);
   }

   public void setString(int offset, String s) {
      byte[] b = s.getBytes(CHARSET);
      // byte の配列に変換している
      setBytes(offset, b);
      // pos1 はオフセットというもの
      
   }

   public static int maxLength(int strlen) {
      // string の長さとbyteの長さは一致するわけではない　だから　計算しなきゃ」
      float bytesPerChar = CHARSET.newEncoder().maxBytesPerChar();
      return Integer.BYTES + (strlen * (int)bytesPerChar);
   }

   // a package private method, needed by FileMgr
   ByteBuffer contents() {
      bb.position(0);
      return bb;
   }
}
