import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.account.service.PasswordHelper;

/**
 * Created by Jeng on 2016/4/20.
 */
public class Test {
    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        System.out.println(passwordHelper.encryptPassword("123456","185d5b6be80f7aca9f27174e517a0e6b"));
        System.out.println(StringUtilsExt.getUUID(16));
    }
}
