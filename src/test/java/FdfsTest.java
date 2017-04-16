
import java.io.File;

import com.xmomen.module.resource.api.DfsPath;
import com.xmomen.module.resource.api.DfsSdk;
import com.xmomen.module.resource.api.DfsService;

/**
 * 测试用例
 *
 */
public class FdfsTest 
{
    public static void main( String[] args ) {
    	DfsService dfsServcie = DfsSdk.getDfsInstance();
    	DfsPath path = dfsServcie.putObject(new File("G:\\work\\pic\\timg.jpg"), null, null);
    	String remotePath = path.getRemotePath();
    	String wholePath = dfsServcie.getHttpPath(remotePath);
    	System.out.println(remotePath);
    	System.out.println(wholePath);
    }
}
