// package simpledb.file;

// import java.io.File;
// import java.io.IOException;

// import simpledb.server.SimpleDB;

package simpledb.file;
import java.io.*;
import simpledb.server.SimpleDB;


public class FileTest {
   public static void main(String[] args) throws IOException {
      SimpleDB db = new SimpleDB("filetest", 400, 8);
      // FileMgr fm = db.fileMgr();
      // BlockId blk = new BlockId("testfile", 2);
      // int pos1 = 88;
      // //これは８８の位置にデータを入れるために値を作成した

      // Page p1 = new Page(fm.blockSize());
      // // stiring set siteiru
      FileMgr fm = db.fileMgr();
      BlockId blk = new BlockId("testfile", 2);
      int pos1 = 88;

      Page p1 = new Page(fm.blockSize());
      p1.setString(pos1, "abcdefghijklm");
      int size = Page.maxLength("abcdefghijklm".length());
      int pos2 = pos1 + size;
      p1.setInt(pos2, 345);

      assert fm.getNumBlocksWritten() == 1 : "The number of blocks written is not expected.";
      fm.write(blk, p1);
      assert fm.getNumBlocksWritten() == 2 : "The number of blocks written is not expected.";

      Page p2 = new Page(fm.blockSize());

      assert fm.getNumBlocksRead() == 0 : "The number of blocks read is not expected.";
      fm.read(blk, p2);
      assert fm.getNumBlocksRead() == 1 : "The number of blocks read is not expected.";
      



      System.out.println("offset " + pos2 + " contains " + p2.getInt(pos2));
      System.out.println("offset " + pos1 + " contains " + p2.getString(pos1));

      File testDirectoryPath = new File("filetest");
      for (String filename : testDirectoryPath.list())
         new File(testDirectoryPath, filename).delete();

       testDirectoryPath.delete();
   }
}