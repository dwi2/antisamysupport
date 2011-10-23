package play.modules.antisamysupport;


import java.io.File;
import play.*;
import play.vfs.VirtualFile;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.AntiSamy;

public class AntiSamyServiceImpl implements AntiSamyService {

  public final static String POLICY_FILE_LOCATION="conf/antisamy.xml";
  public final static String DEFAULT_POLICY_FILE_LOCATION="conf/antisamy-default.xml";

  private static AntiSamy as = null;
  private static Policy policy = null;

  public AntiSamyServiceImpl() throws Exception {
    as = new AntiSamy();
    File policyFile = new File(POLICY_FILE_LOCATION);
    if(policyFile.exists()) {
      Logger.info(POLICY_FILE_LOCATION + " exists!");
      policy = Policy.getInstance(policyFile);
    }
    else {
      Logger.info(POLICY_FILE_LOCATION + " does not exist, go find "+DEFAULT_POLICY_FILE_LOCATION+" instead");
      VirtualFile virtualPolicyFile = Play.getVirtualFile(DEFAULT_POLICY_FILE_LOCATION);
      policy = Policy.getInstance(virtualPolicyFile.getRealFile());
    }
  }

  @Override
  public String filter(String input) {
    String result = AntiSamyPlugin.EMPTY;
    try {
      CleanResults cr = as.scan(input, policy);
      result = cr.getCleanHTML();
    } catch (Exception e) {
      Logger.error(e, "Error when scanning input HTML");
    }
    return result;
  }

}
