package simpledb.file;

import java.io.*;
import simpledb.server.SimpleDB;

public class FileTest {
   public static void main(String[] args) throws IOException {
      SimpleDB db = new SimpleDB("filetest", 400, 8);
      FileMgr fm = db.fileMgr();
      BlockId blk = new BlockId("testfile", 2);
      int pos1 = 88;
      //これは８８の位置にデータを入れるために値を作成した

      Page p1 = new Page(fm.blockSize());
      // stiring set siteiru

      p1.setString(pos1, "abcdefghijklm");
      int size = Page.maxLength("abcdefghijklm".length());

      assert fm.getNumBlocksWritten() == 1 : "error";
      fm.write(blk,p1);
      assert fm.getNumBlocksWritten() == 2 : "error";
      



      // page のメソッド
      int pos2 = pos1 + size;
      p1.setInt(pos2, 345);
      fm.write(blk, p1);
      // fm no write de p1 ni kakikomi 

      Page p2 = new Page(fm.blockSize());
      fm.read(blk, p2);
      System.out.println("offset " + pos2 + " contains " + p2.getInt(pos2));
      System.out.println("offset " + pos1 + " contains " + p2.getString(pos1));
   }
}